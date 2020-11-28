package com.hfrad.popularlibrary.mvp.model.api;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;

import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.Repos;

public interface IDataSource {

    @GET("/users")
    Single<List<GithubUser>> getUsers();

    @GET("/users/{login}")
    Single<GithubUser> loadUser(@Path("login")String login);

    @GET("/users/{login}/repos")
    Single<List<GithubUser>> getReposUrl(@Path("login")String login);

    @GET("/users/{login}/repos")
    Single<List<GithubUser>> getRepos(@Path("login")String login);




}
