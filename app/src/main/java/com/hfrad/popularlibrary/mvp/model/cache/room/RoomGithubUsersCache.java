package com.hfrad.popularlibrary.mvp.model.cache.room;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

import com.hfrad.popularlibrary.mvp.model.cache.IGithubUsersCache;
import com.hfrad.popularlibrary.mvp.model.entity.Response;
import com.hfrad.popularlibrary.mvp.model.entity.Result;
import com.hfrad.popularlibrary.mvp.model.entity.room.Database;
import com.hfrad.popularlibrary.mvp.model.entity.room.RoomGithubUser;

public class RoomGithubUsersCache implements IGithubUsersCache {
    private final Database db;

    public RoomGithubUsersCache(Database db) {
        this.db = db;
    }

    @Override
    public Single<Response> getUsers() {
        return Single.fromCallable(()->{
            List<RoomGithubUser> roomGithubUsers = db.userDao().getAll();

            Response users = new Response();

            /*for (RoomGithubUser roomGithubUser : roomGithubUsers) {
                Result githubUser = new Result(roomGithubUser.getId(),
                        roomGithubUser.getLogin(),
                        roomGithubUser.getAvatarUrl(),
                        roomGithubUser.getLocUrl());

                users.add(githubUser);
            }*/

            return users;
        });
    }

    @Override
    public Completable putUsers(Response users) {
        return Completable.fromAction(()->{
            List<RoomGithubUser> roomGithubUsers = new ArrayList<>();

            for (Result user: users.getResults()) {
                RoomGithubUser roomUser = new RoomGithubUser(user.getId(),
                        user.getName(),
                        user.getImage(),
                        user.getLocation().getName());

                roomGithubUsers.add(roomUser);
            }

            db.userDao().insert(roomGithubUsers);
        }).subscribeOn(Schedulers.io());
    }
}
