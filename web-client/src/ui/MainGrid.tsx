import React from "react";
import { useScreenType } from "../shared-hooks/useScreenType";

interface DashboardGridProps {
  className?: string;
  children?: any
}

export const MainInnerGrid: React.FC<DashboardGridProps> = ({
  children,
  className = "",
}) => {
  const screenType = useScreenType();

  let gridTemplateColumns = "20% 80%";
  let myClassName = ``;

  if (screenType === "2-cols") {
    gridTemplateColumns = "20% 80%";
  } else if (screenType === "1-cols") {
    gridTemplateColumns = "60px 640px";
  } else if (screenType === "fullscreen") {
    myClassName = "w-full px-3";
    gridTemplateColumns = "1fr";
  }

  return (
    <div 
      id="main"
      className={`relative ${myClassName} ${className} w-full`}
      style={{
        display: screenType === "fullscreen" ? "flex" : "grid",
        gridTemplateColumns,
        columnGap: 60,
      }}
    >
      {children}
    </div>
  );
};

export const MainGrid: React.FC<DashboardGridProps> = ({ children }) => {
  return (
    <div
      className={`flex justify-center w-full min-h-screen bg-primary-900`}
      data-testid="main-grid"
    >
      <MainInnerGrid>{children}</MainInnerGrid>
    </div>
  );
};