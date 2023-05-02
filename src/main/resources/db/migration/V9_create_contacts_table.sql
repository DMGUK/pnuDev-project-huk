CREATE TABLE IF NOT EXISTS contacts(
    id SERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(20) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT contact_user_id_foreign FOREIGN KEY(user_id) REFERENCES users(id)
);