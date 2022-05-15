-- PUBLISHER
INSERT INTO publisher (name) VALUES ('Valve');
INSERT INTO publisher (name) VALUES ('Amazon Games');

-- GENRE
INSERT INTO genre (name) VALUES ('Action');
INSERT INTO genre (name) VALUES ('Free to Play');

-- DEVELOPER
INSERT INTO developer (name) VALUES ('Valve');
INSERT INTO developer (name) VALUES ('Hidden Path Entertainment');

-- CATEGORY
INSERT INTO category (name) VALUES ('Multi-player');
INSERT INTO category (name) VALUES ('Steam Achievements');

-- ARTICLE
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('Article 1', 'Description 1', 100, 'USD', 'http://www.google.com', '2018-01-01', 'GAME', NULL);
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('Article 2', 'Description 2', 200, 'USD', 'http://www.google.com', '2018-01-01', 'GAME', NULL);
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('Article 3', 'Description 3', 300, 'USD', 'http://www.google.com', '2018-01-01', 'GAME', NULL);
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('DLC 1', 'Description 4', 400, 'USD', 'http://www.google.com', '2018-01-01', 'GAME', 1);
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('DLC 2', 'Description 5', 500, 'USD', 'http://www.google.com', '2018-01-01', 'GAME', 1);
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('DLC 3', 'Description 6', 600, 'USD', 'http://www.google.com', '2018-01-01', 'GAME', 2);

-- ARTICLE DEVELOPERS
INSERT INTO article_developer (id_article, id_developer) VALUES (1, 1);
INSERT INTO article_developer (id_article, id_developer) VALUES (2, 1);
INSERT INTO article_developer (id_article, id_developer) VALUES (3, 2);
INSERT INTO article_developer (id_article, id_developer) VALUES (4, 2);
INSERT INTO article_developer (id_article, id_developer) VALUES (5, 1);
INSERT INTO article_developer (id_article, id_developer) VALUES (5, 2);
INSERT INTO article_developer (id_article, id_developer) VALUES (6, 2);


-- ARTICLE PUBLISHERS
INSERT INTO Article_Publisher (id_article, id_publisher) VALUES (1, 1);
INSERT INTO Article_Publisher (id_article, id_publisher) VALUES (2, 1);
INSERT INTO Article_Publisher (id_article, id_publisher) VALUES (3, 2);
INSERT INTO Article_Publisher (id_article, id_publisher) VALUES (4, 2);
INSERT INTO Article_Publisher (id_article, id_publisher) VALUES (5, 1);
INSERT INTO Article_Publisher (id_article, id_publisher) VALUES (5, 2);
INSERT INTO Article_Publisher (id_article, id_publisher) VALUES (6, 2);
-- ARTICLE CATEGORIES
INSERT INTO Article_Category (id_article, id_category) VALUES (1, 1);
INSERT INTO Article_Category (id_article, id_category) VALUES (2, 1);
INSERT INTO Article_Category (id_article, id_category) VALUES (3, 2);
INSERT INTO Article_Category (id_article, id_category) VALUES (4, 2);
INSERT INTO Article_Category (id_article, id_category) VALUES (5, 1);
INSERT INTO Article_Category (id_article, id_category) VALUES (5, 2);
INSERT INTO Article_Category (id_article, id_category) VALUES (6, 2);

-- ARTICLE GENRES
INSERT INTO Article_Genre (id_article, id_genre) VALUES (1, 1);
INSERT INTO Article_Genre (id_article, id_genre) VALUES (2, 1);
INSERT INTO Article_Genre (id_article, id_genre) VALUES (3, 2);
INSERT INTO Article_Genre (id_article, id_genre) VALUES (4, 2);
INSERT INTO Article_Genre (id_article, id_genre) VALUES (5, 1);
INSERT INTO Article_Genre (id_article, id_genre) VALUES (5, 2);
INSERT INTO Article_Genre (id_article, id_genre) VALUES (6, 2);

-- PICTURES
INSERT INTO Picture (url_full, url_thumbnail, id_article) VALUES ('http://www.google.com', 'http://www.google.com', 1);
INSERT INTO Picture (url_full, url_thumbnail, id_article) VALUES ('http://www.google.com', 'http://www.google.com', 1);
INSERT INTO Picture (url_full, url_thumbnail, id_article) VALUES ('http://www.google.com', 'http://www.google.com', 2);
INSERT INTO Picture (url_full, url_thumbnail, id_article) VALUES ('http://www.google.com', 'http://www.google.com', 3);
INSERT INTO Picture (url_full, url_thumbnail, id_article) VALUES ('http://www.google.com', 'http://www.google.com', 4);
INSERT INTO Picture (url_full, url_thumbnail, id_article) VALUES ('http://www.google.com', 'http://www.google.com', 5);
INSERT INTO Picture (url_full, url_thumbnail, id_article) VALUES ('http://www.google.com', 'http://www.google.com', 6);

-- USER
INSERT INTO Users (id, is_deleted) VALUES ('USER_1', false);
INSERT INTO Users (id, is_deleted) VALUES ('USER_2', false);
INSERT INTO Users (id, is_deleted) VALUES ('USER_3', false);

-- PURCHASED ARTICLES
INSERT INTO Purchased_Articles (date_of_purchase, price, id_user, id_article) VALUES (CURRENT_DATE, 1, 'USER_1', 1);
INSERT INTO Purchased_Articles (date_of_purchase, price, id_user, id_article) VALUES (CURRENT_DATE, 2, 'USER_1', 2);
INSERT INTO Purchased_Articles (date_of_purchase, price, id_user, id_article) VALUES (CURRENT_DATE, 3, 'USER_1', 3);
INSERT INTO Purchased_Articles (date_of_purchase, price, id_user, id_article) VALUES (CURRENT_DATE, 0, 'USER_2', 2);
INSERT INTO Purchased_Articles (date_of_purchase, price, id_user, id_article) VALUES (CURRENT_DATE, 4, 'USER_2', 3);
