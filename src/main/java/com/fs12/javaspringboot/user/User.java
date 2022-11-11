package com.fs12.javaspringboot.user;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private String firstName;
    private String lastName;
    private final String email;
    private String image;
    private boolean isBanned;
}
