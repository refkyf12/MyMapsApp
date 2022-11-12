package com.example.mymapsapp;

public class createUser {

    public createUser(){

    }

    public String name;
    public String email;
    public String password;

    public createUser(String name, String email, String password, String code, String isSharing, String lat, String lng, String imageUri) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.code = code;
        this.isSharing = isSharing;
        this.lat = lat;
        this.lng = lng;
        this.imageUri = imageUri;
    }

    public String code;
    public String isSharing;
    public String lat;
    public String lng;
    public String imageUri;
}
