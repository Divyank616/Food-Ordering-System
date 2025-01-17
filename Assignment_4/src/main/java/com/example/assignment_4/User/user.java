package com.example.assignment_4.User;

abstract public class user {
    // Protected fields accessible by subclasses (Student, Professor, Administrator)
    protected String email;
    protected String status;

    public user(String email, String status) {
        this.email = email;
        this.status = status;
    }
    public user(String email){
        this.email = email;
    }
    public String getEmail() {
        return email;
    }

    // Setter for email
    public void setEmail(String email) {
        this.email = email;
    }

    // Getter for password
    public String getStatus() {
        return status;
    }

    // Setter for password
    public void setStatus(String status) {
        this.status = status;
    }
    public abstract void login();
}
