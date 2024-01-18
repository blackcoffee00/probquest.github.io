package com.example.loginscreen.ui.profile;

import android.graphics.Bitmap;

public class ProfileModelClass {
    private String name, section;
    private Bitmap image;

    public ProfileModelClass(String name, String section, Bitmap image) {
        this.name = name;
        this.section = section;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public  void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public  void setSection(String section) {
        this.section = section;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

}
