// on connection, first get list of all orgs, project, teams user is in and use that to subscribe

import WebSocket from "isomorphic-ws";
import ReconnectingWebSocket from "reconnecting-websocket";
import { v4 as generateUuid } from "uuid";
import { UUID, User } from "..";

const heartbeatInterval = 8000;
const apiUrl = "ws://localhost:8081/web-handler";
const connectionTimeout = 15000;

export type Token = string;
export type FetchID = UUID;
export type Ref = UUID;
export type Opcode = string;
export type Logger = (
  direction: "in" | "out",
  opcode: Opcode,
  data?: unknown,
  fetchId?: Ref,
  raw?: string
) => void;
export type ListenerHandler<Data = unknown> = (
  data: Data,
  fetchId?: Ref
) => void;
export type Listener<Data = unknown> = {
  opcode: Opcode;
  handler: ListenerHandler<Data>;
};


export type Connection = {
    close: () => void;
    once: <Data = unknown>(
    opcode: Opcode,
    handler: ListenerHandler<Data>
) => void;
    addListener: <Data = unknown>(
      opcode: Opcode,
      handler: ListenerHandler<Data>
    ) => () => void;
    user: User;
    send: (opcode: Opcode, data: unknown, fetchId?: FetchID) => void;
    sendCall: (
      opcode: Opcode,
      data: unknown,
      doneOpcode?: Opcode
    ) => Promise<unknown>;
};

/**
 * Creates a Connection object
 * @param {Token} token Your token
 * @param {Token} refreshToken Your refresh token
 * @returns {Promise<Connection>} Connection object
 */
export const connect = (
  token: Token,
  refreshToken: Token,
  {
    logger = () => {},
    onConnectionTaken = () => {},
    onClearTokens = () => {},
    url = apiUrl,
    fetchTimeout,
    getAuthOptions,
    waitToReconnect
  }: {
    logger?: Logger;
    onConnectionTaken?: () => void;
    onClearTokens?: () => void;
    url?: string;
    fetchTimeout?: number;
    waitToReconnect?: boolean;
    getAuthOptions?: () => Partial<{
      token: Token;
      refreshToken: Token;
    }>;
  }
): Promise<Connection> => new Promise((resolve, reject) => {
  const socket = new ReconnectingWebSocket(url, [], {
    connectionTimeout,
    WebSocket,
  });
  const apiSend = (opcode: Opcode, data: unknown, fetchId?: FetchID) => {
    if (socket.readyState !== socket.OPEN) {
      return;
    }
    const raw = `{"op":"${opcode}","d":${JSON.stringify(data)}${
      fetchId ? `,"fetchId":"${fetchId}"` : ""
    }}`;

    socket.send(raw);
    logger("out", opcode, data, fetchId, raw);
  };

  const listeners: Listener[] = [];

  socket.addEventListener("close", (error) => {
    // eslint-disable-next-line no-console
    console.log(error);
    if (error.code === 4001) {
      // unauthorized token failed
      socket.close();
      onClearTokens();
    } else if (error.code === 4003) {
      // connection taken
      socket.close();
      onConnectionTaken();
    } else if (error.code === 4004) {
      // others
      socket.close();
      onClearTokens();
    }
    if (!waitToReconnect) reject(error);
  });

  socket.addEventListener("message", (e) => {
    if (e.data === `"pong"` || e.data === `pong`) {
      logger("in", "pong");

      return;
    }

    const message = JSON.parse(e.data);

    logger("in", message.op, message.d, message.fetchId, e.data);

    if (message.op === "auth-good") {
      const connection: Connection = {
        close: () => socket.close(),
        once: (opcode, handler) => {
          const listener = { opcode, handler } as Listener<unknown>;

          listener.handler = (...params) => {
            handler(...(params as Parameters<typeof handler>));
            listeners.splice(listeners.indexOf(listener), 1);
          };

          listeners.push(listener);
        },
        addListener: (opcode, handler) => {
          const listener = { opcode, handler } as Listener<unknown>;

          listeners.push(listener);

          return () => listeners.splice(listeners.indexOf(listener), 1);
        },
        user: message.d.user,
        send: apiSend,
        sendCall: (
          opcode: Opcode,
          parameters: unknown,
          doneOpcode?: Opcode
        ) =>
          new Promise((resolveCall, rejectFetch) => {
            // this is to avoid ws events queuing up while socket is closed
            // then it reconnects and fires before auth goes off
            // and you get logged out
            if (socket.readyState !== socket.OPEN) {
              rejectFetch(new Error("websocket not connected"));

              return;
            }
            const ref: FetchID | false = !doneOpcode && generateUuid();
            let timeoutId: NodeJS.Timeout | null = null;
            const unsubscribe = connection.addListener(
              doneOpcode ?? opcode + ":reply",
              (data, arrivedId) => {
                if (!doneOpcode && arrivedId !== ref) return;

                if (timeoutId) clearTimeout(timeoutId);

                unsubscribe();
                resolveCall(data);
              }
            );

            if (fetchTimeout) {
              timeoutId = setTimeout(() => {
                unsubscribe();
                rejectFetch(new Error("timed out"));
              }, fetchTimeout);
            }

            apiSend(opcode, parameters, ref || undefined);
          }),
      };

      resolve(connection);
    } else {
      listeners
        .filter(({ opcode }) => opcode === message.op)
        .forEach((it) =>
          it.handler(message.d || message.p, message.fetchId || message.ref)
        );
    }
  });

  socket.addEventListener("open", () => {
    const id = setInterval(() => {
      if (socket.readyState === socket.CLOSED) {
        clearInterval(id);
      } else {
        socket.send("ping");
        logger("out", "ping");
      }
    }, heartbeatInterval);

    // send auth to get user data to be used to subscribe
    apiSend("auth", {
      accessToken: token,
      refreshToken,
      ...getAuthOptions?.(),
    });
  });
});
