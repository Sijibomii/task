import React from "react";

interface InputErrorMsgProps  extends React.ComponentPropsWithoutRef<"input">  {}


export const InputErrorMsg: React.FC<InputErrorMsgProps> = ({ children }) => {
  return (
    <div className={`flex text-secondary`} data-testid="input-error-msg">
      {children}
    </div>
  );
};
