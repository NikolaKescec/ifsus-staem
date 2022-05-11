import React from "react";

import { Box, Group, Image, Text, useMantineTheme } from "@mantine/core";
import { Dropzone, IMAGE_MIME_TYPE } from "@mantine/dropzone";
import { UseFormReturnType } from "@mantine/form/lib/use-form";
import { IconUpload, IconX, TablerIcon } from "@tabler/icons";

import * as imgbbApi from "../../../api/imgbb";
import { CreateArticleCommand, CreatePictureCommand } from "../../../api/types";
import { getIconColor } from "../util/colorUtil";

type ImageListProps = {
  form: UseFormReturnType<CreateArticleCommand>;
};

export default function ImageListForm({ form }: ImageListProps) {
  const theme = useMantineTheme();

  const [loading, setLoading] = React.useState(false);

  const onDrop = async (files: File[]) => {
    setLoading(true);

    const pictures: CreatePictureCommand[] = [];
    let errorOccurred = false;

    for (const file of files) {
      try {
        const response = await imgbbApi.upload(file);

        const command: CreatePictureCommand = {
          urlFull: response.data.image.url,
          urlThumbnail: response.data.thumb.url,
        };

        if (response.success) {
          pictures.push(command);
          form.setFieldValue("pictures", [...form.values.pictures, command]);
        } else {
          errorOccurred = true;
        }
      } catch (error) {
        errorOccurred = true;
      }

      form.setFieldValue("pictures", [...form.values.pictures, ...pictures]);
      if (errorOccurred) {
        form.setFieldError("pictures", "Upload failed");
      } else {
        form.setFieldError("pictures", null);
      }

      setLoading(false);
    }
  };

  const { error } = form.getInputProps("pictures");

  return (
    <Box>
      <Dropzone
        onDrop={onDrop}
        multiple={true}
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

type ImageUploadIconProps = React.ComponentProps<TablerIcon> & {
  form: UseFormReturnType<CreateArticleCommand>;
};

function ImageUploadIcon({ form, ...props }: ImageUploadIconProps) {
  const picturesLength = form.values.pictures.length;
  const { error } = form.getInputProps("pictures");

  if (!error && picturesLength === 0) {
    return (
      <>
        <IconUpload {...props} />
        <div>
          <Text>Drag images here or click to select files</Text>
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

  return (
    <Group spacing={10}>
      {form.values.pictures.map((picture) => (
        <Image
          key={picture.urlThumbnail}
          src={picture.urlFull}
          height="100px"
        />
      ))}
    </Group>
  );
}
