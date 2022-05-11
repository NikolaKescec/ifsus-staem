import * as paths from "./paths";
import { ImgbbResponse } from "./types";

export async function upload(file: File): Promise<ImgbbResponse> {
  const formData = new FormData();
  formData.append("image", file);

  const response = await fetch(paths.imgbb.upload, {
    method: "POST",
    body: formData,
  });

  return response.json();
}
