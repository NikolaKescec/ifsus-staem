import React from "react";

import { AspectRatio, Image } from "@mantine/core";

import { A11y, Navigation, Pagination, Scrollbar } from "swiper";
import { Swiper, SwiperSlide } from "swiper/react";
import "swiper/css";
import "swiper/css/navigation";
import "swiper/css/pagination";
import "swiper/css/scrollbar";

import { PictureResponse } from "../api/types";

type ImageCarouselProps = {
  images: PictureResponse[];
};

export default function ImageCarousel({ images }: ImageCarouselProps) {
  return (
    <AspectRatio ratio={16 / 9}>
      <Swiper
        modules={[Navigation, Pagination, Scrollbar, A11y]}
        spaceBetween={50}
        slidesPerView={1}
        navigation={true}
        loop={true}
        pagination={{ clickable: true }}
        scrollbar={{ draggable: true }}
      >
        {images.map((image, index) => (
          <SwiperSlide key={index}>
            <Image src={image.urlFull} alt="image" />
          </SwiperSlide>
        ))}
      </Swiper>
    </AspectRatio>
  );
}
