package com.hfrad.popularlibrary.mvp.model.api;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Url;
import com.hfrad.popularlibrary.mvp.model.entity.GithubRepository;
import com.hfrad.popularlibrary.mvp.model.entity.Number;

public interface IDataSource {

    @GET("/random/year?json")
    Single<Number> getUsers();

    @GET
    Single<List<GithubRepository>> getRepositories(@Url String url);

}

