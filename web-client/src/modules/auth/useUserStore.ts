import { create } from "zustand";
import { combine } from "zustand/middleware";
import { isServer } from "../../lib/isServer";

const idKey = "@task/user_id";
const emailKey = "@task/user_email";

const getDefaultValues = () => {
  if (!isServer) {
    try {
      return {
        id: localStorage.getItem(idKey) || "",
        email: localStorage.getItem(emailKey) || "",
      };
    } catch {}
  }

  return {
    id: "",
    email: "", 
  };
};

export const useUserStore = create(
  combine(getDefaultValues(), (set) => ({
    setDetails: (x: { id: string; email: string }) => {
      try {
        localStorage.setItem(idKey, x.id);
        localStorage.setItem(emailKey, x.email);
      } catch {}

      set(x);
    },
  }))
);
