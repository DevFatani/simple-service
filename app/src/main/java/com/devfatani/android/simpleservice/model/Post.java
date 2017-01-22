package com.devfatani.android.simpleservice.model;

import java.util.Locale;

/**
 * Created by devfatani on 1/22/17.
 */

public class Post {
    private int userId;
    private int id;
    private String title;
    private String body;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public String toString() {
        return String.format(Locale.getDefault(), "id: %d\ntitle: %s\nbody: %s\n", this.id, this.title, this.body);
    }
}
