package com.example.chat.model;

public class ContactInfo {
    private String number;
    private String name;
    private String imageUrl;

    public ContactInfo(String number, String name, String imageUrl) {
        this.number = number;
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public ContactInfo(String number, String name) {
        this.number = number;
        this.name = name;
        this.imageUrl = null;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }



}
