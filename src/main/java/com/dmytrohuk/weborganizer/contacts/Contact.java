package com.dmytrohuk.weborganizer.contacts;

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


@Entity
@Table(name = "contacts")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Contact {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "contacts_generator")
    @SequenceGenerator(name="contacts_generator", sequenceName = "contacts_SEQ", allocationSize=1)

    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    @Column(name = "surname")
    private String surname;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false, insertable=false, updatable=false)
    @JsonIgnore
    private User user;

    @Column(name = "user_id")
    private Long userId;
}
