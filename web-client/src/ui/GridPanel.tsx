import React from "react";

export const GridPanel: React.FC<any> = ({ children }) => {
  return <div className={`flex flex-col flex-1 w-full`}>{children}</div>;
};

export const FixedGridPanel: React.FC<any> = ({ children }) => {
  return (
    <div className={`flex pt-5 flex-1 sticky top-0 h-screen`}>
      {children}
    </div>
  );
};