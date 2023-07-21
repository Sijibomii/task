import Image from "next/image";
import React, { useState } from "react";


export const avatarSizeMap = {
  default: "80px",
  lg: "60px",
  md: "50px",
  sm: "40px",
  xs: "20px",
  xxs: "30px",
};

export const onlineIndicatorStyleMap = {
  default: {
    width: "15px",
    height: "15px",
    right: "2px",
    bottom: "-4px",
    borderWidth: "4px",
  },
  lg: {
    width: "12px",
    height: "12px",
    right: "2px",
    bottom: "-2px",
    borderWidth: "2px",
  },
  md: {
    width: "10px",
    height: "10px",
    right: "2px",
    bottom: "-2px",
    borderWidth: "2px",
  },
  sm: {
    width: "8px",
    height: "8px",
    right: "2px",
    bottom: "-2px",
    borderWidth: "2px",
  },
  xs: {
    width: "4px",
    height: "4px",
    right: "0px",
    bottom: "-1px",
    borderWidth: "1px",
  },
  xxs: {
    width: "6px",
    height: "6px",
    right: "1px",
    bottom: "-1px",
    borderWidth: "1px",
  },
};

export interface AvatarProps {
  src: string;
  size?: keyof typeof onlineIndicatorStyleMap;
  className?: string;
  isOnline?: boolean;
  hover?: boolean;
}

export const SingleUser: React.FC<AvatarProps> = ({
  src,
  size = "default",
  className = "",
  isOnline = false,
  hover = false,

}) => {
  const [isError, setError] = useState(false);
  const sizeStyle = onlineIndicatorStyleMap[size];
  return (
    <div
      className={`relative inline-block ${className}`}
      style={{
        width: avatarSizeMap[size],
        height: avatarSizeMap[size],
      }}
      data-testid="single-user-avatar"
    >
      <Image
        alt={`s-avatar` }
        width={10}
        height={10}
        className={`rounded-full w-full h-full object-cover`}
        onError={() => setError(true)}
        src={
          isError
            ? `https://ui-avatars.com/api/&name=john&rounded=true&background=B23439&bold=true&color=FFFFFF`
            :src
        }
      />
      {hover && (
        <div
          className={`bg-primary-900 hover:opacity-20 transition duration-200 opacity-0 absolute w-full h-full top-0 left-0 rounded-full`}
        ></div>
      )}
      {isOnline && (
        <span
          className={
            "rounded-full absolute box-content bg-accent border-primary-800"
          }
          style={sizeStyle}
          data-testid="online-indictor"
        ></span>
      )}
    </div>
  );
};