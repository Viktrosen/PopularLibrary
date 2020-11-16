package com.hfrad.popularlibrary.mvp.model.entity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class GithubUserRepo {
    private List<GithubUser> repositories = new ArrayList<>(Arrays.asList(
            new GithubUser("login1"),
            new GithubUser("login2"),
            new GithubUser("login3"),
            new GithubUser("login4"),
            new GithubUser("login5")));

    public Observable<GithubUser> just(){
        return Observable.just(new GithubUser("login1"),
                new GithubUser("login2"),
                new GithubUser("login3"),
                new GithubUser("login4"),
                new GithubUser("login5"));
    }

    public List<GithubUser> getUsers() {
        return Collections.unmodifiableList(repositories);
    }
}
