import React from "react";

interface TagProps {
    children?: any
    label: string
    color: string
    text: 'xm' | 'sm' |'lg' 
}

export const Tag: React.FC<TagProps> = ({ color, label, text }) => {
  return (
    <div className={`text-${text} mr-4 px-2 rounded-xl`} style={
      {backgroundColor: color}
    }>
        <h4 className={`text-${text}`}>{label}</h4>
    </div>
  );
};
