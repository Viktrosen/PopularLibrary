package com.hfrad.popularlibrary.mvp.model.repo.retrofit;

import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.hfrad.popularlibrary.mvp.model.api.IDataSource;
import com.hfrad.popularlibrary.mvp.model.entity.GithubRepository;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.room.Database;
import com.hfrad.popularlibrary.mvp.model.network.INetworkStatus;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubRepositoriesRepo;

public class RetrofitGithubRepositoriesRepo implements IGithubRepositoriesRepo {
    private final IDataSource api;
    private INetworkStatus networkStatus;
    private Database db;

    public RetrofitGithubRepositoriesRepo(IDataSource api, INetworkStatus status, Database database) {
        this.api = api;
        this.networkStatus = status;
        this.db = database;
    }

    @Override
    public Single<List<GithubRepository>> getRepositories(GithubUser user) {
        final String url = user.getReposUrl();

        return api.getRepositories(url).subscribeOn(Schedulers.io());
    }
}
