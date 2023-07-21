import * as React from "react";

interface CaretUpProps {
    width?: number
    height?: number
    className?: string
}

const CaretUp: React.FC<CaretUpProps> = ({
  width= 16, height=16, className
}) => {

  return (
    <svg xmlns="http://www.w3.org/2000/svg" width={`${width}`} height={`${height}`} fill="currentColor" className={`bi bi-chevron-Up ${className}`} viewBox="0 0 16 16"> <path fill-rule="evenodd" d="M7.646 4.646a.5.5 0 0 1 .708 0l6 6a.5.5 0 0 1-.708.708L8 5.707l-5.646 5.647a.5.5 0 0 1-.708-.708l6-6z"/> </svg>
    );
  }
export default CaretUp;