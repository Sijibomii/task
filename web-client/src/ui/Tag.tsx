import React, { ReactNode } from "react";

interface TagProps {
    children?: ReactNode
    label: string
    labelClass?: string
    color: string
    text: 'xm' | 'sm' |'lg' | 'md'
}

export const Tag: React.FC<TagProps> = ({ color, label, text, children, labelClass }) => {
  return (
    <div className={`text-${text} mr-4 px-2 rounded-xl`} style={
      {backgroundColor: color}
    }>
        {children ? (
          <>
            {children}
          </>
        ): 
        <h4 className={`text-${text} ${labelClass}`}>{label}</h4>
        }
    </div>
  );
};
