package com.dmytrohuk.weborganizer.reminders;

import com.dmytrohuk.weborganizer.calendars.Calendars;
import com.dmytrohuk.weborganizer.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "calendar_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private Calendars calendar;

    @Column(name = "calendar_id")
    private Long calendarId;
}
