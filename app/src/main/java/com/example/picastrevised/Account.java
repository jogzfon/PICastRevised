package com.example.picastrevised;

public class Account {
    private String username, email, password, region, firstname, lastname,bio,profile,job;
    private Double balance;

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public Account(String firstname, String lastname, Double balance) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.balance = balance;
    }

    public Account(String firstname, String lastname, String job, String bio, String profile, Double balance) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.job = job;
        this.bio = bio;
        this.profile = profile;
        this.balance = balance;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

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
