import router from "next/router";
import React from "react";
import { TabletSidebar } from "./TabletSidebar";

interface MainLayoutProps {
  tabletSidebar?: React.ReactNode;
  leftPanel?: React.ReactNode;
  rightPanel?: React.ReactNode;
  children?: any;
}

export const MainLayout: React.FC<MainLayoutProps> = ({
  children,
  leftPanel = <div />,
  rightPanel = <div />,
  tabletSidebar = <TabletSidebar />,
}) => {
 

  

  return (
    <>
      
    </>
  );
};