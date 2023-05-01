CREATE TABLE IF NOT EXISTS calendars(
   id SERIAL PRIMARY KEY NOT NULL,
   title VARCHAR(20) NOT NULL,
   description TEXT NOT NULL,
   start_date DATE NOT NULL,
   end_date DATE NOT NULL,
   timezone TEXT NOT NULL,
   user_id INT NOT NULL,
   CONSTRAINT calendar_user_id_foreign FOREIGN KEY(user_id) REFERENCES users(id)
);