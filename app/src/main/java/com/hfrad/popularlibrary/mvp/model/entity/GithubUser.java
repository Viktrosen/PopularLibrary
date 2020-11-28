package com.hfrad.popularlibrary.mvp.model.entity;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class GithubUser {
    @Expose private String id;
    @Expose private String login;
    @Expose private String avatarUrl;
    @Expose private String reposUrl;
    @Expose private List<Repos> repos;

    public GithubUser(String login) {
        this.login = login;
        repos = new ArrayList<>();
    }

    public GithubUser(List<Repos> repos) {
        repos = new ArrayList<>();
    }

    public String getLogin() {
        return login;
    }

    public String getId() {
        return id;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public List<Repos> getRepos() {
        return repos;
    }

    public String getReposUrl() {
        return reposUrl;
    }
}
