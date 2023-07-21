import React, { useState } from "react";
import Email from "@/icons/Email";
import { SearchBar } from "@/ui/search/SearchBar";
import CaretDown from "@/icons/CaretDown";
import CaretUp from "@/icons/CaretUp";
import Star from "@/icons/Star";
import Square from "@/icons/Square";
import Circle from "@/icons/Circle";
import Triangle from "@/icons/Triangle";
import Plus from "@/icons/Plus";

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
      <div className="py-6 flex flex-col">
        <div className="search-con">
          <SearchBar iconClassName="text-[#434344]"  placeholder="Search..." iconHeight={20} iconWidth={20} inputClassName="placeholder:text-xl text-[#434344] text-xl" />
        </div>

        {/* favourites */}
        <div className="mt-10">
          <div className="flex items-center">
            <CaretDown className="text-[#434344]"/>
            <h4 className="uppercase text-[#434344] ml-4 text-sm">favourites</h4>
          </div>

          <div className="">
            <div className="flex items-center py-3 px-4 mt-3">
              <Star className="text-[yellow]" />
              <h4 className="text-[#434344] ml-4 text-sm">Mirage</h4>
            </div>

            <div className="flex items-center py-3 px-4">
              <Square className="text-[purple]" width={12} height={12} />
              <h4 className="text-[#434344] ml-4 text-sm">Mashroom</h4>
            </div>

            <div className="flex items-center py-3 px-4">
              <Circle className="text-[violet]" width={12} height={12} />
              <h4 className="text-[#434344] ml-4 text-sm">Weedo</h4>
            </div>

            <div className="flex items-center py-3 px-4">
              <Triangle className="text-[green]" width={12} height={12} />
              <h4 className="text-[#434344] ml-4 text-sm">Lonely walls</h4>
            </div>
          </div>
        </div>

        {/* all projects */}
        <div className="mt-10 ">
          <div className="flex items-center">
            <CaretDown className="text-[#434344]"/>
            <h4 className="uppercase text-[#434344] ml-4 text-sm">all projects</h4>
          </div>

          <div className="">
          <div className="flex items-center py-3 px-4 mt-3 prj-active">
              <Star className="text-[yellow]" />
              <h4 className="text-[#434344] ml-4 text-sm">Essential Bottle</h4>

              {/* notification */}
              <div className="bg-[#F85058] ml-4 prj-not">
                <div className=" text-white">3</div>
              </div>
            </div>

            <div className="flex items-center py-3 px-4">
              <Square className="text-[purple]" width={12} height={12} />
              <h4 className="text-[#434344] ml-4 text-sm">New Shteti</h4>
            </div>

            <div className="flex items-center py-3 px-4">
              <Circle className="text-[violet]" width={12} height={12} />
              <h4 className="text-[#434344] ml-4 text-sm">Nintendo</h4>
            </div>

            <div className="flex items-center py-3 px-4">
              <Triangle className="text-[green]" width={12} height={12} />
              <h4 className="text-[#434344] ml-4 text-sm">Lonely walls</h4>
            </div>

            <div className="flex items-center py-3 px-4">
              <Square className="text-[purple]" width={12} height={12} />
              <h4 className="text-[#434344] ml-4 text-sm">Mashroom</h4>
            </div>

            <div className="flex items-center py-3 px-4">
              <Circle className="text-[violet]" width={12} height={12} />
              <h4 className="text-[#434344] ml-4 text-sm">Weedo</h4>
            </div>

            <div className="flex items-center py-3 px-4">
              <Triangle className="text-[green]" width={12} height={12} />
              <h4 className="text-[#434344] ml-4 text-sm">Lonely walls</h4>
            </div>
          </div>
        </div>

        {/* archieve */}
        <div className="mt-10">
          <div className="flex items-center">
            <CaretUp className="text-[#434344]"/>
            <h4 className="uppercase text-[#434344] ml-4 text-sm">archive</h4>
          </div>

          <div className="">

          </div>
        </div>

        <div className="absolute bottom-4 bg-[#17191C] py-2 px-10 flex items-center justify-center rounded-2xl">
          <Plus className="text-white mr-3" />
          <h5 className="text-white">New Project</h5>
        </div>
      </div>
    </>
  );
};