CREATE TABLE IF NOT EXISTS notes(
    id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('notes_seq'),
    title VARCHAR(20) NOT NULL,
    content TEXT NOT NULL,
    created_date DATE NOT NULL,
    updated_date DATE NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT note_user_id_foreign FOREIGN KEY(user_id) REFERENCES users(id)
);