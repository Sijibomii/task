import Announcements from "@/icons/Announcements";
import Bell from "@/icons/Bell";
import Board from "@/icons/Boards";
import Chat from "@/icons/Chat";
import CheckSquare from "@/icons/CheckSquare";
import Notes from "@/icons/Notes";
import People from "@/icons/People";
import Settings from "@/icons/Settings";
import React from "react";
import { useDashRouter } from "../../global-stores/useDashRouter"
 
export const TabletSidebar: React.FC<any> = ({}) => {
  
  const dashRouter = useDashRouter();

  return (
    <>
      <div className="border-ri h-screen mr-4">
        <div className="flex flex-col py-20 px-5">

          <CheckSquare className={`${dashRouter.current === "task" ? "text-white" : "text-[#434344]" } my-6 cursor-pointer`} width={28} height={28} onClick={() => {
            dashRouter.setCurrent("task")
          }} />

          <Chat className={`${dashRouter.current === "channel" ? "text-white" : "text-[#434344]" } my-6 cursor-pointer`} width={28} height={28} onClick={() => {
            dashRouter.setCurrent("channel")
          }} />
          
          <Bell className="text-[#434344] my-6 cursor-pointer" width={28} height={28} />
          <Notes className="text-[#434344] my-6 cursor-pointer" width={28} height={28} />
          <Board className="text-[#434344] my-6 cursor-pointer" width={28} height={28} />
          <Announcements className="text-[#434344] my-6 cursor-pointer" width={28} height={28} />
          <People className="text-[#434344] my-6 cursor-pointer" width={28} height={28} />
          <Settings className="text-[#434344] my-6 cursor-pointer" width={28} height={28} />
        </div>
      </div>
    </>
  );
};