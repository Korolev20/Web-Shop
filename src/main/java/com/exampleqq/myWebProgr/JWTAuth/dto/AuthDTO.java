package com.exampleqq.myWebProgr.JWTAuth.dto;

import jakarta.persistence.Column;

public class AuthDTO {
    @Column
    private String fistname ;

    @Column
    private String lastname;

    @Column
    private String email;

    @Column
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
