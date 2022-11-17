package com.fs12.javaspringboot.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
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
    @NotBlank(message = "First name cannot be empty.")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty.")
    private String lastName;
    @Email(message = "Email address must be valid.")
    @NotBlank(message = "Email address cannot be empty.")
    private String email;
    private String image;
    private Boolean isBanned;

    public User(String firstName, String lastName, String email, String image, Boolean isBanned) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.image = image;
        this.isBanned = isBanned;
    }
}
