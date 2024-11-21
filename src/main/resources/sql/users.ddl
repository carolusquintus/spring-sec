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

INSERT IGNORE INTO `users`
VALUES ('user', '{noop}EazyBytes@12345', '1');
INSERT IGNORE INTO `authorities`
VALUES ('user', 'read');

INSERT IGNORE INTO `users`
VALUES ('admin', '{bcrypt}$2a$12$mrWlqf3q2sthXPdx1v4X.ukc0URyQAnPGf3IkrM1mqiEWuntQgNfG', '1');
INSERT IGNORE INTO `authorities`
VALUES ('admin', 'admin');

CREATE TABLE IF NOT EXISTS customer
(
    id       BIGINT AUTO_INCREMENT
        PRIMARY KEY,
    email    VARCHAR(45)  NULL,
    password VARCHAR(255) NULL,
    role     VARCHAR(45)  NULL,
    CONSTRAINT UK1xnaqqsgphjppu4xnppn8nnin
        UNIQUE (email)
);

INSERT INTO customer (email, password, role)
VALUES ('user@example.com', '{noop}EazyBytes@12345', 'read');
INSERT INTO customer (email, password, role)
VALUES ('admin@example.com', '{bcrypt}$2a$12$mrWlqf3q2sthXPdx1v4X.ukc0URyQAnPGf3IkrM1mqiEWuntQgNfG', 'admin');

