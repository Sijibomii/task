import React from "react";


interface ChannelDescriptionProps {
    children?:  any
    className?: string
  }

export const ChannelDescriptionController: React.FC<ChannelDescriptionProps> = ({ children, className }) => {

  return (
    <div className={`${className} `}>
        <h5 className="text-white">Hello world</h5>
    </div>
  );
};
