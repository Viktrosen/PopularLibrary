package com.hfrad.popularlibrary.mvp.model.repo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Path;

import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.Repos;

public interface IGithubUsersRepo {
    Single<List<GithubUser>> getUsers();
    Single<GithubUser> loadUser(String login);
    Single<List<GithubUser>> getReposUrl(String login);

    Single<List<GithubUser>> getRepos(@Path("login")String login);
}
