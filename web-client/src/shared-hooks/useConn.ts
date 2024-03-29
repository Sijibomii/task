import { WsWrap as wrap } from "@sijibomi/task-api-client";
import { useContext } from "react";
import { WebSocketContext } from "../modules/ws/WebSocketProvider";

export const useConn = () => {
  return useContext(WebSocketContext).conn!;
};

export const useWrappedConn = () => {
  return wrap.wrap(useContext(WebSocketContext).conn!);
};