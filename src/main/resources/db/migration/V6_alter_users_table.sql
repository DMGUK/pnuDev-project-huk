ALTER TABLE users
    ADD CONSTRAINT username UNIQUE (username);
ALTER TABLE users
    ADD CONSTRAINT email UNIQUE (email);
ALTER TABLE users
    ADD CONSTRAINT password UNIQUE (password);