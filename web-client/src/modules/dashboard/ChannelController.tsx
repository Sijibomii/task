import People from "@/icons/People";
import PersonPlus from "@/icons/PersonPlus";
import SvgSolidSearch from "@/icons/SolidSearch";
import Telephone from "@/icons/Telephone";
import ThreeHorizontalDots from "@/icons/ThreeDotsHor";
import Video from "@/icons/Video";
import { Message } from "@/ui/Message";
import { Tag } from "@/ui/Tag";
import React from "react";
import { ChannelDescriptionController } from "./ChannelDescription";



interface ChannelProps {
    children?: any
    
  }

export const ChannelController: React.FC<ChannelProps> = ({ children }) => {

  return (
    <div className="flex bg-[#101214]">
        <div className="basis-4/6 py-4 ">
            {/* header */}
            <div className="pl-10 channel-header pb-2">
                <div className="flex items-center justify-between">
                    <div className="flex items-center">
                        <h3 className="text-white mr-6 font-light">Desginer</h3>
                        <Tag color="#E5DCFD" label="Essential bottle" text="sm" labelClass="font-light" />     
                    </div>

                    <div className="flex items-center">
                        <SvgSolidSearch className="text-white mr-6"  width={24} height={24} />
                        <PersonPlus className="text-white mr-6"  width={24} height={24} />
                        <Telephone className="text-white mr-6"  width={24} height={24} />
                        <Video className="text-white mr-6"  width={24} height={24} />
                        <ThreeHorizontalDots className="text-white mr-6"  width={24} height={24} />
                    </div>
                </div>

                <div className="flex items-center">
                    <People className="text-white mr-2"  width={12} height={12} />
                    <h6 className="text-white">10 Members</h6>
                </div>
            </div>

            <div className="py-8 px-4">
                <Message name="Ben Ten" me={false} />
                <Message name="Ben Ken" me={false} />
                <Message name="Ben sssn" me={false} />
                <Message name="Ben Ten" me={true} />
                <Message name="Ben Ten" me={false} />
                <Message name="Ben Ten" me={false} />

            </div>
        </div>

        <ChannelDescriptionController className="basis-2/6 py-4 bg-[#080808]" />
    </div>
  );
};
