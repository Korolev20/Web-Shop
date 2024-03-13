package com.exampleqq.myWebProgr.JWTAuth.dto;

import jakarta.validation.constraints.NotEmpty;


public class UsersDTO {


    private String fistname;


    private String lastname;



    private String email;

    @NotEmpty(message = "Password not empty")
    private String password;


    public String getFistname() {
        return fistname;
    }

    public void setFistname(String fistname) {
        this.fistname = fistname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
}
