// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck because internet is unpredictable
/**
 * Creates a wrapper object that allows you to make websocket calls using functions
 * @param {Connection} connection reference to the websocket connection
 * @returns  {connection} Wrapper object
 */
export var wrap = function (connection) { return ({
    connection: connection,
    /**
     * Allows you to subscribe to various pre-defined websocket events
     */
    subscribe: {}
}); };
