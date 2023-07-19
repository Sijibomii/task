"use strict";
// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck because internet is unpredictable
Object.defineProperty(exports, "__esModule", { value: true });
exports.wrap = void 0;
/**
 * Creates a wrapper object that allows you to make websocket calls using functions
 * @param {Connection} connection reference to the websocket connection
 * @returns  {connection} Wrapper object
 */
var wrap = function (connection) { return ({
    connection: connection,
    /**
     * Allows you to subscribe to various pre-defined websocket events
     */
    subscribe: {}
}); };
exports.wrap = wrap;
