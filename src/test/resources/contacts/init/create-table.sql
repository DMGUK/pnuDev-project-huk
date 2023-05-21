CREATE TABLE IF NOT EXISTS users (
     id INT AUTO_INCREMENT NOT NULL,
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

CREATE TABLE IF NOT EXISTS contacts (
     id INT AUTO_INCREMENT NOT NULL,
     name TEXT NOT NULL,
     phone TEXT NOT NULL ,
     surname TEXT NOT NULL,
     user_id INT NOT NULL
     CONSTRAINT fk_user_id REFERENCES users(id)
);
