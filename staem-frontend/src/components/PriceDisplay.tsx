import React from "react";

import { Text } from "@mantine/core";

import { CURRENCY_MAP } from "../constants/currency";

type PriceDisplayProps = {
  price: number;
  currency: string;
};

export default function PriceDisplay({ price, currency }: PriceDisplayProps) {
  // @ts-ignore
  const currencySymbol: any = CURRENCY_MAP[currency];

  return (
    <Text weight="bolder">
      {price === 0 ? "Free" : `${price} ${currencySymbol}`}
    </Text>
  );
}
