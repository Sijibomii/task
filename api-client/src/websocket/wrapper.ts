// eslint-disable-next-line @typescript-eslint/ban-ts-comment
// @ts-nocheck because internet is unpredictable

import { Connection } from "./raw";

  /**
   * Allows you to handle custom logic on websocket events
   */
  type Handler<Data> = (data: Data) => void;
  
  /**
   * A wrapper object created using `wrap()` that can be used to make websocket calls using functions
   */
  export type Wrapper = ReturnType<typeof wrap>;
  
  /**
   * Creates a wrapper object that allows you to make websocket calls using functions
   * @param {Connection} connection reference to the websocket connection
   * @returns  {connection} Wrapper object
   */
  export const wrap = (connection: Connection) => ({
    connection,
  
    /**
     * Allows you to subscribe to various pre-defined websocket events
     */
    subscribe: {
      
    }
  });