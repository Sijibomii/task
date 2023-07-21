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
      <div className="border-ri h-screen mr-4">
        <div className="flex flex-col py-20 px-5">
          <CheckSquare className="text-white my-6" width={28} height={28} />
          <Chat className="text-[#434344] my-6" width={28} height={28} />
          <Bell className="text-[#434344] my-6" width={28} height={28} />
          <Notes className="text-[#434344] my-6" width={28} height={28} />
          <Announcements className="text-[#434344] my-6" width={28} height={28} />
          <People className="text-[#434344] my-6" width={28} height={28} />
          <Settings className="text-[#434344] my-6" width={28} height={28} />
        </div>
      </div>
    </>
  );
};