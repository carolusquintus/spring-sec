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
VALUES ('admin', '{bcrypt}$2a$12$zzPE4kYY.2vMGD/aroe2uuJm8wxrV3AHwVGMeYaMElMMSjmoicfTC', '1');
INSERT IGNORE INTO `authorities`
VALUES ('admin', 'admin');