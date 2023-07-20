import React, { FC, useContext } from "react";
import { useScreenType } from "../../shared-hooks/useScreenType";
import { FixedGridPanel, GridPanel } from "../../ui/GridPanel";
import { WebSocketContext } from "../ws/WebSocketProvider";

interface LeftPanelProps {}

const HeaderWrapper: FC<any> = ({ children }) => (
  <div className={`flex mb-7 h-6 items-center`}>{children}</div>
);

export const LeftPanel: React.FC<LeftPanelProps> = ({ children }) => {
  return (
    <FixedGridPanel>
      {children}
    </FixedGridPanel>
  );
};
 
export const MiddlePanel: React.FC<
  LeftPanelProps & { stickyChildren?: React.ReactNode }
> = ({ stickyChildren, children }) => {
  const screenType = useScreenType();
  return (
    <GridPanel>
      <div
        className={
          !(screenType === "fullscreen" && !stickyChildren)
            ? `flex sticky w-full flex-col z-10 bg-primary-900 pt-5`
            : ""
        }>
        {screenType !== "fullscreen" ? (
          <HeaderWrapper>
           
          </HeaderWrapper>
        ) : (
          ""
        )}
        {stickyChildren}
      </div>
      {children}
    </GridPanel>
  );
};

export const RightPanel: React.FC<LeftPanelProps> = ({ children }) => {
  const { conn } = useContext(WebSocketContext);
  return (
    <FixedGridPanel>
      <HeaderWrapper>

      </HeaderWrapper>
      {children}
    </FixedGridPanel>
  );
};