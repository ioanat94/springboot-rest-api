package com.fs12.javaspringboot.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @SequenceGenerator(
            name = "user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String image;
    private boolean isBanned;

    public User(String firstName, String lastName, String email, String image, boolean isBanned) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
        this.isBanned = isBanned;
    }
}
