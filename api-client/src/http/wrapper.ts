import { Http } from "./raw";
 
export interface Response{
  code?: Number,
  message?: String
};

export interface LoginResponse extends Response {
    data?: {
        access: String,
        refresh: String
    }
}
 
export const wrap = (http: Http) => {

  return {
    // captcha: I'm not sure the typescript type of response
    //  to use: 
    //    const captchaImage = await captcha;

    //   const imageUrl = URL.createObjectURL(captchaImage);
    
    //   const captchaElement = document.createElement('img');
    //   captchaElement.src = imageUrl;
    captcha: () => http.request("GET", "/captcha", {}) as Promise<Blob>,

    login: (email: String, password: String, captcha: String) => http.request("POST", "/login", { 
        email, password, captcha
    }) as Promise<Response>,

    loginVerify: (code: String) => http.request("POST", "/login/verify", { 
       code
    }) as Promise<LoginResponse>,

    // register
    register: (email: String, password: String, captcha: String) => http.request("POST", "/register", { 
        email, password, captcha
    }) as Promise<Response>,


    testUser: (username: string) =>
      http.request("GET", `/dev/test-info?username=${username}`) as Promise<{
        accessToken: string;
        refreshToken: string;
      }>,
  };
};