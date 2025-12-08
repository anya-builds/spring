package com.demo.first.app;

//POJ--> plain old java Objects
public class User {
    private int id;
    private String name;
    private String email;

    public User(int id, String email, String name) {
        this.id = id;
        this.email = email;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
}
