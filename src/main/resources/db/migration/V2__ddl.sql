CREATE TABLE roles
(
    id   BIGSERIAL   NOT NULL PRIMARY KEY,
    name VARCHAR(50) NOT NULL,
    UNIQUE (name)
);

CREATE TABLE tips
(
    id          BIGSERIAL   NOT NULL PRIMARY KEY,
    advice      VARCHAR(80) NOT NULL,
    description VARCHAR(300)
);

CREATE TABLE users
(
    id         BIGSERIAL   NOT NULL PRIMARY KEY,
    username   VARCHAR(30) NOT NULL,
    password   VARCHAR(80) NOT NULL,
    email      VARCHAR(50) NOT NULL,
    first_name VARCHAR(25),
    last_name  VARCHAR(25),
    birthday   DATE,
    role_id    INTEGER,
    FOREIGN KEY (role_id) references roles (id) ON UPDATE CASCADE ON DELETE CASCADE
);