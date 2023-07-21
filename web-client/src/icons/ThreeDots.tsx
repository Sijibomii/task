
import * as React from "react";

interface ThreeDotsProps {
    width?: number
    height?: number
    className?: string
}

const ThreeDots: React.FC<ThreeDotsProps> = ({
    width= 16, height=16, className
}) => {
    
    return (
        <svg xmlns="http://www.w3.org/2000/svg"  width={`${width}`} height={`${height}`} fill="currentColor" className={`bi bi-three-dots-vertical ${className}`} viewBox="0 0 16 16"> <path d="M9.5 13a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0zm0-5a1.5 1.5 0 1 1-3 0 1.5 1.5 0 0 1 3 0z"/> </svg>
    );
  }
export default ThreeDots;