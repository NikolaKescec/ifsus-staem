INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('Article 1', 'Description 1', 100, 'USD', 'http://www.google.com', '2018-01-01', 'game', NULL);
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('Article 2', 'Description 2', 200, 'USD', 'http://www.google.com', '2018-01-01', 'game', NULL);
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('Article 3', 'Description 3', 300, 'USD', 'http://www.google.com', '2018-01-01', 'game', NULL);
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('DLC 1', 'Description 4', 400, 'USD', 'http://www.google.com', '2018-01-01', 'game', 1);
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('DLC 2', 'Description 5', 500, 'USD', 'http://www.google.com', '2018-01-01', 'game', 1);
INSERT INTO Article (title, description, price, currency, picture_url, release_date, article_type, id_base_article) VALUES ('DLC 3', 'Description 6', 600, 'USD', 'http://www.google.com', '2018-01-01', 'game', 2);

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
