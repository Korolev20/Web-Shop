package com.exampleqq.myWebProgr.JWTAuth.models;


import jakarta.persistence.*;


@Table
@Entity
public class Users {
    @Id
    @Column(name = "userid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "firstname")
    private String fistname ;

    @Column(name = "lastname")
    private String lastname;


    @Column(name = "email")
    private String email;

    @Column(name = "password")

    private String password;

    public Users() {
    }

    public Users(String fistname, String lastname, String email, String password) {
        this.fistname = fistname;
        this.lastname = lastname;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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
