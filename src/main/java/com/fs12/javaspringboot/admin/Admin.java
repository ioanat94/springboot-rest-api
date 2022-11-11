package com.fs12.javaspringboot.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admins")
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
    private String firstName;
    private String lastName;
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
