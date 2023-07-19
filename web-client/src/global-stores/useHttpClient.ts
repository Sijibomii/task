import { create } from "zustand";
import { combine } from "zustand/middleware";
import { http } from "@sijibomi/task-api-client";

export const useHttpClient = create(
  combine(
    {
      http: null,
    },
    (set, get) => ({
      set,
      setHttpClient: (http: http.Http) => {
        const client = get().http;
        set({ http });
      },
    })
  )
);
