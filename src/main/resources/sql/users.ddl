DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS customer;

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled  BOOLEAN      NOT NULL
);

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

INSERT IGNORE INTO users
VALUES
    ('user', '{noop}EazyBytes@12345', '1'),
    ('admin', '{bcrypt}$2a$12$IvF.U/MXVse2o.JwKRWGu.5sUbFmhhg7c7xcmuJ.cKFhJW4U7Ztee', '1');

INSERT IGNORE INTO authorities
VALUES
    ('admin', 'admin'),
    ('user', 'read');

CREATE TABLE IF NOT EXISTS customer
(
    customer_id     INT                         NOT NULL AUTO_INCREMENT,
    first_name      VARCHAR(100)                NOT NULL,
    last_name       VARCHAR(100)                NOT NULL,
    username        VARCHAR(45)                 NOT NULL,
    email           VARCHAR(45)                 NOT NULL,
    mobile_number   VARCHAR(20)                 NOT NULL,
    password        VARCHAR(255)                NOT NULL,
    role            VARCHAR(45)                 NOT NULL,
    enabled         BOOLEAN     DEFAULT TRUE    NOT NULL,
    created_at      DATETIME    DEFAULT NULL,
    updated_at      DATETIME    DEFAULT NULL,
    PRIMARY KEY (customer_id)
);

INSERT INTO customer (customer_id, first_name, last_name, username, email, mobile_number, password, role, created_at)
VALUES
    (1, 'Usuario', 'Administrador', 'admin', 'admin@example.com', '55 5656 5656', '{bcrypt}$2a$12$wH5l0JebXQBwn/vETDvEfOMHoNAR1E5c1Q6GEYlxk3dYe0fR8yPNW', 'admin', NOW()),
    (2, 'Usuario', 'Lector', 'user', 'user@example.com', '55 5555 5555', '{noop}EazyBytes@12345', 'read', NOW());
