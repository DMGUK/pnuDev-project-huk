CREATE TABLE IF NOT EXISTS users(
    id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('users_seq'),
    username VARCHAR(20) NOT NULL,
    password VARCHAR(30) NOT NULL,
    firstname VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    email VARCHAR(40) NOT NULL,
    address VARCHAR(40) NOT NULL,
    role TEXT NOT NULL
);