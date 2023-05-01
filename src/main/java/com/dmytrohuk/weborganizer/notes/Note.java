package com.dmytrohuk.weborganizer.notes;

import com.dmytrohuk.weborganizer.users.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "notes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Note {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "notes_generator")
    @SequenceGenerator(name="notes_generator", sequenceName = "notes_SEQ", allocationSize=1)

    private Long id;

    @Column(name = "title")
    private String title;

    @Column(name = "content")
    private String content;

    @Column(name = "created_date")
    private LocalDate created_date;


    @Column(name = "updated_date")
    private LocalDate updated_date;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private User user;


    @Column(name = "user_id")
    private Long userId;


    public void setUser(User user) {
        this.user = user;
        user.getNotes().add(this);
    }
}