import Announcements from "@/icons/Announcements";
import Bell from "@/icons/Bell";
import Chat from "@/icons/Chat";
import CheckSquare from "@/icons/CheckSquare";
import Notes from "@/icons/Notes";
import People from "@/icons/People";
import Settings from "@/icons/Settings";
import React, { useState } from "react";
 
export const TabletSidebar: React.FC<any> = ({}) => {
  

  return (
    <>
      <div className="border-ri h-screen">
        <div className="flex flex-col py-4 px-5">
          <CheckSquare className="text-white my-6" width={32} height={32} />
          <Chat className="text-white my-6" width={32} height={32} />
          <Bell className="text-white my-6" width={32} height={32} />
          <Notes className="text-white my-6" width={32} height={32} />
          <Announcements className="text-white my-6" width={32} height={32} />
          <People className="text-white my-6" width={32} height={32} />
          <Settings className="text-white my-6" width={32} height={32} />
        </div>
      </div>
    </>
  );
};