package com.hfrad.popularlibrary.mvp.model.cache;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;

public interface IGithubUsersCache {
    Single<List<GithubUser>> getUsers();
    Completable putUsers(List<GithubUser> users);
}
