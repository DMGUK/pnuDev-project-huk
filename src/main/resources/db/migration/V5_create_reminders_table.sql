CREATE TABLE reminders(
      id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('reminders_seq'),
      title VARCHAR(20) NOT NULL,
      content TEXT NOT NULL,
      due_date DATE NOT NULL,
      user_id INT NOT NULL,
      calendar_id INT NOT NULL
);