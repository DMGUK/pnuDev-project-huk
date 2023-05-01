package com.dmytrohuk.weborganizer.calendars;

import com.dmytrohuk.weborganizer.reminders.Reminder;
import com.dmytrohuk.weborganizer.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "calendars")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Calendars {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "calendars_generator")
    @SequenceGenerator(name="calendars_generator", sequenceName = "calendars_SEQ", allocationSize=1)
    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @Column(name = "timezone")
    private String timezone;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private User user;

    @Column(name = "user_id")
    private Long userId;

    public void setUser(User user) {
        this.user = user;
        user.getCalendars().add(this);
    }

    @OneToMany(mappedBy = "calendar", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Reminder> reminders;
}
