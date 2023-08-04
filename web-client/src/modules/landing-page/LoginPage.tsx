import React, { useCallback, useContext, useEffect, useState } from "react";
import { Form, Formik } from "formik";
import { useRouter } from "next/router";
import { Button } from "../../ui/Button";
import { Input } from "../../ui/input";
import { apiBaseUrl, isStaging, loginNextPathKey, __prod__, } from "../../lib/constants";
import { isServer } from "../../lib/isServer";
import Image from "next/image";
import { useTokenStore } from "../auth/useTokenStore";
import SvgSolidGoogle from "../../icons/SolidGoogle";
import SvgSolidGitHub from "../../icons/GitHub";
import SvgSolidPerson from "../../icons/Person";
import { errorObject, usePasswordValidator } from "../../shared-hooks/usePasswordValidator";
import { InputErrorMsg } from "../../ui/inputErrorMsg";
import { useHttpClient } from "../../shared-hooks/useHttpClient";
import { http } from "@sijibomi/task-api-client";

interface LoginButtonProps {
    children: React.ReactNode | [React.ReactNode, React.ReactNode];
    dev?: true;
    onClick?: () => void;
    oauthUrl?: string; 
    className?: string;
    type?: "button" | "submit" | "reset" | undefined;
    loading?: boolean;
}

export const LoginButton: React.FC<LoginButtonProps> = ({
    children,
    onClick,
    oauthUrl,
    dev,
    type, 
    loading,
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
        className="justify-center text-base py-3 mt-2 bg-black"
        color={dev ? "primary" : "secondary"}
        type={type ? type : undefined}
        loading={loading ? true: false}
        onClick={oauthUrl ? clickHandler : onClick}
        {...props}
      >
        <div
          className={Array.isArray(children) ? "grid gap-4": ""}
          style={{
            gridTemplateColumns: "1fr auto 1fr",
          }}
        >
          {Array.isArray(children) ? [ ...children] : children}
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
    const [captcha, setCaptcha] = useState<string | undefined | null>(undefined);
    const httpClient = useHttpClient();
    const wrappedClient = http.wrap(httpClient)
    const [ isValid, passwordErrors,  setIsValid ] = usePasswordValidator({
      digits: true,
      lowercase: true,
      uppercase: true,
      symbols: true,
      spaces: false
    });

    interface LoginErrors {
      email?: string | undefined;
      password?: errorObject[] | undefined;
      captcha_code?: string | undefined;
    }

   async function generateCaptcha(){
      const reader = new FileReader();
      const resp = await wrappedClient.captcha()
      reader.onloadend = () => {
        const dataUrl = reader.result;
        setCaptcha(dataUrl as string);
      };

      reader.readAsDataURL(resp);
    }
   
  
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
        <div className="flex items-center justify-center w-screen h-screen">
        <div className="flex items-center justify-center">
          <div className="hidden sm:flex" />
          <div className="flex  m-auto flex-col p-14 gap-5 bg-[#151a21] sm:rounded-8 z-10 sm:w-400 w-full">
            <div className="flex gap-2 flex-col text-center">
              <span className="text-3xl text-[#dee3ea] font-bold">Welcome</span>
              <div className="text-[#dee3ea] text-center">
                By logging in you accept our&nbsp;
                <br />
                <a
                  href="/privacy-policy.html"
                  className="text-[#fd4d4d] hover:underline"
                >
                  Privacy Policy
                </a>
                &nbsp;and&nbsp;
                <a href="/terms.html" className="text-[#fd4d4d] hover:underline">
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
                Log in with Google
              </LoginButton>
            </div>
            <Formik<{
                email: string;
                password: string;
                captcha_code: String;
              }>
                initialValues={
                  {
                    email: "",
                    password: "",
                    captcha_code: ""
                  }
                }
                validateOnChange
                validateOnBlur 

                validate={({ email, password }): LoginErrors => {

                  const errors: LoginErrors = {};

                  // const emailNotValid =  (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email))
                  // if (emailNotValid && email.length !== 0){ 
                  //   errors.email = "enter a valid email" 
                  //   return errors
                  // }
                  // setIsValid(password)
                  // if(!isValid && password.length !== 0){
                  //   errors.password= passwordErrors
                  //   return errors
                  // }

                  // had to do this because formik keeps changing my type of errors 
                  return {};
                }}
               
                onSubmit={async ({ email, password, captcha_code }) => {
                  
                  if (email.length === 0 || password.length ===0) return

                  if (captcha_code.length === 0){
                    alert('generate captcha with the link in the form')
                  }
                    
                    const resp = await wrappedClient.login(email, password, captcha_code)
                    if(resp.code===200 && resp.message==="SUCCESS"){
                      localStorage.setItem("USER_EMAIL", email);
                      push('/login-verify')
                    }else{
                      alert(resp.message)
                    }
                }}
              >
                {({ isSubmitting, errors, handleChange, handleBlur, setFieldValue }) => (
                  <Form className={``}>
                    <div className="flex flex-col gap-4">
                    <div className="flex flex-col">
                        <h3 className="text-[#dee3ea] text-sm text-gray">Email:</h3>
                        {errors.email ? (
                          <div className={`flex mt-1`}>
                            <InputErrorMsg>{errors.email}</InputErrorMsg>
                          </div>
                        ) : null }
                          <Input
                            autoFocus
                            className={`login-input`}
                            placeholder={"Enter your Email"}
                            name="email"
                            id="email"
                            type={"email"}
                            onBlur={handleBlur}
                            onChange={handleChange}
                          />
                    </div>
                    <div className="flex flex-col">
                        <h3 className="text-[#dee3ea] text-sm">Password</h3>
                        {errors.password ? (
                          <div className={`flex flex-col mt-1`}>
                            {errors.password && errors.password.map(error => 
                            <InputErrorMsg
                            key={error.message}>{error.message}</InputErrorMsg>)}
                          </div>
                        ) : null}
                        <Input
                          className={`login-input`}
                          id="password"
                          placeholder={"Enter password"}
                          name="password"
                          type={"password"}
                          onBlur={handleBlur}
                          onChange={handleChange}
                          />
                    </div>
                    <div className="flex flex-col">
                        
                          {captcha && (
                          <>
                          <h3 className="text-[#dee3ea] text-sm">Captcha</h3>
                          <div className="flex items-center justify-between">
                              <Image src={captcha} height={100} width={120} alt="captcha" />
                              <Input
                              className={`ml-3`}
                              // autoFocus
                              placeholder={"Enter Captcha Code"}
                              id="captcha_code"                   
                              name="captcha_code"
                              onBlur={handleBlur}
                              onChange={handleChange}
                            
                            />
                          </div>
                          </>
                          )}
                    </div>
                    <div className="flex items-center justify-between">
                        <a
                            href="#"
                            className="text-[#b2bdcd] text-sm mt-0 underline"
                            onClick={generateCaptcha}
                        >
                            Generate captcha
                        </a>
                        <a
                            href=""
                            className="text-[#b2bdcd] text-sm mt-0 underline"
                        >
                            Forgot Password?
                        </a>
                    </div>

                    
                    <LoginButton loading={isSubmitting} type="submit">
                        <SvgSolidPerson width={20} height={20} />
                        Login with email
                    </LoginButton>


                    <h3 className="text-sm text-center">
                        <span className="font-normal text-[#b2bdcd]">Dont have an account?</span>
                        <a
                            href=""
                            className="text-[#b2bdcd] text-sm mt-0 ml-1"
                        >
                            Sign up
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
            <div className="flex flex-row gap-6 text-[#5d7290]">
              <a href="mail:to" className="hover:text-[#b2bdcd]">
                sijibomiolajubu@gmail.com
              </a>
              <a
                href="https://github.com/sijibomii/task/issues"
                className="ml-2 hover:text-[#b2bdcd]"
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
                    className="ml-2 cursor-pointer hover:text-[#b2bdcd]"
                  />
                </a>
              </div>
            </div>
          </div>
        </div>
        </div>
      </>
    );
  };
  