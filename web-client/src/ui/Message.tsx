import React from "react";
import { SingleUser } from "./UserAvatar/SingleUser";

type MessageProps = {
  avartarUrl?: string
  name: string
  time?: string
  me: boolean
  text?: string
};

export const Message: React.FC<MessageProps> = ({ name, me }) => {
  return (
   <div className={`message flex pb-5 ${me ? "flex-row-reverse":"" }`}>
        <div className={`${me ? "ml-4":"mr-4" }`}>
            <SingleUser 
            src="https://images.unsplash.com/photo-1557862921-37829c790f19?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2071&q=80" 
            isOnline 
            size="sm"
            />
        </div>
        <div className="flex flex-col">
            <div className={`flex items-center ${me ? "flex-row-reverse":"" }`}>
                <h4 className={`text-gray-200 font-light text-md ${me ? "ml-3":"mr-2" }`}>{name}</h4>
                <p className="text-white text-sm font-thin">11:25 AM</p>
            </div>

            <div className="mt-2 px-2 py-2 bg-[#222325] rounded-lg">
                <p className="text-gray-300">Hi everyone! can we get an update on the progress of the web design project?</p>
            </div>
        </div>
   </div>
  );
};
