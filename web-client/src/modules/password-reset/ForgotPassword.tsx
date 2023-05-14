import React, { useCallback, useContext, useEffect, useState } from "react";
import { useRouter } from "next/router";
import { Button } from "../../ui/Button";
import { Input } from "../../ui/input";
import { apiBaseUrl, isStaging, loginNextPathKey, __prod__, } from "../../lib/constants";
import { isServer } from "../../lib/isServer";
import Image from "next/image";
import { useTokenStore } from "../auth/useTokenStore";
import Key from "../../icons/Key";
import Email from "../../icons/Email";
import { LoginButton } from "../landing-page/LoginPage";
import captchaPlaceholder from "../../img/captcha-example.webp";
import SvgSolidGitHub from "../../icons/GitHub";

export const ForgotPassword: React.FC = () => {

    const [submitted, setSubmitted] = useState(true);

    return (
        <>
             <div className="flex">
                </div>
                <div
                className="grid w-full h-full"
                style={{
                    gridTemplateRows: "1fr auto 1fr",
                }}
                >
                <div className="hidden sm:flex" />
                {!submitted ? (
                    <div className="flex  m-auto flex-col p-6 gap-5 bg-primary-800 sm:rounded-8 z-10 sm:w-400 w-full">
                    <div className="flex gap-2 flex-col text-center items-center">
                        <Key height={32} width={32} className={`text-accent text-center`} />
                        <span className="text-3xl text-primary-100 font-normal">Forgot Password?</span>
                        <span className="text-md text-primary-300">No worries, we will send you reset instructions</span>
                    </div>
                    
                    <div className="flex flex-col gap-4">
                        <div className="flex flex-col">
                            <h3 className="text-primary-100 text-sm text-gray">Email:</h3>
                            <div className="flex">
                                <Input
                                autoFocus
                                placeholder={"Enter your Email"}
                                // value={username}
                                // onChange={(e) => setUsername(e.target.value)}
                                />
                            </div>
                        </div>
                    
                        
                        <LoginButton
                            
                            // onClick={}
                            >
                           <span className="text-center">Reset password</span>
                        </LoginButton>
                        <h3 className="text-sm text-center">
                            {/* back svg */}
                            <span className="font-normal text-primary-200">Back to</span>
                            <a
                                href=""
                                className="text-primary-200 text-sm mt-0 ml-1"
                            >
                                Log in
                            </a>
                        </h3>
                    </div>
                </div>
                ):(
                    <div className="flex  m-auto flex-col p-6 gap-5 bg-primary-800 sm:rounded-8 z-10 sm:w-400 w-full">
                    <div className="flex gap-2 flex-col text-center items-center">
                        <Email height={32} width={32} className={`text-accent text-center`} />
                        <span className="text-3xl text-primary-100 font-normal">Check your email</span>
                        <span className="text-md text-primary-300">We sent a password link to you</span>
                    </div>
                    
                    <div className="flex flex-col gap-4">
                        <h3 className="text-sm text-center">
                            {/* back svg */}
                            <span className="font-normal text-primary-200">Back to</span>
                            <a
                                href=""
                                className="text-primary-200 text-sm mt-0 ml-1"
                            >
                                Log in
                            </a>
                        </h3>
                    </div>
                </div>
                )}
                <div className="flex flex-row absolute bottom-0 w-full justify-between px-5 py-5 mt-auto items-center sm:px-7">
                    <div className="hidden sm:flex">
                    {/* <LgLogo /> */}
                    </div>
                    <div className="flex flex-row gap-6 text-primary-300">
                    <a href="mail:to" className="hover:text-primary-200">
                        sijibomiolajubu@gmail.com
                    </a>
                    <a
                        href="https://github.com/sijibomii/task/issues"
                        className="ml-2 hover:text-primary-200"
                    >
                        Report a bug
                    </a>
                    <div className="flex flex-row gap-6 sm:gap-4">
                        <a
                        href="https://github.com/sijibomii/task"
                        target="_blank"
                        rel="noreferrer"
                        >
                        <SvgSolidGitHub
                            width={20}
                            height={20}
                            className="ml-2 cursor-pointer hover:text-primary-200"
                        />
                        </a>
                    </div>
                    </div>
                </div>
            </div>
        </>
    )
}