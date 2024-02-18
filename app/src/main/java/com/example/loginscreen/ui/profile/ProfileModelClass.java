package com.example.loginscreen.ui.profile;

import android.graphics.Bitmap;

public class ProfileModelClass {
    private Integer id;
    private String fname, lname, section;
    private Bitmap image;

    public ProfileModelClass(String fname, String lname, String section, Bitmap image) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.section = section;
        this.image = image;
    }

    public ProfileModelClass(Integer id, String fname, String lname, String section, Bitmap image) {
        this.id = id;
        this.fname = fname;
        this.lname = lname;
        this.section = section;
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFname() {
        return fname;
    }

    public  void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
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
