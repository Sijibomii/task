import React, { useState } from "react";
import { Input } from "../../ui/input";
import { __prod__, } from "../../lib/constants";
import Image from "next/image";
import { useTokenStore } from "../auth/useTokenStore";
import Key from "../../icons/Key";
import { Form, Formik } from "formik";
import { LoginButton } from "../landing-page/LoginPage";
import SvgSolidGitHub from "../../icons/GitHub";
import { useHttpClient } from "../../shared-hooks/useHttpClient";
import { http } from "../../api-client";

export const LoginVerify: React.FC = () => {

    const httpClient = useHttpClient();
    const wrappedClient = http.wrap(httpClient)

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
              
                    <div className="flex  m-auto flex-col p-6 gap-5 bg-primary-800 sm:rounded-8 z-10 sm:w-400 w-full">
                    <div className="flex gap-2 flex-col text-center items-center">
                        <Key height={32} width={32} className={`text-accent text-center`} />
                        <span className="text-3xl text-primary-100 font-normal">Verify Login?</span>
                        <span className="text-md text-primary-300">Please enter the code sent to your email</span>
                    </div>
                    
                    <Formik<{
                        code: string
                    }>
                        initialValues={
                            {
                                code: ""
                            }
                        }
                
                        onSubmit={async ({ code}) => {
                            if (code.length === 0) return
                            const email = localStorage.getItem("USER_EMAIL") && localStorage.getItem("USER_EMAIL");
                            if (!email) return
                            const resp = await wrappedClient.loginVerify(code, email)
                            console.log(resp)
                        
                        }}
                        >
                    {({ isSubmitting,handleChange, handleBlur }) => (
                  <Form className={``}>

                  
                    <div className="flex flex-col gap-4">
                        <div className="flex flex-col">
                            <h3 className="text-primary-100 text-sm text-gray">Code:</h3>
                            <div className="flex">
                                <Input
                                    autoFocus
                                    placeholder={"Enter your code"}
                                    id="code"                   
                                    name="code"
                                    onBlur={handleBlur}
                                    onChange={handleChange}
                                />
                            </div>
                        </div>
                    
                        
                        <LoginButton loading={isSubmitting} type="submit">
                           <span className="text-center">Verify Login</span>
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
                    </Form>
                    )}
                    </Formik>
                </div>
                
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