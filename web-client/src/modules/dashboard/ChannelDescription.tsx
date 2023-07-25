import People from "@/icons/People";
import { SingleUser } from "@/ui/UserAvatar/SingleUser";
import React from "react";


interface ChannelDescriptionProps {
    children?:  any
    className?: string
  }

export const ChannelDescriptionController: React.FC<ChannelDescriptionProps> = ({ children, className }) => {

  return (
    <div className={`${className} px-10 pt-20 pr-28`}>
      <div className="bg-[#282b2e] py-5 px-4 rounded-lg">
        <h4 className="text-white font-normal mb-3">Description</h4>
        <p className="text-white font-thin">Main discussion all about design project (web, mobile, illustration, social media post etc). We will live, love and grow together.</p>
      </div>

      <div className="bg-[#282b2e] py-5 px-4 rounded-lg mt-8">
        <div className="flex items-center px-3">
          <People width={20} height={20} className="text-white" />
          <h3 className="text-white font-normal text-sm ml-3">Members</h3>
        </div>
        
        <div className="">
          <div className="px-3 py-3 mt-3 flex items-center">
            <div className="mr-3">
              <SingleUser 
                src="https://images.unsplash.com/photo-1557862921-37829c790f19?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2071&q=80" 
                isOnline 
                size="sm"
                />
            </div>
              <div className="">
                <h6 className="text-gray-300">Mark Roll</h6>
                <p className="text-gray-300">Creative Director</p>
              </div>
          </div>

          <div className="px-3 py-3 mt-3 flex items-center">
          <div className="mr-3">
            <SingleUser 
              src="https://images.unsplash.com/photo-1557862921-37829c790f19?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2071&q=80" 
              isOnline 
              size="sm"
              />
          </div>
            <div className="">
              <h6 className="text-gray-300">Mark Roll</h6>
              <p className="text-gray-300">Creative Director</p>
            </div>
        </div>

        <div className="px-3 py-3 mt-3 flex items-center">
          <div className="mr-3">
            <SingleUser 
              src="https://images.unsplash.com/photo-1557862921-37829c790f19?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2071&q=80" 
              isOnline 
              size="sm"
              />
          </div>
            <div className="">
              <h6 className="text-gray-300">Mark Roll</h6>
              <p className="text-gray-300">Creative Director</p>
            </div>
        </div>

        <div className="px-3 py-3 mt-3 flex items-center">
          <div className="mr-3">
            <SingleUser 
              src="https://images.unsplash.com/photo-1557862921-37829c790f19?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2071&q=80" 
              isOnline 
              size="sm"
              />
          </div>
            <div className="">
              <h6 className="text-gray-300">Mark Roll</h6>
              <p className="text-gray-300">Creative Director</p>
            </div>
        </div>

        <div className="px-3 py-3 mt-3 flex items-center">
          <div className="mr-3">
            <SingleUser 
              src="https://images.unsplash.com/photo-1557862921-37829c790f19?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2071&q=80" 
              isOnline 
              size="sm"
              />
          </div>
            <div className="">
              <h6 className="text-gray-300">Mark Roll</h6>
              <p className="text-gray-300">Creative Director</p>
            </div>
        </div>
        </div>
        
      </div>
    </div>
  );
};
