import React from "react";
import  SvgSolidSearch  from "../../icons/SolidSearch";
import { Input } from "../input";
import { Spinner } from "../Spinner";

export interface SearchBarProps
  extends React.ComponentPropsWithoutRef<"input"> {
  inputClassName?: string;
  mobile?: boolean;
  isLoading?: boolean; 
  iconClassName?: string
  iconHeight?: number
  iconWidth?: number
}

export const SearchBar: React.FC<SearchBarProps> = ({
  className = "",
  inputClassName = "",
  isLoading = false,
  mobile = false,
  iconClassName="",
  iconWidth,
  iconHeight,
  ...props
}) => {
  return (
    <div
      className={`items-center flex w-full bg-primary-700 text-primary-300 transition duration-200 ease-in-out focus-within:text-primary-100 rounded-lg ${
        mobile ? "px-4" : ""
      } ${className}`}
    >
      {!mobile && (
        <div className="h-full mx-4 flex items-center pointer-events-none">
          <SvgSolidSearch className={iconClassName} width={iconWidth} height={iconHeight} />
        </div>
      )}
      <Input
        autoFocus
        data-testid="searchbar"
        className={`${inputClassName} pl-0`}
        {...props}
      />
      {isLoading && (
        <div
          className={`h-full flex items-center pointer-events-none ${
            !mobile && "mx-4"
          }`}
        >
          <Spinner />
        </div>
      )}
    </div>
  );
};