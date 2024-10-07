package org.ecommercems.orderservice.proxy.essentials;

public class User {
    private String username;
    private String password;
    private double id;

    public User(String username, String password) {
        this.id = Math.random()*1000;
        this.username = username;
        this.password = password;
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
