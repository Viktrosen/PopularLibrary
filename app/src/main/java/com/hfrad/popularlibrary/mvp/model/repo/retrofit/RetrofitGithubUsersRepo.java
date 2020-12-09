package com.hfrad.popularlibrary.mvp.model.repo.retrofit;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.hfrad.popularlibrary.mvp.model.api.IDataSource;
import com.hfrad.popularlibrary.mvp.model.cache.IGithubUsersCache;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.room.Database;
import com.hfrad.popularlibrary.mvp.model.entity.room.RoomGithubUser;
import com.hfrad.popularlibrary.mvp.model.network.INetworkStatus;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubUsersRepo;


public class RetrofitGithubUsersRepo implements IGithubUsersRepo {
    private final IDataSource api;
    private INetworkStatus networkStatus;
    final IGithubUsersCache cache;

    public RetrofitGithubUsersRepo(IDataSource api, INetworkStatus status, IGithubUsersCache cache) {
        this.api = api;
        this.networkStatus = status;
        this.cache = cache;
    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return networkStatus.isOnlineSingle().flatMap((isOline)-> {
            // Мапируем сетевой статус к данным
            if (isOline) {
                return api.getUsers().flatMap((users) -> {
                    return cache.putUsers(users).toSingleDefault(users);
                });
            } else {
                return cache.getUsers();
            }
        }).subscribeOn(Schedulers.io());
    }
}
