import React from "react";

import { Text } from "@mantine/core";

import { currencyMap } from "../constants/currency";

type PriceDisplayProps = {
  price: number;
  currency: string;
};

export default function PriceDisplay({ price, currency }: PriceDisplayProps) {
  // @ts-ignore
  const currencySymbol: any = currencyMap[currency];

  return (
    <Text weight="bolder">
      {price === 0 ? "Free" : `${price} ${currencySymbol}`}
    </Text>
  );
}
