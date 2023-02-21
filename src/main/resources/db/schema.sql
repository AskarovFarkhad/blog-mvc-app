-- Создаем таблицу "users" для хранения информации о пользователях
CREATE TABLE users
(
    user_id  UserDto PRIMARY KEY,
    username VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    is_deleted BOOLEAN NOT NULL,
    is_admin BOOLEAN NOT NULL
);

-- Создаем таблицу "posts" для хранения информации о постах
CREATE TABLE posts
(
    post_id    UserDto PRIMARY KEY,
    title      VARCHAR(100) NOT NULL,
    content    TEXT,
    created_at TIMESTAMP    NOT NULL,
    user_id    UserDto          NOT NULL REFERENCES users (user_id) ON DELETE CASCADE
);

-- Создаем таблицу "comments" для хранения информации о комментариях
create TABLE comments
(
    comment_id UserDto PRIMARY KEY,
    content    TEXT,
    created_at TIMESTAMP NOT NULL,
    user_id    UserDto       NOT NULL REFERENCES users (user_id),
    post_id    UserDto       NOT NULL REFERENCES posts (post_id)
);

-- Создаем таблицу "tags" для хранения информации о тегах
create TABLE tags
(
    tag_id UserDto PRIMARY KEY,
    name   VARCHAR(50) NOT NULL
);

-- Создаем таблицу "post_tags" для связи между постами и тегами
create TABLE post_tags
(
    post_id UserDto NOT NULL REFERENCES posts (post_id),
    tag_id  UserDto NOT NULL REFERENCES tags (tag_id),
    PRIMARY KEY (post_id, tag_id)
);