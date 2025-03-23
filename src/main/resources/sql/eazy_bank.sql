DROP TABLE IF EXISTS contact;
DROP TABLE IF EXISTS notice;
DROP TABLE IF EXISTS card;
DROP TABLE IF EXISTS loan;
DROP TABLE IF EXISTS transaction;
DROP TABLE IF EXISTS account;
DROP TABLE IF EXISTS authorities;
DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS customer;

CREATE TABLE users
(
    username VARCHAR(50)  NOT NULL PRIMARY KEY,
    password VARCHAR(500) NOT NULL,
    enabled  BOOLEAN      NOT NULL
);

INSERT IGNORE INTO users
VALUES
    ('user', '{noop}EazyBytes@12345', '1'),
    ('admin', '{bcrypt}$2a$12$IvF.U/MXVse2o.JwKRWGu.5sUbFmhhg7c7xcmuJ.cKFhJW4U7Ztee', '1');


CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);

CREATE UNIQUE INDEX ix_auth_username ON authorities (username, authority);

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



CREATE TABLE account
(
    account_number  INT                 NOT NULL,
    customer_id     INT                 NOT NULL,
    account_type    VARCHAR(100)        NOT NULL,
    branch_address  VARCHAR(200)        NOT NULL,
    created_at      DATETIME        DEFAULT NULL,
    PRIMARY KEY (account_number),
    KEY         customer_id (customer_id),
    CONSTRAINT  fk_account_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO account (customer_id, account_number, account_type, branch_address, created_at)
VALUES (1, 1865764534, 'Savings', '123 Main Street, New York', NOW());



CREATE TABLE transaction
(
    transaction_id      INT             NOT NULL AUTO_INCREMENT,
    account_number      INT             NOT NULL,
    customer_id         INT             NOT NULL,
    transaction_at      DATETIME        NOT NULL,
    transaction_summary VARCHAR(200)    NOT NULL,
    transaction_type    VARCHAR(100)    NOT NULL,
    transaction_amount  INT             NOT NULL,
    closing_balance     INT             NOT NULL,
    created_at          DATETIME    DEFAULT NULL,
    PRIMARY KEY (transaction_id),
    KEY         customer_id (customer_id),
    KEY         account_number (account_number),
    CONSTRAINT  fk_transaction_account FOREIGN KEY (account_number) REFERENCES account (account_number) ON DELETE CASCADE,
    CONSTRAINT  fk_transaction_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO transaction (account_number, customer_id, transaction_at, transaction_summary, transaction_type, transaction_amount, closing_balance, created_at)
VALUES
    (1865764534, 1, DATE_SUB(NOW(), INTERVAL 7 DAY), 'Coffee Shop', 'Withdrawal', 30, 34500,
        DATE_SUB(NOW(), INTERVAL 7 DAY)),
    (1865764534, 1, DATE_SUB(NOW(), INTERVAL 6 DAY), 'Uber', 'Withdrawal', 100, 34400,
        DATE_SUB(NOW(), INTERVAL 6 DAY)),
    (1865764534, 1, DATE_SUB(NOW(), INTERVAL 5 DAY), 'Self Deposit', 'Deposit', 500, 34900,
        DATE_SUB(NOW(), INTERVAL 5 DAY)),
    (1865764534, 1, DATE_SUB(NOW(), INTERVAL 4 DAY), 'Ebay', 'Withdrawal', 600, 34300,
        DATE_SUB(NOW(), INTERVAL 4 DAY)),
    (1865764534, 1, DATE_SUB(NOW(), INTERVAL 2 DAY), 'OnlineTransfer', 'Deposit', 700, 35000,
        DATE_SUB(NOW(), INTERVAL 2 DAY)),
    (1865764534, 1, DATE_SUB(NOW(), INTERVAL 1 DAY), 'Amazon.com', 'Withdrawal', 100, 34900,
        DATE_SUB(NOW(), INTERVAL 1 DAY));



CREATE TABLE loan
(
    loan_number         INT             NOT NULL AUTO_INCREMENT,
    customer_id         INT             NOT NULL,
    start_date          DATE            NOT NULL,
    loan_type           VARCHAR(100)    NOT NULL,
    total_loan          INT             NOT NULL,
    amount_paid         INT             NOT NULL,
    outstanding_amount  INT             NOT NULL,
    created_at          DATETIME    DEFAULT NULL,
    PRIMARY KEY (loan_number),
    KEY         customer_id (customer_id),
    CONSTRAINT  fk_loan_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO loan (customer_id, start_date, loan_type, total_loan, amount_paid, outstanding_amount, created_at)
VALUES
    (1, '2020-10-13', 'Home', 200000, 50000, 150000, '2020-10-13'),
    (1, '2020-06-06', 'Vehicle', 40000, 10000, 30000, '2020-06-06'),
    (1, '2018-02-14', 'Home', 50000, 10000, 40000, '2018-02-14'),
    (1, '2018-02-14', 'Personal', 10000, 3500, 6500, '2018-02-14');



CREATE TABLE card
(
    card_id             INT             NOT NULL AUTO_INCREMENT,
    card_number         VARCHAR(100)    NOT NULL,
    customer_id         INT             NOT NULL,
    card_type           VARCHAR(100)    NOT NULL,
    total_limit         INT             NOT NULL,
    amount_used         INT             NOT NULL,
    available_amount    INT             NOT NULL,
    created_at          DATETIME    DEFAULT NULL,
    PRIMARY KEY (card_id),
    KEY         customer_id (customer_id),
    CONSTRAINT fk_card_customer FOREIGN KEY (customer_id) REFERENCES customer (customer_id) ON DELETE CASCADE
);

INSERT INTO card (card_number, customer_id, card_type, total_limit, amount_used, available_amount, created_at)
VALUES
    ('4565XXXX4656', 1, 'Credit', 10000, 500, 9500, NOW()),
    ('3455XXXX8673', 1, 'Credit', 7500, 600, 6900, NOW()),
    ('2359XXXX9346', 1, 'Credit', 20000, 4000, 16000, NOW());



CREATE TABLE notice
(
    notice_id           INT             NOT NULL AUTO_INCREMENT,
    notice_summary      VARCHAR(200)    NOT NULL,
    notice_detail       VARCHAR(500)    NOT NULL,
    notice_begin_date   DATE            NOT NULL,
    notice_end_date     DATE        DEFAULT NULL,
    created_at          DATETIME    DEFAULT NULL,
    updated_at          DATETIME    DEFAULT NULL,
    PRIMARY KEY (notice_id)
);

INSERT INTO notice (notice_summary, notice_detail, notice_begin_date, notice_end_date, created_at, updated_at)
VALUES
    ('Home Loan Interest rates reduced',
        'Home loan interest rates are reduced as per the goverment guidelines. The updated rates will be effective immediately',
        NOW() - INTERVAL 30 DAY, NOW() + INTERVAL 30 DAY, NOW(), NULL),
    ('Net Banking Offers',
        'Customers who will opt for Internet banking while opening a saving account will get a $50 amazon voucher',
        NOW() - INTERVAL 30 DAY, NOW() + INTERVAL 30 DAY, NOW(), NULL),
    ('Mobile App Downtime',
        'The mobile application of the EazyBank will be down from 2AM-5AM on 12/05/2020 due to maintenance activities',
        NOW() - INTERVAL 30 DAY, NOW() + INTERVAL 30 DAY, NOW(), NULL),
    ('E Auction notice',
        'There will be a e-auction on 12/08/2020 on the Bank website for all the stubborn arrears.Interested parties can participate in the e-auction',
        NOW() - INTERVAL 30 DAY, NOW() + INTERVAL 30 DAY, NOW(), NULL),
    ('Launch of Millennia card',
        'Millennia Credit card are launched for the premium customers of EazyBank. With these card, you will get 5% cashback for each purchase',
        NOW() - INTERVAL 30 DAY, NOW() + INTERVAL 30 DAY, NOW(), NULL),
    ('COVID-19 Insurance',
        'EazyBank launched an insurance policy which will cover COVID-19 expenses. Please reach out to the branch for more details',
        NOW() - INTERVAL 30 DAY, NOW() + INTERVAL 30 DAY, NOW(), NULL);



CREATE TABLE contact
(
    contact_id      VARCHAR(50)     NOT NULL,
    contact_name    VARCHAR(50)     NOT NULL,
    contact_email   VARCHAR(100)    NOT NULL,
    subject         VARCHAR(500)    NOT NULL,
    message         VARCHAR(2000)   NOT NULL,
    created_at      DATETIME    DEFAULT NULL,
    PRIMARY KEY (contact_id)
);
