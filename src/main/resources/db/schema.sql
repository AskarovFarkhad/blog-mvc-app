-- Создаем таблицу "users" для хранения информации о пользователях
CREATE TABLE users
(
    user_id  UUID PRIMARY KEY,
    username VARCHAR(50)  NOT NULL,
    email    VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    is_deleted BOOLEAN NOT NULL,
    is_admin BOOLEAN NOT NULL
);

-- Создаем таблицу "posts" для хранения информации о постах
CREATE TABLE posts
(
    post_id    UUID PRIMARY KEY,
    title      VARCHAR(100) NOT NULL,
    content    TEXT,
    created_at TIMESTAMP    NOT NULL,
    user_id    UUID          NOT NULL REFERENCES users (user_id) ON DELETE CASCADE
);

-- Создаем таблицу "comments" для хранения информации о комментариях
create TABLE comments
(
    comment_id UUID PRIMARY KEY,
    content    TEXT,
    created_at TIMESTAMP NOT NULL,
    user_id    UUID       NOT NULL REFERENCES users (user_id) ON DELETE CASCADE,
    post_id    UUID       NOT NULL REFERENCES posts (post_id) ON DELETE CASCADE
);

-- Создаем таблицу "tags" для хранения информации о тегах
create TABLE tags
(
    tag_id UUID PRIMARY KEY,
    name   VARCHAR(50) NOT NULL
);

-- Создаем таблицу "post_tags" для связи между постами и тегами
create TABLE post_tags
(
    post_id UUID NOT NULL REFERENCES posts (post_id) ON DELETE CASCADE,
    tag_id  UUID NOT NULL REFERENCES tags (tag_id) ON DELETE CASCADE,
    PRIMARY KEY (post_id, tag_id)
);