import { Connection } from "./raw";
/**
 * A wrapper object created using `wrap()` that can be used to make websocket calls using functions
 */
export type Wrapper = ReturnType<typeof wrap>;
/**
 * Creates a wrapper object that allows you to make websocket calls using functions
 * @param {Connection} connection reference to the websocket connection
 * @returns  {connection} Wrapper object
 */
export declare const wrap: (connection: Connection) => {
    connection: Connection;
    /**
     * Allows you to subscribe to various pre-defined websocket events
     */
    subscribe: {};
};
