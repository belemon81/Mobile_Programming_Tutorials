package com.example.mpr.tutorials.tut5_db.models;

public class Friend {
    private long id;
    private String name;
    private String email;
    private String phoneNo;

    public Friend(long id, String name, String email, String phoneNo) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
    }

    public Friend(String name, String email, String phoneNo) {
        this.name = name;
        this.email = email;
        this.phoneNo = phoneNo;
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

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
