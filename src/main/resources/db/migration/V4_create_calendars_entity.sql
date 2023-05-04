CREATE SEQUENCE IF NOT EXISTS calendars_seq INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS calendars(
    id INT CHECK (id > 0) NOT NULL DEFAULT NEXTVAL ('calendars_seq'),
    title VARCHAR(20) NOT NULL,
    description TEXT NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL,
    timezone TEXT NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT calendar_user_id_foreign FOREIGN KEY(user_id) REFERENCES users(id)
);