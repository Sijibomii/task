import React, {  useEffect, useState } from "react";
import { useRouter } from "next/router";

import { Input } from "../../ui/input";
import {  isStaging,  __prod__, } from "../../lib/constants";
import { isServer } from "../../lib/isServer";
import Image from "next/image";
import { useTokenStore } from "../auth/useTokenStore";
import { LoginButton } from "../landing-page/LoginPage";
import SvgSolidGitHub from "../../icons/GitHub";
import SvgSolidPerson from "../../icons/Person";
import captchaPlaceholder from "../../img/captcha-example.webp";


export const SignUpPage: React.FC = () => {
    const hasTokens = useTokenStore((s) => !!(s.accessToken && s.refreshToken));
    const { push } = useRouter();
    const [tokensChecked, setTokensChecked] = useState(false);
  
    useEffect(() => {
      if (hasTokens) {
        push("/dashboard");
      } else {
        setTokensChecked(true);
      }
    }, [hasTokens, push]);
  
    const queryParams =
      isStaging && !isServer
        ? "?redirect_after_base=" + window.location.origin
        : "";
  
    if (!tokensChecked) return null;

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
          {/* <HeaderController embed={{}} title="Login" /> */}
          <div className="hidden sm:flex" />
          <div className="flex  m-auto flex-col p-6 gap-5 bg-primary-800 sm:rounded-8 z-10 sm:w-400 w-full">
            <div className="flex gap-2 flex-col text-center">
                <span className="text-3xl text-primary-100 font-bold">Welcome</span>
                <div className="text-primary-100 flex-wrap">
                    By logging in you accept our&nbsp;
                    <a
                    href="/privacy-policy.html"
                    className="text-accent hover:underline"
                    >
                    Privacy Policy
                    </a>
                    &nbsp;and&nbsp;
                    <a href="/terms.html" className="text-accent hover:underline">
                    Terms of Service
                    </a>
                    .
                </div>
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
                <div className="flex flex-col">
                    <h3 className="text-primary-100 text-sm">Password</h3>
                    <Input
                    className={``}
                    autoFocus
                    placeholder={"Enter password"}
                    // value={reason}
                    // onChange={(e) => setReason(e.target.value)}
                    />
                </div>
                <div className="flex flex-col">
                    <h3 className="text-primary-100 text-sm">Captcha</h3>
                    <div className="flex items-center justify-between">
                        <Image src={captchaPlaceholder} height={100} width={120} alt="captcha" />
                        <Input
                        className={`ml-3`}
                        autoFocus
                        placeholder={"Enter Captcha Code"}
                        // value={reason}
                        // onChange={(e) => setReason(e.target.value)}
                        />
                    </div>
                </div>
                <div className="flex items-center justify-between">
                    <a
                        href=""
                        className="text-primary-200 text-sm mt-0 underline"
                    >
                        Generate captcha
                    </a>
                
                </div>
                
                <LoginButton
                // onClick={}
                >
                <SvgSolidPerson width={20} height={20} />
                    Login 
                </LoginButton>
                <h3 className="text-sm text-center">
                    <span className="font-normal text-primary-200">have an account?</span>
                    <a
                        href=""
                        className="text-primary-200 text-sm mt-0 ml-1"
                    >
                        Sign in
                    </a>
                </h3>
            </div>
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