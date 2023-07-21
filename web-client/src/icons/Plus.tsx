
import * as React from "react";

interface PlusProps {
    width?: number
    height?: number
    className?: string
}

const Plus: React.FC<PlusProps> = ({
    width= 16, height=16, className
}) => {
    
    return (
        <svg xmlns="http://www.w3.org/2000/svg" width={`${width}`} height={`${height}`} fill="currentColor" className={`bi bi-plus-lg ${className}`}  viewBox="0 0 16 16"> <path fill-rule="evenodd" d="M8 2a.5.5 0 0 1 .5.5v5h5a.5.5 0 0 1 0 1h-5v5a.5.5 0 0 1-1 0v-5h-5a.5.5 0 0 1 0-1h5v-5A.5.5 0 0 1 8 2Z"/> </svg>
    );
  }
export default Plus;