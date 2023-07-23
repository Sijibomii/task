import People from "@/icons/People";
import PersonPlus from "@/icons/PersonPlus";
import SvgSolidSearch from "@/icons/SolidSearch";
import Telephone from "@/icons/Telephone";
import ThreeHorizontalDots from "@/icons/ThreeDotsHor";
import Video from "@/icons/Video";
import { Tag } from "@/ui/Tag";
import React from "react";



interface ChannelProps {
    children?: any
    
  }


export const ChannelController: React.FC<ChannelProps> = ({ children }) => {

  return (
    <div className="">
        <div className="">
            {/* header */}
            <div className="">
                <div className="">
                    <div className="">
                        <h3>Desginer</h3>
                        <Tag />
                    </div>

                    <div className="">
                        <SvgSolidSearch />
                        <PersonPlus />
                        <Telephone />
                        <Video />
                        <ThreeHorizontalDots />
                    </div>
                </div>

                <div className="">
                    <People />
                    <h6>10 Members</h6>
                </div>
            </div>
        </div>
    </div>
  );
};
