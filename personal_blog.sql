USE MASTER
GO
DROP DATABASE personal_blog
GO
CREATE DATABASE personal_blog
GO
USE personal_blog
GO

CREATE TABLE users
(
	nickname			VARCHAR(60)		NOT NULL,
	email				VARCHAR(200)	NOT NULL,
	profile_image_url	VARCHAR(200)	NOT NULL,
	is_adm				BIT

	PRIMARY KEY(nickname)
)
GO

CREATE TABLE posts
(
	id					INT				NOT NULL,
	title				VARCHAR(100)	NOT NULL,
	content_url			VARCHAR(100)	NOT NULL,
	user_nickname		VARCHAR(60)		NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (user_nickname) REFERENCES users (nickname) ON DELETE SET NULL
)
GO

CREATE TABLE post_images
(
	url_image			VARCHAR(60)		NOT NULL,
	post_id				INT				NOT NULL,

	PRIMARY KEY (url_image),
	FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE
)
GO

CREATE TABLE post_comments
(
	id					INT				NOT NULL,
	content				VARCHAR(2000)	NOT NULL,
	user_nickname		VARCHAR(60)		NULL,
	post_id				INT				NOT NULL,

	PRIMARY KEY (id),
	FOREIGN KEY (user_nickname) REFERENCES users (nickname) ON DELETE SET NULL,
	FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE
)
GO

INSERT INTO users VALUES
('user-1', 'user1@gmail.com', 'url-profile-1', 1),
('user-2', 'user2@gmail.com', 'url-profile-2', 0)
GO

INSERT INTO posts VALUES
(1, 'title-1', 'url-content-1', 'user-1'),
(2, 'title-2', 'url-content-2', 'user-1')
GO

INSERT INTO post_images VALUES
('url-image-1', 1),
('url-image-2', 1),
('url-image-3', 2),
('url-image-4', 2)
GO

INSERT INTO post_comments VALUES
(1, 'content-1', 'user-2', 1),
(2, 'content-2', 'user-2', 1),
(3, 'content-3', 'user-2', 1),
(4, 'content-4', 'user-1', 1)
GO

SELECT * FROM users GO
SELECT * FROM posts GO
SELECT * FROM post_images GO
SELECT * FROM post_comments