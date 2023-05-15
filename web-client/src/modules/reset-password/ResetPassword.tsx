import React, { useCallback, useContext, useEffect, useState } from "react";
import Key from "../../icons/Key";
import { Input } from "../../ui/input";
import { LoginButton } from "../landing-page/LoginPage";
import SvgSolidGitHub from "../../icons/GitHub";
import CheckCircle from "../../icons/CheckCircle";

export const ResetPassword: React.FC = () => {
    
    const [success, setSuccess] = useState(true);

    return(
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
                {!success ? (
                    <div className="flex  m-auto flex-col p-6 gap-5 bg-primary-800 sm:rounded-8 z-10 sm:w-400 w-full">
                    <div className="flex gap-2 flex-col text-center items-center">
                        <Key height={32} width={32} className={`text-accent text-center`} />
                        <span className="text-3xl text-primary-100 font-normal">Set new password</span>
                    </div>
                    
                    <div className="flex flex-col gap-4">
                        <div className="flex flex-col">
                            <h3 className="text-primary-100 text-sm text-gray">Password:</h3>
                            <div className="flex">
                                <Input
                                autoFocus
                                placeholder={"Enter your new password"}
                                // value={username}
                                // onChange={(e) => setUsername(e.target.value)}
                                />
                            </div>
                        </div>

                        <div className="flex flex-col">
                            <h3 className="text-primary-100 text-sm text-gray">Confirm Password:</h3>
                            <div className="flex">
                                <Input
                                autoFocus
                                placeholder={"conform your new password"}
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
                        <CheckCircle height={32} width={32} className={`text-accent text-center`} />
                        <span className="text-3xl text-primary-100 font-normal">Password rest</span>
                       
                    </div>
                    
                    <div className="flex flex-col gap-4">
                        <LoginButton
                                
                                // onClick={}
                                >
                            <span className="text-center">Continue</span>
                        </LoginButton>
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