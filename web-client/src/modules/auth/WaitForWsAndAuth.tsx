import React, { useContext } from "react";
import { WebSocketContext } from "../ws/WebSocketProvider";
import { useVerifyLoggedIn } from "./useVerifyLoggedIn";

interface WaitForWsAndAuthProps {}

export const WaitForWsAndAuth: React.FC<WaitForWsAndAuthProps> = ({
  children,
}) => {
  const { conn } = useContext(WebSocketContext);

  if (!useVerifyLoggedIn()) {
    return (<></>);
  }

  return (<>
  
  {conn === undefined || conn === null ? (<div className="flex">loading...</div>) : (<>{children}</>)}
  </>)
};