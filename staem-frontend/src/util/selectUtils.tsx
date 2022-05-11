import { DataValues } from "../api/types";

type SelectProps = {
  id: number;
  name: string;
};

export function mapDataToSelectItems(data: SelectProps[]): DataValues[] {
  return data.map((item) => ({
    value: item.id.toString(),
    label: item.name,
  }));
}
