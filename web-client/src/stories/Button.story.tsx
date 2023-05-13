import React from "react";
import { StoryFn } from "@storybook/react";
import { Button, ButtonProps } from "../ui/Button";
import { toEnum } from "./utils/toEnum";
import { toBoolean } from "./utils/toBoolean";
import { SolidGithub} from "../icons";

export default {
  title: "Button",
  argTypes: { onClick: { action: "clicked" } },
};

const TheButton: StoryFn<ButtonProps & { exampleIcon?: boolean }> = ({ children, exampleIcon, ...props }) => {
  return (
    <Button {...props} icon={exampleIcon ? <SolidGithub/> : undefined}>
      {children || `New Task`}
    </Button>
  );
};

export const Main = TheButton.bind({});

Main.argTypes = {
  color: toEnum(["primary", "secondary"]),
  size: toEnum(["big", "small"]),
  disabled: toBoolean(),
  loading: toBoolean(),
  exampleIcon: toBoolean(),
};
