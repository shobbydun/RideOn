package com.example.rideon.models;

public class NavCategoryModel {
    String name;
    String description;
    String best;
    String img_url;

    public NavCategoryModel() {
    }

    public NavCategoryModel(String name, String description, String best, String img_url) {
        this.name = name;
        this.description = description;
        this.best = best;
        this.img_url = img_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBest() {
        return best;
    }

    public void setBest(String best) {
        this.best = best;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
