package org.ecommercems.userservice.controller;


public class User {
    private double id;
    private String username;
    private String password;

    public User(String username, String password) {
        this.id = Math.round(Math.random() * 1000);
        this.username = username;
        this.password = "i"+password+this.id ;
    }

    public User(){}

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public double getId() {
        return id;
    }

}
