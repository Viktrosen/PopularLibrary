package com.hfrad.popularlibrary.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class RoomGithubUser {
    @PrimaryKey @NonNull
    public String id;

    public String login;
    public String avatarUrl;
    public String reposUrl;

    public RoomGithubUser() {
    }

    public RoomGithubUser(String id, String login, String avatar) {
        this.id = id;
        this.login = login;
        this.avatarUrl = avatar;
    }

    public String getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public String getReposUrl() {
        return reposUrl;
    }
}
