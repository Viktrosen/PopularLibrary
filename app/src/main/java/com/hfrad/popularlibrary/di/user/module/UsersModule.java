package com.hfrad.popularlibrary.di.user.module;

import dagger.Module;
import dagger.Provides;
import com.hfrad.popularlibrary.di.user.UsersScope;
import com.hfrad.popularlibrary.mvp.model.api.IDataSource;
import com.hfrad.popularlibrary.mvp.model.cache.IGithubUsersCache;
import com.hfrad.popularlibrary.mvp.model.cache.room.RoomGithubUsersCache;
import com.hfrad.popularlibrary.mvp.model.entity.room.Database;
import com.hfrad.popularlibrary.mvp.model.network.INetworkStatus;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubUsersRepo;
import com.hfrad.popularlibrary.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;

@Module
public class UsersModule {
    @Provides
    IGithubUsersCache usersCache(Database db) {
        return new RoomGithubUsersCache(db);
    }

    @UsersScope
    @Provides
    public IGithubUsersRepo usersRepo(IDataSource api, INetworkStatus status, IGithubUsersCache cache) {
        return new RetrofitGithubUsersRepo(api, status, cache);
    }
}
