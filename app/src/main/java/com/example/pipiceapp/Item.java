package com.example.pipiceapp;

public class Item {

    String phoneName;
    String imageID;

    public Item(String phoneName, String imageID) {
        this.phoneName = phoneName;
        this.imageID = imageID;
    }

    public String getPhoneName() {
        return phoneName;
    }

    public void setPhoneName(String phoneName) {
        this.phoneName = phoneName;
    }

    public String getImageID() {
        return imageID;
    }

    public void setImageID(String imageID) {
        this.imageID = imageID;
    }
}
