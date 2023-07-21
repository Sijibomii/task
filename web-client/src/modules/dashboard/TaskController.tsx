import SvgSolidSearch from "@/icons/SolidSearch";
import Star from "@/icons/Star";
import ThreeDots from "@/icons/ThreeDots";
import { MultipleUsers } from "@/ui/UserAvatar/MultipleUsers";
import React, { useState } from "react";

const Page: React.FC<{}> = () => {
  

  return (
    <>
      
    </>
  );
};

export const TaskController: React.FC<any> = ({}) => {

  const srcArr = ['https://images.unsplash.com/photo-1480429370139-e0132c086e2a?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=988&q=80',
  'https://images.unsplash.com/photo-1557862921-37829c790f19?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=2071&q=80',
  'https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=987&q=80',
  'https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=987&q=80',
  'https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=987&q=80',
'https://images.unsplash.com/photo-1539571696357-5a69c17a67c6?ixlib=rb-4.0.3&ixid=M3wxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHx8fA%3D%3D&auto=format&fit=crop&w=987&q=80']

  return (
    <div className="bg-[#101214] w-full h-full  py-10">
      {/* Header */}
      <div className="px-12">
        <div className="flex items-center justify-between w-full">
          <div className="flex items-center">
            <Star className="text-[yellow]" width={20} height={20} />
            <h4 className="text-white ml-4 text-3xl font-semibold">Essential Bottle</h4>
          </div>
          <div className=" flex items center pr-10">
            <SvgSolidSearch className="text-white mr-8" width={18} height={18} />
            <Star className="text-white mr-8" width={18} height={18} />
            <ThreeDots className="text-white" width={18} height={18} />
            
          </div>
        </div>
        <div className="flex items-center ml-10 py-3">
          <div className="flex items-center mr-3">
            <Star className="text-[#434344] mr-2" width={12} height={12}  />
            <h4 className="text-[#434344] text-sm">Website</h4>
          </div>
          <p className="text-[#434344] text-sm">/</p>
          <div className="flex items-center mr-3 ml-3">
            <Star className="text-[#434344] mr-2" width={12} height={12} />
            <h4 className="text-[#434344] text-sm">IOS app</h4>
          </div>
          <p className="text-[#434344] text-sm">/</p>
          <div className="flex items-center mr-3 ml-3">
            <Star className="text-[#434344] mr-2" width={12} height={12} />
            <h4 className="text-[#434344] text-sm">Dribble shot</h4>
          </div>
        </div>
      </div>

      {/* header */}
      <div className="">
        <div className="">
          <div className="">
            <h4>Discussion</h4>
            {/* not */}
            <div className="bg-[#F85058] ml-4 prj-not absolute right-3">
              <div className=" text-white">3</div>
            </div>
          </div>
          <div className="">
            <h4>Tasks</h4>
          </div>
          <div className="">
            <h4>Timelines</h4>
          </div>

          <div className="">
            <h4>Overview</h4>
          </div>
        </div>
        <div className="">
          <MultipleUsers  srcArray={srcArr}/>
        </div>
      </div>
    </div>
  );
};