CREATE TABLE users(
     id SERIAL PRIMARY KEY DEFAULT NOT NULL ,
     username VARCHAR(20) NOT NULL,
     password VARCHAR(30) NOT NULL,
     firstName VARCHAR(20) NOT NULL,
     surname VARCHAR(20) NOT NULL,
     email VARCHAR(40) NOT NULL,
     address VARCHAR(40) NOT NULL,
     role TEXT NOT NULL
);