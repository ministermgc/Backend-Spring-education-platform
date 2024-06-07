CREATE TABLE lesson_content
(
    id            BIGSERIAL    NOT NULL,
    lesson_id     BIGINT,
    code          TEXT,
    code_language VARCHAR(30),
    paragraph     TEXT NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE lessons
(
    id          BIGSERIAL    NOT NULL,
    title       VARCHAR(40)  NOT NULL,
    description VARCHAR(100) NOT NULL,
    image_url   VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE roles
(
    id        BIGSERIAL NOT NULL,
    authority VARCHAR(255),
    PRIMARY KEY (id)
);

CREATE TABLE tasks
(
    id        BIGSERIAL    NOT NULL,
    lesson_id BIGINT,
    answer    VARCHAR(255) NOT NULL,
    text      VARCHAR(255) NOT NULL,
    title     VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_accounts
(
    id         BIGSERIAL    NOT NULL,
    first_name VARCHAR(20)  NOT NULL,
    last_name  VARCHAR(20)  NOT NULL,
    username   VARCHAR(20)  NOT NULL UNIQUE,
    email      VARCHAR(50)  NOT NULL UNIQUE,
    password   VARCHAR(255) NOT NULL,
    PRIMARY KEY (id)
);

CREATE TABLE user_accounts_roles
(
    role_id         BIGINT NOT NULL,
    user_account_id BIGINT NOT NULL,
    PRIMARY KEY (role_id, user_account_id)
);

ALTER TABLE IF EXISTS lesson_content
    ADD CONSTRAINT lesson_content_lesson
        FOREIGN KEY (lesson_id)
            REFERENCES lessons;

ALTER TABLE IF EXISTS tasks
    ADD CONSTRAINT task_lesson
        FOREIGN KEY (lesson_id)
            REFERENCES lessons;

ALTER TABLE IF EXISTS user_accounts_roles
    ADD CONSTRAINT role_user_account
        FOREIGN KEY (role_id)
            REFERENCES roles;

ALTER TABLE IF EXISTS user_accounts_roles
    ADD CONSTRAINT user_account_user_account
        FOREIGN KEY (user_account_id)
            REFERENCES user_accounts;