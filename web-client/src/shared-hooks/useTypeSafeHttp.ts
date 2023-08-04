import { http } from "@sijibomi/task-api-client";
import { useQuery, UseQueryOptions } from "react-query";
import { isServer } from "../lib/isServer";
import { useHttpClient } from "./useHttpClient";

type Keys = keyof ReturnType<typeof http.wrap>;

// (string | number | boolean)[] means an arr that can hold any of the three values string, number, boolean (can be mixed too)
type PaginatedKey<K extends Keys> = [K, ...(string | number | boolean)[]];
//  checks if T is a promise type. if yes, returns the type U which is the type of the resolved value to else returns T
type Await<T> = T extends Promise<infer U> ? U : T;

export const useTypeSafeQuery = <K extends Keys>(
  key: K | PaginatedKey<K>,
  opts?: UseQueryOptions,
  // get the parameter types of the function k
  params?: Parameters<ReturnType<typeof http.wrap>[K]>
) => {
  const httpClient = useHttpClient();
  const conn = http.wrap;
  return useQuery<Await<ReturnType<ReturnType<typeof conn>[K]>>>(
    key,
    () => {
      
      const fn = conn(httpClient)[typeof key === "string" ? key : key[0]] as any;
      return fn(...(params || []));
    },
    {
      enabled: !isServer,
      ...opts,
    } as any
  );
};