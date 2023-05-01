package com.dmytrohuk.weborganizer.reminders;

import com.dmytrohuk.weborganizer.calendars.Calendars;
import com.dmytrohuk.weborganizer.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "reminders")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Reminder {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reminders_generator")
    @SequenceGenerator(name="reminders_generator", sequenceName = "reminders_SEQ", allocationSize=1)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "duedate")
    private LocalDate duedate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private User user;

    @Column(name = "user_id")
    private Long userId;

    public void setUser(User user) {
        this.user = user;
        user.getReminders().add(this);
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private Calendars calendar;

    @Column(name = "calendar_id")
    private Long calendarId;

    public void setCalendar(Calendars calendar) {
        this.calendar = calendar;
        calendar.getReminders().add(this);
    }
}
