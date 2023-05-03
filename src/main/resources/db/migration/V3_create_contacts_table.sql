CREATE TABLE IF NOT EXISTS contacts(
    id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('contacts_seq'),
    name VARCHAR(20) NOT NULL,
    phone VARCHAR(20) NOT NULL,
    surname VARCHAR(20) NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT contact_user_id_foreign FOREIGN KEY(user_id) REFERENCES users(id)
);