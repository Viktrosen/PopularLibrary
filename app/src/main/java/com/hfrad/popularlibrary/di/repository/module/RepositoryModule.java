package com.hfrad.popularlibrary.di.repository.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import com.hfrad.popularlibrary.di.repository.RepositoryScope;
import com.hfrad.popularlibrary.mvp.model.api.IDataSource;
import com.hfrad.popularlibrary.mvp.model.cache.IGithubRepositoriesCache;
import com.hfrad.popularlibrary.mvp.model.cache.room.RoomGithubRepositoriesCache;
import com.hfrad.popularlibrary.mvp.model.entity.room.Database;
import com.hfrad.popularlibrary.mvp.model.network.INetworkStatus;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubRepositoriesRepo;
import com.hfrad.popularlibrary.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo;

@Module
public class RepositoryModule {
    @Provides
    IGithubRepositoriesCache userRepositoriesCache(Database db) {
        return new RoomGithubRepositoriesCache(db);
    }

    @RepositoryScope
    @Provides
    public IGithubRepositoriesRepo userRepositoriesRepo(IDataSource api, INetworkStatus networkStatus, IGithubRepositoriesCache cache) {
        return new RetrofitGithubRepositoriesRepo(api, networkStatus, cache);
    }
}
