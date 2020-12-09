package com.hfrad.popularlibrary.mvp.model.repo.retrofit;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.http.Path;

import com.hfrad.popularlibrary.mvp.model.api.IDataSource;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.Repos;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubUsersRepo;


public class RetrofitGithubUsersRepo implements IGithubUsersRepo {
    private IDataSource api;

    public RetrofitGithubUsersRepo(IDataSource api) {
        this.api = api;
    }

    @Override
    public Single<List<GithubUser>> getUsers() {
        return api.getUsers().subscribeOn(Schedulers.io());
    }

    @Override
    public Single<GithubUser> loadUser(String login) {
        return api.loadUser(login).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<GithubUser>> getReposUrl(String login) {
        return api.getReposUrl(login).subscribeOn(Schedulers.io());
    }

    @Override
    public Single<List<GithubUser>> getRepos(String login){
        return api.getRepos(login).subscribeOn(Schedulers.io());
    }


}
