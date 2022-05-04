-- Kreiranje tablica

CREATE TABLE Category
(
  id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Genre
(
  id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Developer
(
  id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Publisher
(
  id BIGINT NOT NULL,
  name VARCHAR(255) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Article
(
  id BIGINT NOT NULL,
  title VARCHAR(255) NOT NULL,
  description TEXT NOT NULL,
  price DECIMAL NOT NULL,
  currency VARCHAR(3) NOT NULL,
  picture_url TEXT NOT NULL,
  release_date DATE NOT NULL,
  article_type VARCHAR(10) NOT NULL,
  id_base_article BIGINT,
  PRIMARY KEY (id),
  FOREIGN KEY (id_base_article) REFERENCES Article(id)
);

CREATE TABLE Picture
(
  id BIGINT NOT NULL,
  url_full TEXT NOT NULL,
  url_thumbnail TEXT NOT NULL,
  id_article BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_article) REFERENCES Article(id)
);

CREATE TABLE Article_Publisher
(
  id_publisher BIGINT NOT NULL,
  id_article BIGINT NOT NULL,
  PRIMARY KEY (id_publisher, id_article),
  FOREIGN KEY (id_publisher) REFERENCES Publisher(id),
  FOREIGN KEY (id_article) REFERENCES Article(id)
);

CREATE TABLE Article_Developer
(
  id_developer BIGINT NOT NULL,
  id_article BIGINT NOT NULL,
  PRIMARY KEY (id_developer, id_article),
  FOREIGN KEY (id_developer) REFERENCES Developer(id),
  FOREIGN KEY (id_article) REFERENCES Article(id)
);

CREATE TABLE Article_Category
(
  id_category BIGINT NOT NULL,
  id_article BIGINT NOT NULL,
  PRIMARY KEY (id_category, id_article),
  FOREIGN KEY (id_category) REFERENCES Category(id),
  FOREIGN KEY (id_article) REFERENCES Article(id)
);

CREATE TABLE Article_Genre
(
  id_genre BIGINT NOT NULL,
  id_article BIGINT NOT NULL,
  PRIMARY KEY (id_genre, id_article),
  FOREIGN KEY (id_genre) REFERENCES Genre(id),
  FOREIGN KEY (id_article) REFERENCES Article(id)
);

CREATE TABLE Role
(
  id BIGINT NOT NULL,
  name VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

CREATE TABLE Users
(
  id BIGINT NOT NULL,
  email VARCHAR(255) NOT NULL,
  first_name VARCHAR(255) NOT NULL,
  last_name VARCHAR(255) NOT NULL,
  profile_picture_url TEXT NOT NULL,
  id_role BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_role) REFERENCES Role(id),
  UNIQUE (email)
);

CREATE TABLE Cart
(
  id BIGINT NOT NULL,
  purchase_date DATE,
  id_user BIGINT NOT NULL,
  PRIMARY KEY (id),
  FOREIGN KEY (id_user) REFERENCES Users(id)
);

CREATE TABLE Purchased_Articles
(
  date_of_purchase DATE NOT NULL,
  price DECIMAL NOT NULL,
  id_user BIGINT NOT NULL,
  id_article BIGINT NOT NULL,
  PRIMARY KEY (id_user, id_article),
  FOREIGN KEY (id_user) REFERENCES Users(id),
  FOREIGN KEY (id_article) REFERENCES Article(id)
);

CREATE TABLE Article_Cart
(
  id_article BIGINT NOT NULL,
  id_cart BIGINT NOT NULL,
  PRIMARY KEY (id_article, id_cart),
  FOREIGN KEY (id_article) REFERENCES Article(id),
  FOREIGN KEY (id_cart) REFERENCES Cart(id)
);
