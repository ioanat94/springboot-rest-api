package com.fs12.javaspringboot.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admin")
public class Admin {
    @Id
    @SequenceGenerator(
            name = "admin_sequence",
            sequenceName = "admin_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "admin_sequence"
    )
    private int id;
    @NotBlank(message = "First name cannot be empty.")
    private String firstName;
    @NotBlank(message = "Last name cannot be empty.")
    private String lastName;
    @Email(message = "Email address must be valid.")
    @NotBlank(message = "Email address cannot be empty.")
    private String email;
    private String password;
    @ElementCollection(targetClass = Permission.class)
    private List<Permission> permissions;

    public Admin(String firstName, String lastName, String email, String password, List<Permission> permissions) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.permissions = permissions;
    }
}
