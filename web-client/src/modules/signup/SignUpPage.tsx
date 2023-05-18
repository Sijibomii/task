import React, {  useEffect, useState } from "react";
import { useRouter } from "next/router";
import { Form, Formik } from "formik";
import { Input } from "../../ui/input";
import {  isStaging,  __prod__, } from "../../lib/constants";
import { isServer } from "../../lib/isServer";
import Image from "next/image";
import { useTokenStore } from "../auth/useTokenStore";
import { LoginButton } from "../landing-page/LoginPage";
import SvgSolidGitHub from "../../icons/GitHub";
import SvgSolidPerson from "../../icons/Person";
import { InputErrorMsg } from "../../ui/inputErrorMsg";
import { useHttpClient } from "../../shared-hooks/useHttpClient";
import { http } from "../../api-client";
import { errorObject, usePasswordValidator } from "../../shared-hooks/usePasswordValidator";

export const SignUpPage: React.FC = () => {
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

                  const emailNotValid =  (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email))
                  if (emailNotValid && email.length !== 0){ 
                    errors.email = "enter a valid email" 
                    return errors
                  }
                  setIsValid(password)
                  if(!isValid && password.length !== 0){
                    errors.password= passwordErrors
                    return errors
                  }

                  // had to do this because formik keeps changing my type of errors 
                  return {};
                }}
               
                onSubmit={async ({ email, password, captcha_code }) => {
                  
                  if (email.length === 0 || password.length ===0) return

                  if (captcha_code.length === 0){
                    alert('generate captcha with the link in the form')
                  }
                    
                    const resp = await wrappedClient.register(email, password, captcha_code)
                    if(resp.code===200 && resp.message==="SUCCESS"){
                     
                    }else{
                      alert(resp.message)
                    }
                }}
              >
                {({ isSubmitting, errors, handleChange, handleBlur, setFieldValue }) => (
                  <Form className={``}>
                    <div className="flex flex-col gap-4">
                    <div className="flex flex-col">
                        <h3 className="text-primary-100 text-sm text-gray">Email:</h3>
                        {errors.email ? (
                          <div className={`flex mt-1`}>
                            <InputErrorMsg>{errors.email}</InputErrorMsg>
                          </div>
                        ) : null }
                          <Input
                            autoFocus
                            placeholder={"Enter your Email"}
                            name="email"
                            id="email"
                            type={"email"}
                            onBlur={handleBlur}
                            onChange={handleChange}
                          />
                    </div>
                    <div className="flex flex-col">
                        <h3 className="text-primary-100 text-sm">Password</h3>
                        {errors.password ? (
                          <div className={`flex flex-col mt-1`}>
                            {errors.password && errors.password.map(error => 
                            <InputErrorMsg
                            key={error.message}>{error.message}</InputErrorMsg>)}
                          </div>
                        ) : null}
                        <Input
                          className={``}
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
                          <h3 className="text-primary-100 text-sm">Captcha</h3>
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
                            className="text-primary-200 text-sm mt-0 underline"
                            onClick={generateCaptcha}
                        >
                            Generate captcha
                        </a>
                        <a
                            href=""
                            className="text-primary-200 text-sm mt-0 underline"
                        >
                            Forgot Password?
                        </a>
                    </div>

                    
                    <LoginButton loading={isSubmitting} type="submit">
                        <SvgSolidPerson width={20} height={20} />
                        Register
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
                  </Form>
                )}
            </Formik>
            
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