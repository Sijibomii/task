import React, { ButtonHTMLAttributes, DetailedHTMLProps, ReactNode,} from "react";
import { Spinner } from "./Spinner";

const sizeClassnames = {
    big: "py-2 px-6 text-sm rounded-lg",
    small: "px-2 py-1 text-sm rounded-md",
    tiny: "px-1 text-sm rounded-5",
};

const colorClassnames = {
    primary:
        "text-[#fff] bg-[#fd4d4d] transition duration-200 ease-in-out hover:bg-[#fd6868] disabled:text-[#f5bfbf] disabled:bg-[#fd6868]",
    secondary:
        "text-[#fff] bg-[#242c37] hover:bg-[#323d4d] disabled:text-[#5d7290]",
    "secondary-800":
        "text-[#fff] bg-primary-800 hover:bg-[#323d4d] disabled:text-[#5d7290]",
    "primary-300":
        "text-[#fff] bg-[#242c37] hover:bg-[#323d4d] disabled:text-[#5d7290]",
    transparent: "text-[#fff] bg-transparent",
    "accent-secondary":
        "text-[#fff] bg-[#5575e7] hover:bg-[#879eed] disabled:text-[#879eed]",
};
// DetailedHTMLProps is a TypeScript type that represents all the possible props that a HTML element can have, including the standard HTML attributes, events, and the element-specific attributes.
export type ButtonProps = DetailedHTMLProps<ButtonHTMLAttributes<HTMLButtonElement>, HTMLButtonElement> & {
    size?: keyof typeof sizeClassnames;
    color?: keyof typeof colorClassnames;
    loading?: boolean;
    icon?: ReactNode;
    transition?: boolean;
};

export const Button: React.FC<ButtonProps> = ({
    children,
    size = "big",
    color = "primary",
    disabled,
    loading,
    icon,
    className = "",
    transition,
    ...props
    }) => {
    return (
        <button
        disabled={disabled || loading}
        className={`flex outline-none focus:ring-4 focus:ring-${color} ${sizeClassnames[size]
            } ${transition ? `transition duration-200 ease-in-out` : ``} ${colorClassnames[color]
            } font-bold flex items-center justify-center ${className}`}
        data-testid="button"
        {...props}
        >
        <span className={loading ? "opacity-0" : `flex items-center`}>
            {icon ? <span className={`mr-2 items-center`}>{icon}</span> : null}
            {children}
        </span>
        {loading ? (
            <span className={`absolute`}>
                <Spinner size={size === "small" ? "2" : "4"} />
            </span>
        ) : null}
        </button>
    );
};
