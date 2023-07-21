import React from "react";

interface TagProps {
    children?: any
    label: string
    color: string
    text: 'xm' | 'sm' |'lg' 
}

export const Tag: React.FC<TagProps> = ({ color, label, text }) => {
  return (
    <div className={`bg-[${color}] text-${text}`}>
        <h4 className={`text-${text}`}>{label}</h4>
    </div>
  );
};
