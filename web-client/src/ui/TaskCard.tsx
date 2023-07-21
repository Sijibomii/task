import Attachment from "@/icons/Attachment";
import Message from "@/icons/Message";
import ThreeDots from "@/icons/ThreeDots";
import React from "react";
import { Tag }from "./Tag"
import { MultipleUsers } from "./UserAvatar/MultipleUsers";

interface TaskCardProps {
    children?: any
    tags?: string[]
    heading: string
    imagesSrc: string[]
    comment: number
    files: number
  }


export const TaskCard: React.FC<TaskCardProps> = ({ tags, heading, imagesSrc, comment, files, children }) => {
  return (

    <div className="bg-[#1D1F21] mt-2 px-2 py-2">
        <div className="">
            <div className="">
                <Tag label="Website" color="#B4D6FE" text="sm" />
                <Tag label="Design" color="#E9FE90" text="sm" />  
            </div>
            <div className="">
                <ThreeDots />
            </div>
        </div>
        <div className="">
            <h4 className="">Pages About and carrers</h4>
        </div>
        {children}
        <div className="">
            <div className="">
                <MultipleUsers />
            </div>
            <div className="">
                <Message />
                <Attachment />
            </div>
        </div>
    </div>  
  );
};
