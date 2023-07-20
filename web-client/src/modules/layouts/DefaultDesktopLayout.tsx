import React from "react";
import { WaitForWsAndAuth } from "../auth/WaitForWsAndAuth";
import { ProjectsController } from "../dashboard/ProjectsController";
import { MainLayout } from "./MainLayout";
import { TabletSidebar } from "./TabletSidebar";

interface DefaultDesktopLayoutProps {
}

export const DefaultDesktopLayout: React.FC<DefaultDesktopLayoutProps> = ({
  children,
}) => {
  return (
    <WaitForWsAndAuth>
      <MainLayout
        // tablet shows in both desktop and tab
        tabletSidebar={<TabletSidebar />}
        // left panel only on lg screens
        leftPanel={<ProjectsController />}
      >
        {children}
      </MainLayout>
    </WaitForWsAndAuth>
  );
};