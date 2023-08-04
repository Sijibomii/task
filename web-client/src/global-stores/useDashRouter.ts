import { create } from "zustand";
import { combine } from "zustand/middleware";
import { http } from "@sijibomi/task-api-client";

type Current = "task" | "channel"

export const useDashRouter = create(
  combine( 
    {
      current: "task"
    },
    (set, get) => ({
      set,
      setCurrent: (current: Current) => {
        set({
          current
        });
      },
    })
  )
);
