package com.thapovan.android.socialnetwork.facebook.model;

/**
 * Created by karthick_vetrivel on 22/08/17.
 */

public class FacebookProfile {

    private String name;

    private String email;

    private String imageUrl;

    public FacebookProfile(String name, String email, String imageUrl) {
        this.name = name;
        this.email = email;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
