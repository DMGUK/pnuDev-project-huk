CREATE TABLE IF NOT EXISTS users (
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

CREATE TABLE IF NOT EXISTS notes (
     id INT AUTO_INCREMENT PRIMARY KEY CHECK (id > 0) NOT NULL,
     title TEXT NOT NULL,
     content TEXT NOT NULL,
     created_date DATE NOT NULL,
     updated_date DATE NOT NULL,
     user_id INT NOT NULL
     CONSTRAINT fk_user_id REFERENCES users(id)
);