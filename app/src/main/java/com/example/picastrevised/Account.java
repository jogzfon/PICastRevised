package com.example.picastrevised;

public class Account {
    private String username, email, password, region;
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
        this.email = "";
    }
    public Account(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public Account(String username, String password, String email, String region) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.region = region;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
