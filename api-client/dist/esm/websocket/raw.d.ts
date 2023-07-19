import { UUID, User } from "..";
export type Token = string;
export type FetchID = UUID;
export type Ref = UUID;
export type Opcode = string;
export type Logger = (direction: "in" | "out", opcode: Opcode, data?: unknown, fetchId?: Ref, raw?: string) => void;
export type ListenerHandler<Data = unknown> = (data: Data, fetchId?: Ref) => void;
export type Listener<Data = unknown> = {
    opcode: Opcode;
    handler: ListenerHandler<Data>;
};
export type Connection = {
    close: () => void;
    once: <Data = unknown>(opcode: Opcode, handler: ListenerHandler<Data>) => void;
    addListener: <Data = unknown>(opcode: Opcode, handler: ListenerHandler<Data>) => () => void;
    user: User;
    send: (opcode: Opcode, data: unknown, topic: string, fetchId?: FetchID) => void;
    sendCall: (opcode: Opcode, data: unknown, doneOpcode?: Opcode) => Promise<unknown>;
};
/**
 * Creates a Connection object
 * @param {Token} token Your token
 * @param {Token} refreshToken Your refresh token
 * @returns {Promise<Connection>} Connection object
 */
export declare const connect: (token: Token, refreshToken: Token, { logger, onConnectionTaken, onClearTokens, url, fetchTimeout, getAuthOptions, waitToReconnect }: {
    logger?: Logger | undefined;
    onConnectionTaken?: (() => void) | undefined;
    onClearTokens?: (() => void) | undefined;
    url?: string | undefined;
    fetchTimeout?: number | undefined;
    waitToReconnect?: boolean | undefined;
    getAuthOptions?: (() => Partial<{
        token: Token;
        refreshToken: Token;
    }>) | undefined;
}) => Promise<Connection>;
