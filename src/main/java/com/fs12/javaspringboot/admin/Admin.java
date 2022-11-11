package com.fs12.javaspringboot.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Admin {
    private String firstName;
    private String lastName;
    private final String email;
    private String password;
    private Permission[] permissions;
}
