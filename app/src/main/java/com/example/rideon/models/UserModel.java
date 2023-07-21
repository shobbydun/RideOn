package com.example.rideon.models;

public class UserModel {
    String name;
    String Email;
    String password;
    String profileImg;


    public UserModel(){
    }
    public UserModel(String name, String email, String password){
        this.name = name;
        this.Email = email;
        this.password = password;
    }

    public String getProfileImg() {
        return profileImg;
    }

    public void setProfileImg(String profileImg) {
        this.profileImg = profileImg;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
