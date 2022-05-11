export function mapDataToSelectItems(
  data: { id: number; name: string }[]
): { value: string; label: string }[] {
  return data.map((item) => ({
    value: item.id.toString(),
    label: item.name,
  }));
}
