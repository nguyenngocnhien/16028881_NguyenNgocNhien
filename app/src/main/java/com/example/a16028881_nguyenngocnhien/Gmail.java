package com.example.a16028881_nguyenngocnhien;

public class Gmail {
    private String id;
    private String name;
    private String email;
    private String signinAt;

    public Gmail(String name, String email, String signinAt) {
        this.name = name;
        this.email = email;
        this.signinAt = signinAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSigninAt() {
        return signinAt;
    }

    public void setSigninAt(String signinAt) {
        this.signinAt = signinAt;
    }
}