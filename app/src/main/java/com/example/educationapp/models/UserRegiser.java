package com.example.educationapp.models;

public class UserRegiser {
    private String userName;
    private String job;
    private String email;
    private String phone;
    private String password;


    public UserRegiser(String userName, String job, String email, String phone, String password) {
        this.userName = userName;
        this.job = job;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserRegiser{" +
                "userName='" + userName + '\'' +
                ", job='" + job + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", password='" + password + '\'' +
                '}';
    }



}
