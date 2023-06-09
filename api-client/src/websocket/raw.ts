// on connection, first get list of all orgs, project, teams user is in and use that to subscribe

import WebSocket from "isomorphic-ws";
import ReconnectingWebSocket from "reconnecting-websocket";
import { v4 as generateUuid } from "uuid";
import { UUID } from "..";

const heartbeatInterval = 8000;
const apiUrl = "ws://localhost:8081/web-handler";
const connectionTimeout = 15000;

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
    send: (opcode: Opcode, data: unknown, fetchId?: FetchID) => void;
};
