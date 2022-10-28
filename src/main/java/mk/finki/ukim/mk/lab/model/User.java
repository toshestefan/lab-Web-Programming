package mk.finki.ukim.mk.lab.model;

public class User {
    private final String username;
    private final String name;
    private final String surnamename;
    private final String password;

    public User(String username, String name, String surnamename, String password) {
        this.username = username;
        this.name = name;
        this.surnamename = surnamename;
        this.password = password;
    }
}
