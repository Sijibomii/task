import * as React from "react";

interface ThreeHorizontalDotsProps {
    width?: number
    height?: number
    className?: string
}

const ThreeHorizontalDots: React.FC<ThreeHorizontalDotsProps> = ({
    width= 16, height=16, className
}) => {
    
    return (
        <svg xmlns="http://www.w3.org/2000/svg" width={`${width}`} height={`${height}`} fill="currentColor" className={`bi bi-three-dots ${className}`} viewBox="0 0 16 16"> <path d="M3 9.5a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3zm5 0a1.5 1.5 0 1 1 0-3 1.5 1.5 0 0 1 0 3z"/> </svg>
    );
  }
export default ThreeHorizontalDots;