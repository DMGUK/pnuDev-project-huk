CREATE SEQUENCE IF NOT EXISTS reminders_seq INCREMENT BY 1;

CREATE TABLE reminders(
      id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('reminders_seq'),
      title VARCHAR(20) NOT NULL,
      content TEXT NOT NULL,
      due_date DATE NOT NULL,
      user_id INT NOT NULL,
      calendar_id INT NOT NULL,
      CONSTRAINT reminders_user_id_foreign FOREIGN KEY(user_id) REFERENCES users(id),
      CONSTRAINT reminders_calendars_id_foreign FOREIGN KEY(calendar_id) REFERENCES calendars(id)
);