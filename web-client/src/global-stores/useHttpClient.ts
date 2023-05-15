import { create } from "zustand";
import { combine } from "zustand/middleware";
import { Http } from "@sijibomi/api-client";

export const useHttpClient = create(
  combine(
    {
      http: null,
    },
    (set, get) => ({
      set,
      setHttpClient: (http: Http) => {
        const client = get().http;
        set({ http });
      },
    })
  )
);
