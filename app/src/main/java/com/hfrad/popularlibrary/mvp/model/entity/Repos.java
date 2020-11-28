package com.hfrad.popularlibrary.mvp.model.entity;

import com.google.gson.annotations.Expose;

public class Repos {
    @Expose private String id;
    @Expose private String name;
    @Expose private String forks;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getForks() {
        return forks;
    }
}
