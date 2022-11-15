package com.fs12.javaspringboot.util;

public class EmailAlreadyInUse extends Exception {
    public EmailAlreadyInUse(String message) {
        super(message);
    }
}
