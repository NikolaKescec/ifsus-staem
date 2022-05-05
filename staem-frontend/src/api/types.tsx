// general

export type Page<T> = {
  content: T[];
  totalElements: number;
  pageable: {
    pageNumber: number;
    pageSize: number;
  };
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

export type ArticleResponse = {};

export type ArticleDetailsResponse = {};

export type FindArticlesQuery = {};
