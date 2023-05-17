CREATE SEQUENCE IF NOT EXISTS user_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT CHECK (id > 0) NOT NULL,
    username VARCHAR(20) UNIQUE NOT NULL,
    password VARCHAR(30) UNIQUE NOT NULL,
    first_name VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    email VARCHAR(40) UNIQUE NOT NULL,
    address VARCHAR(40) NOT NULL,
    role TEXT NOT NULL,
    CONSTRAINT username_unique UNIQUE (username),
    CONSTRAINT email_unique UNIQUE (email),
    CONSTRAINT password_unique UNIQUE (password)
);
