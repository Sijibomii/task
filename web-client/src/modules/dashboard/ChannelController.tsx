import People from "@/icons/People";
import PersonPlus from "@/icons/PersonPlus";
import SvgSolidSearch from "@/icons/SolidSearch";
import Telephone from "@/icons/Telephone";
import ThreeHorizontalDots from "@/icons/ThreeDotsHor";
import Video from "@/icons/Video";
import { Tag } from "@/ui/Tag";
import React from "react";
import { ChannelDescriptionController } from "./ChannelDescription";



interface ChannelProps {
    children?: any
    
  }

export const ChannelController: React.FC<ChannelProps> = ({ children }) => {

  return (
    <div className="flex py-4">
        <div className="px-10 basis-4/6">
            {/* header */}
            <div className="">
                <div className="flex items-center justify-between">
                    <div className="flex items-center">
                        <h3>Desginer</h3>
                        <Tag color="#E5DCFD" label="Essential bottle" text="lg"  />
                    </div>

                    <div className="flex items-center pr-12">
                        <SvgSolidSearch className="text-[#434344]" />
                        <PersonPlus className="text-[#434344]" />
                        <Telephone className="text-[#434344]" />
                        <Video className="text-[#434344]" />
                        <ThreeHorizontalDots className="text-[#434344]" />
                    </div>
                </div>

                <div className="">
                    <People />
                    <h6 className="text-white">10 Members</h6>
                </div>
            </div>
        </div>

        <ChannelDescriptionController className="basis-2/6" />
    </div>
  );
};
