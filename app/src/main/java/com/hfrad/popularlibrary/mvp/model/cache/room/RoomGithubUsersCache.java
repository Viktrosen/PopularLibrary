package com.hfrad.popularlibrary.mvp.model.cache.room;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;

import com.hfrad.popularlibrary.mvp.model.cache.IGithubUsersCache;
import com.hfrad.popularlibrary.mvp.model.entity.Number;
import com.hfrad.popularlibrary.mvp.model.entity.room.Database;
import com.hfrad.popularlibrary.mvp.model.entity.room.RoomGithubUser;

public class RoomGithubUsersCache implements IGithubUsersCache {
    private final Database db;

    public RoomGithubUsersCache(Database db) {
        this.db = db;
    }

    @Override
    public Single<Number> getUsers() {
        return Single.fromCallable(()->{
            RoomGithubUser roomGithubUsers = db.userDao().getAll();




                Number number = new Number(roomGithubUsers.getId(),
                        roomGithubUsers.getLogin(),
                        roomGithubUsers.getAvatarUrl(),
                        roomGithubUsers.getLocUrl());




            return number;
        });
    }

    @Override
    public Completable putUsers(Number users) {
        return Completable.fromAction(()->{
            List<RoomGithubUser> roomGithubUsers = new ArrayList<>();


                RoomGithubUser roomUser = new RoomGithubUser(users.getId(),
                        users.getLogin(),
                        users.getAvatarUrl(),
                        users.getLocUrl());

                roomGithubUsers.add(roomUser);


            db.userDao().insert(roomGithubUsers);
        }).subscribeOn(Schedulers.io());
    }
}

