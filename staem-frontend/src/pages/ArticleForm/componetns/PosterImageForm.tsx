import React from "react";

import {
  Box,
  Group,
  Image,
  MantineTheme,
  Text,
  useMantineTheme,
} from "@mantine/core";
import { Dropzone, DropzoneStatus, IMAGE_MIME_TYPE } from "@mantine/dropzone";
import { UseFormReturnType } from "@mantine/form/lib/use-form";
import { IconUpload, IconX, TablerIcon } from "@tabler/icons";

import * as imgbbApi from "../../../api/imgbb";
import { CreateArticleCommand } from "../../../api/types";

type PosterImageProps = {
  form: UseFormReturnType<CreateArticleCommand>;
};

export default function PosterImageForm({ form }: PosterImageProps) {
  const theme = useMantineTheme();

  const [loading, setLoading] = React.useState(false);

  const onDrop = async (files: File[]) => {
    try {
      setLoading(true);
      const response = await imgbbApi.upload(files[0]);

      if (response.success) {
        form.setFieldValue("pictureUrl", response.data.image.url);
        form.setFieldError("pictureUrl", null);
      } else {
        throw new Error();
      }
    } catch (err) {
      form.setFieldError("pictureUrl", "Failed to upload image");
    } finally {
      setLoading(false);
    }
  };

  const { error } = form.getInputProps("pictureUrl");

  return (
    <Box>
      <Dropzone
        onDrop={onDrop}
        multiple={false}
        loading={loading}
        maxSize={3 * 1024 ** 2}
        accept={IMAGE_MIME_TYPE}
      >
        {(status) => (
          <Group
            position="center"
            spacing="xl"
            style={{ minHeight: 220, pointerEvents: "none" }}
          >
            <ImageUploadIcon
              form={form}
              style={{ color: getIconColor(status, theme) }}
              size={80}
            />
          </Group>
        )}
      </Dropzone>
      {error && (
        <Text color="red" mt={5}>
          {error}
        </Text>
      )}
    </Box>
  );
}

function getIconColor(status: DropzoneStatus, theme: MantineTheme) {
  return status.accepted
    ? theme.colors[theme.primaryColor][theme.colorScheme === "dark" ? 4 : 6]
    : status.rejected
    ? theme.colors.red[theme.colorScheme === "dark" ? 4 : 6]
    : theme.colorScheme === "dark"
    ? theme.colors.dark[0]
    : theme.colors.gray[7];
}

type ImageUploadIconProps = React.ComponentProps<TablerIcon> & {
  form: UseFormReturnType<CreateArticleCommand>;
};

function ImageUploadIcon({ form, ...props }: ImageUploadIconProps) {
  const { value, error } = form.getInputProps("pictureUrl");

  if (!error && !value) {
    return (
      <>
        <IconUpload {...props} />
        <div>
          <Text>Drag image here or click to select file</Text>
        </div>
      </>
    );
  }

  if (error) {
    return (
      <>
        <IconX {...props} />
        <div>
          <Text>{error}</Text>
        </div>
      </>
    );
  }

  return <Image src={value} width="100%" />;
}
