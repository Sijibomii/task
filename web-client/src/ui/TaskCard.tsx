import Attachment from "@/icons/Attachment";
import Message from "@/icons/Message";
import ThreeDots from "@/icons/ThreeDots";
import React from "react";
import { Tag }from "./Tag"
import { MultipleUsers } from "./UserAvatar/MultipleUsers";

type tag =  {
    color: string
    label: string
}

interface TaskCardProps {
    children?: any
    tags?: tag[]
    heading: string
    imagesSrc: string[]
    comment: number
    files: number
  }


export const TaskCard: React.FC<TaskCardProps> = ({ tags, heading, imagesSrc, comment, files, children }) => {

  return (

    <div className="bg-[#1D1F21] mt-2 px-5 pt-6 pb-4 rounded-xl">
        <div className="flex items-center justify-between mb-4">
            <div className="flex items-center">
                {tags && tags.map((tag) => (
                        <Tag label={tag.label} color={tag.color} text="sm" key={tag.label} />
                    ))}
                
            </div>
            <div className="">
                <ThreeDots className="text-gray-400" />
            </div>
        </div>
        <div className="mb-3">
            <h4 className="text-lg leading-5 text-gray-100 font-light">{heading}</h4>
        </div>
        {children}
        <div className="flex items-center justify-between mt-4">
            <div className="">
                <MultipleUsers srcArray={imagesSrc} />
            </div>
            <div className="flex items-center">
                <div className="flex items-center mr-2">
                    <Message className="text-gray-400 mr-2" />
                    <p className="text-gray-400">{comment}</p>
                </div>
                <div className="flex items-center">
                    <Attachment className="text-gray-400" />
                    <p className="text-gray-400">{files}</p>
                </div>
            </div>
        </div>
    </div>  
  );
};
