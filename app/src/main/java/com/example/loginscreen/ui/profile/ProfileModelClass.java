package com.example.loginscreen.ui.profile;

import android.graphics.Bitmap;

public class ProfileModelClass {
    private Integer profileId;
    private String fname, lname, section;
    private Bitmap image;

    public ProfileModelClass(String fname, String lname, String section, Bitmap image) {
        this.profileId = profileId;
        this.fname = fname;
        this.lname = lname;
        this.section = section;
        this.image = image;
    }

    public ProfileModelClass(Integer profileId, String fname, String lname, String section, Bitmap image) {
        this.profileId = profileId;
        this.fname = fname;
        this.lname = lname;
        this.section = section;
        this.image = image;
    }

    public Integer getProfileId() {
        return profileId;
    }

    public void setProfileId(Integer profileId) {
        this.profileId = profileId;
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
