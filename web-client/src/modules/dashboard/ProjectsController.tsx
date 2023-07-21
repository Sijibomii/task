import React, { useState } from "react";
import Email from "@/icons/Email";
import { SearchBar } from "@/ui/search/SearchBar";
import CaretDown from "@/icons/CaretDown";
import CaretUp from "@/icons/CaretUp";

const Page: React.FC<{}> = () => {
  

  return (
    <>
      
    </>
  );
};

const Sidebar: React.FC<{}> = () => {
  

  return (
    <>
      <div className="pb-5 w-full flex flex-col flex-1 overflow-y-auto">
        <div className="icons">
            <Email />
        </div>
      </div>
    </>
  );
};


export const ProjectsController: React.FC<any> = ({}) => {

  return (
    <>
      <div className="py-6">
        <div className="search-con">
          <SearchBar iconClassName="text-[#434344]"  placeholder="Search..." iconHeight={20} iconWidth={20} inputClassName="placeholder:text-xl text-[#434344] text-xl" />
        </div>

        {/* favourites */}
        <div className="mt-10 px-4">
          <div className="flex items-center">
            <CaretDown className="text-[#434344]"/>
            <h4 className="uppercase text-[#434344] ml-4 text-sm">favourites</h4>
          </div>

          <div className="">
            
          </div>
        </div>

        {/* all projects */}
        <div className="mt-10 px-4">
          <div className="flex items-center">
            <CaretDown className="text-[#434344]"/>
            <h4 className="uppercase text-[#434344] ml-4 text-sm">all projects</h4>
          </div>

          <div className="">

          </div>
        </div>

        {/* archieve */}
        <div className="mt-10 px-4">
          <div className="flex items-center">
            <CaretUp className="text-[#434344]"/>
            <h4 className="uppercase text-[#434344] ml-4 text-sm">all projects</h4>
          </div>

          <div className="">

          </div>
        </div>
      </div>
    </>
  );
};