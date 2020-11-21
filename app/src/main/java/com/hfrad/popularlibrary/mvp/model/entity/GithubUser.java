package com.hfrad.popularlibrary.mvp.model.entity;



public class GithubUser {
    private String login;
    private String image;

    public String getImage() {
        return image;
    }

    public GithubUser(String login, String image) {
        this.login = login;
        this.image = image;
    }

    public String getLogin() {
        return login;
    }

}
