// general

export type Page<T> = {
  content: T[];
  totalPages: number;
  pageable: {
    pageNumber: number;
    pageSize: number;
  };
};

export type PageInfo = {
  page: number;
  size: number;
};

export type Status = "success" | "waiting" | "idle" | "error";

export class ValidationError extends Error {
  errors: object;

  constructor(errors: object) {
    super("Validation error");
    this.errors = errors;
  }
}

// articles

export type ArticleResponse = {
  id: number;
  title: string;
  pictureUrl: string;
  price: number;
  currency: string;
};

export type ArticleDetailsResponse = {
  id: number;
  title: string;
  description: string;
  price: number;
  currency: string;
  pictureUrl: string;
  releaseDate: string;
  articleType: string;
  alreadyBought: boolean;
  dlcs: ArticleResponse[];
  categories: CategoryResponse[];
  developers: DeveloperResponse[];
  genres: GenreResponse[];
  pictures: PictureResponse[];
  publishers: PublisherResponse[];
};

export type ArticleType = "GAME" | "DLC";

export type CategoryResponse = {
  id: number;
  name: string;
};

export type CreateArticleCommand = {
  type: ArticleType | string;
  baseArticleId: number | undefined;
  title: string;
  description: string;
  price: number | undefined;
  currency: string;
  pictureUrl: string;
  releaseDate: string | Date;
  categories: string[];
  developers: string[];
  genres: string[];
  publishers: string[];
  pictures: CreatePictureCommand[];
};

export type CreateCartCommand = {
  articles: number[];
};

export type CreatePictureCommand = {
  urlFull: string;
  urlThumbnail: string;
};

export type DeveloperResponse = {
  id: number;
  name: string;
};

export type GenreResponse = {
  id: number;
  name: string;
};

export type PictureResponse = {
  urlFull: string;
  urlThumbnail: string;
};

export type PublisherResponse = {
  id: number;
  name: string;
};

export type ArticlesFilter = {
  title?: string;
  publisherId?: number | string;
  developerId?: number | string;
  categoryId?: number | string;
  genreId?: number | string;
  priceRange?: [number, number];
};

export type FindArticlesQuery = {
  title?: string;
  publisherId?: number | string;
  developerId?: number | string;
  categoryId?: number | string;
  genreId?: number | string;
  priceRange?: PriceRange;
  pageInfo: PageInfo;
};

export type PriceRange = {
  minPrice: number;
  maxPrice: number;
};

export type UpdateCatalogValues = {
  id: number;
  name: string;
};

export type ImgbbResponse = {
  data: {
    id: string;
    title: string;
    url_viewer: string;
    url: string;
    display_url: string;
    width: string;
    height: string;
    size: string;
    time: string;
    expiration: string;
    image: {
      filename: string;
      name: string;
      mime: string;
      extension: string;
      url: string;
    };
    thumb: {
      filename: string;
      name: string;
      mime: string;
      extension: string;
      url: string;
    };
    medium: {
      filename: string;
      name: string;
      mime: string;
      extension: string;
      url: string;
    };
    delete_url: string;
  };
  success: boolean;
  status: number;
};

export type DataValues = {
  value: string;
  label: string;
};
