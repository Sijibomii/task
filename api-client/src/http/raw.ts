import fetch from "isomorphic-unfetch";

const BASE_URL = "https://localhost:8080/";

// TODO: Make headers type stricter

interface Options {
  baseUrl?: string;
}

type Request = (
  method: string,
  endpoint: string,
  body?: unknown,
  opts?: Options,
  headers?: any
) => Promise<unknown>;

export type Http = {
  request: Request;
};

export const create = (baseOpts: Options): Http => {
  return {

    request: async (
      method: string,
      endpoint: string,
      body?: unknown, 
      opts: Options = {},
      headers?: any
    ) => {
      const { baseUrl = BASE_URL } = { ...baseOpts, ...opts };

      return endpoint === '/captcha' ? 
    
    //   captcha  returns image
      await fetch(`${baseUrl}${endpoint}`, {
        method,
        headers: { "Content-Type": "application/json", ...headers},
        body: body ? JSON.stringify(body) : undefined,
      }).then((res) => res.blob())
      :

      await fetch(`${baseUrl}${endpoint}`, {
        method,
        headers: { "Content-Type": "application/json", ...headers},
        body: body ? JSON.stringify(body) : undefined,
      }).then((res) => res.json())
    },
  };
};


