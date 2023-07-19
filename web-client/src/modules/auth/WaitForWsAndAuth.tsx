import React, { useContext, useState, useEffect } from "react";
import { WebSocketContext } from "../ws/WebSocketProvider";
import { useVerifyLoggedIn } from "./useVerifyLoggedIn";


export const WaitForWsAndAuth: React.FC<any> = ({
  children,
}) => {
  useEffect(() => {
    setDomLoaded(true);
  }, []);

  const [domLoaded, setDomLoaded] = useState(false);

  const { conn } = useContext(WebSocketContext);

  if (!useVerifyLoggedIn()) {
    return (<></>);
  }

  return (
    <>
      {domLoaded && (
       <>{conn === undefined || conn === null ? (<div className="flex">loading...</div>) : (<>{children}</>)}</>
      )}
    </>
  )
  
};