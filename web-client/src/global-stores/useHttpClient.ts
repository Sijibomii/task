import { create } from "zustand";
import { combine } from "zustand/middleware";
import { http } from "@sijibomi/task-api-client";

export const useHttpClient = create(
  combine( 
    {
      http: http.create({
        baseUrl: "http://localhost:8080"
      })
    },
    (set, get) => ({
      set,
      setHttpClient: (http: http.Http) => {
        const client = get().http;
        set({
          http: http
        });
      },
    })
  )
);
