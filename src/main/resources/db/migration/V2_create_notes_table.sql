CREATE TABLE notes(
      id SERIAL PRIMARY KEY DEFAULT NOT NULL,
      title VARCHAR(20) NOT NULL,
      content TEXT NOT NULL,
      created_date TIMESTAMP NOT NULL,
      updated_date TIMESTAMP NOT NULL,
      user_id INT NOT NULL,
      CONSTRAINT note_user_id_foreign FOREIGN KEY(user_id) REFERENCES users(id)
);