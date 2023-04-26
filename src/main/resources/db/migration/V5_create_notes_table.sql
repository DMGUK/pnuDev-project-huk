CREATE TABLE IF NOT EXISTS notes(
      id SERIAL PRIMARY KEY NOT NULL,
      title VARCHAR(20) NOT NULL,
      content TEXT NOT NULL,
      date DATE NOT NULL,
      user_id INT NOT NULL,
      CONSTRAINT note_user_id_foreign FOREIGN KEY(user_id) REFERENCES users(id)
);