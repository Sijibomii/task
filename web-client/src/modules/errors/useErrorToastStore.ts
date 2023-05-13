import React from "react";
import { create } from "zustand";
import { combine } from "zustand/middleware";
import { v4 } from "uuid";
import { ToastDurations } from "../../ui/ErrorToast";

type Toast = {
  id: string;
  button?: React.ReactNode;
  duration?: ToastDurations;
  message: string;
};

export const useErrorToastStore = create(
  combine(
    {
      toasts: [] as Toast[],
    },
    (set) => ({
        // remove a toast by id
      hideToast: (id: string) => set((x) => ({ toasts: x.toasts.filter((y) => y.id !== id) })),
        // add toast to toast array
      showToast: (t: Omit<Toast, "id">) =>
        set((x) => {
            // remove any prev toast with same message
          const currentRemovedToasts: Toast[] = x.toasts.filter((y) => y.message !== t.message);
          return {
            toasts: [...currentRemovedToasts, { ...t, id: v4() }]
          };
        }),
    })
  )
);
