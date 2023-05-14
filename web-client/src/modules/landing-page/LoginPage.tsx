import React, { useCallback, useContext, useEffect, useState } from "react";
import { useRouter } from "next/router";
import { Button } from "../../ui/Button";
import { Input } from "../../ui/input";
import { LgLogo } from "../../icons";
import { apiBaseUrl, isStaging, loginNextPathKey, __prod__, } from "../../lib/constants";
import { isServer } from "../../lib/isServer";
// import { useSaveTokensFromQueryParams } from "../auth/useSaveTokensFromQueryParams";
import { useTokenStore } from "../auth/useTokenStore";
import SvgSolidGoogle from "../../icons/SolidGoogle";
import SvgSolidGitHub from "../../icons/GitHub";
import SvgSolidPerson from "../../icons/Person";
interface LoginButtonProps {
    children: [React.ReactNode, React.ReactNode];
    dev?: true;
    onClick?: () => void;
    oauthUrl?: string; 
}

const LoginButton: React.FC<LoginButtonProps> = ({
    children,
    onClick,
    oauthUrl,
    dev,
    ...props
  }) => {
    const { query } = useRouter();
    const clickHandler = useCallback(() => {
      if (typeof query.next === "string" && query.next) {
        try {
          localStorage.setItem(loginNextPathKey, query.next);
        } catch {}
      }
  
      window.location.href = oauthUrl as string;
    }, [query, oauthUrl]);
  
    return (
      <Button
        className="justify-center text-base py-3 mt-2 "
        color={dev ? "primary" : "secondary"}
        onClick={oauthUrl ? clickHandler : onClick}
        {...props}
      >
        <div
          className="grid gap-4"
          style={{
            gridTemplateColumns: "1fr auto 1fr",
          }}
        >
          {children[0]}
          {children[1]}
          <div />
        </div>
      </Button>
    );
  };


  export const LoginPage: React.FC = () => {
    // useSaveTokensFromQueryParams();
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
          <div className="flex justify-self-center self-center sm:hidden">
            {/* <LgLogo /> */}
          </div>
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
              <LoginButton 
                oauthUrl={`${apiBaseUrl}/`}
              >
                <SvgSolidGitHub width={20} height={20} />
                Log in with GitHub
              </LoginButton>
              <LoginButton
                oauthUrl={`${apiBaseUrl}/`}
              >
                <SvgSolidGoogle width={20} height={20} />
                Log in with Twitter
              </LoginButton>
            </div>
            <div className="flex flex-col gap-4">
                <div className="flex flex-col">
                    <h3 className="text-primary-100 text-sm text-gray">Username:</h3>
                    <div className="flex">
                        <Input
                        autoFocus
                        placeholder={"Enter your username"}
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
                <a
                    href=""
                    className="ml-auto hover:text-primary-200 text-sm mt-0"
                >
                    Forgot Password?
                </a>
                <LoginButton
                // onClick={}
                >
                <SvgSolidPerson width={20} height={20} />
                    Login with email
                </LoginButton>
                <h3 className="text-sm text-center">
                    <span className="font-normal">Dont have an account?</span>
                    <a
                        href=""
                        className="hover:text-primary-200 text-sm mt-0 ml-1"
                    >
                        Sign up
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
    );
  };
  