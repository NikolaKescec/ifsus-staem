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
  dlcs: ArticleResponse[];
  categories: CategoryResponse[];
  developers: DeveloperResponse[];
  genres: GenreResponse[];
  pictures: PictureResponse[];
  publishers: PublisherResponse[];
};

export type CategoryResponse = {
  id: number;
  name: string;
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
