package com.hfrad.popularlibrary.mvp.model.repo.retrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.hfrad.popularlibrary.mvp.model.api.IDataSource;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.room.Database;
import com.hfrad.popularlibrary.mvp.model.entity.room.RoomGithubUser;
import com.hfrad.popularlibrary.mvp.model.network.INetworkStatus;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubUsersRepo;


public class RetrofitGithubUsersRepo implements IGithubUsersRepo {
    private final IDataSource api;
    private INetworkStatus networkStatus;
    private Database db;

    public RetrofitGithubUsersRepo(IDataSource api, INetworkStatus status, Database database) {
        this.api = api;
        this.networkStatus = status;
        this.db = database;
    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return networkStatus.isOnlineSingle().flatMap((isOnline) -> {
            if (isOnline) {
                return api.getUsers().flatMap((users) -> {
                    return Single.fromCallable(() -> {
                        List<RoomGithubUser> roomGithubUsers = new ArrayList<>();

                        for (GithubUser user: users) {
                            RoomGithubUser roomUser = new RoomGithubUser(user.getId(),
                                    user.getLogin(),
                                    user.getAvatarUrl(),
                                    user.getReposUrl());

                            roomGithubUsers.add(roomUser);
                        }

                        db.userDao().insert(roomGithubUsers);

                        return users;
                    });
                });
            } else {
                return Single.fromCallable(() -> {
                    List<GithubUser> users = new ArrayList<>();

                    List<RoomGithubUser> roomGithubUsers = db.userDao().getAll();

                    for (RoomGithubUser roomGithubUser : roomGithubUsers) {
                        GithubUser githubUser = new GithubUser(roomGithubUser.getId(),
                                roomGithubUser.getLogin(),
                                roomGithubUser.getAvatarUrl(),
                                roomGithubUser.getReposUrl());

                        users.add(githubUser);
                    }

                    return users;
                });
            }
        }).subscribeOn(Schedulers.io());
    }
}
