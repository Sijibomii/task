import { create } from "zustand";
import { combine } from "zustand/middleware";

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
