import router from "next/router";
import React from "react";
import { TabletSidebar } from "./TabletSidebar";
import { useScreenType } from "../../shared-hooks/useScreenType";
import { useConn } from "../../shared-hooks/useConn";
import { useHostStore } from "../../global-stores/useHostStore";
import { MainInnerGrid } from "../../ui/MainGrid";
import { LeftPanel, RightPanel } from "./GridPanels";


interface MainLayoutProps {
  tabletSidebar?: React.ReactNode;
  leftPanel?: React.ReactNode;
  rightPanel?: React.ReactNode;
  children?: any;
}

export const MainLayout: React.FC<MainLayoutProps> = ({
  children,
  leftPanel = <div />,
  tabletSidebar = <TabletSidebar />,
}) => {
   
  const screenType = useScreenType();
  const conn = useConn()!;
  const me = conn ? conn.user : undefined;
  

  const items: any[] = [
    // { icon: <SolidHome />, targetPath: "/dash" },
    // { icon: <SolidCalendar />, targetPath: "/scheduled-rooms" },
  ];


  let middle = null;
  let prepend = null;

  switch (screenType) {
    case "3-cols":
      middle = (
        <>
          <LeftPanel className="flex">
            {tabletSidebar}
            {leftPanel}
          </LeftPanel>
          {children}
          
        </>
      );
      break;
    case "2-cols":
      middle = (
        <>
          <LeftPanel>{tabletSidebar}</LeftPanel>
          {children}
        </>
      );
      break;
    case "1-cols":
      middle = (
        <>
          <LeftPanel>{tabletSidebar}</LeftPanel>
          {children}
        </>
      );
      break;
    case "fullscreen":
      prepend = (
        <>
        </>
      );
      middle = (
        <>
          {children}
        </>
      );
  }

  

  return (
    <>
      <div
        className={`fixed left-0 w-full z-10`}>
        {prepend}
      </div>
      <div
        className={
         !useHostStore.getState().isLinux
            ? `default-desktop-layout flex flex-col items-center w-full scrollbar-thin scrollbar-thumb-primary-700 ${
                prepend ? "mb-7" : ""
              }`
            : `flex flex-col items-center w-full scrollbar-thin scrollbar-thumb-primary-700 ${
                prepend ? "mt-8 mb-7" : ""
              }`
        }
      >
        <MainInnerGrid>{middle}</MainInnerGrid>
      </div>
    </>
  );
};