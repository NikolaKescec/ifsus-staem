import React from "react";

import cx from "classnames";

import { useThemeContext } from "../context/ThemeContext";

type ButtonProps = {
  label: string;
  onClick?: () => void;
  variant?: "primary" | "secondary";
  className?: string;
  type?: "button" | "submit" | "reset";
};

export default function Button({
  label,
  className = "",
  type = "button",
  variant = "primary",
  onClick = () => {},
}: ButtonProps) {
  const { button } = useThemeContext();

  const buttonClassName = cx({
    [button.primary]: variant === "primary",
    [button.secondary]: variant === "secondary",
    [className]: true,
  });

  return (
    <button type={type} onClick={onClick} className={buttonClassName}>
      {label}
    </button>
  );
}
