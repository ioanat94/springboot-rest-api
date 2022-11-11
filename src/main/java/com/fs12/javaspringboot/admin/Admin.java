package com.fs12.javaspringboot.admin;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Admin {
    private String firstName;
    private String lastName;
    private final String email;
    private String password;
    private ArrayList<Permission> permissions;
}
