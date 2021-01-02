package com.hfrad.popularlibrary.mvp.model.cache;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import com.hfrad.popularlibrary.mvp.model.entity.Response;

public interface IGithubUsersCache {
    Single<Response> getUsers();
    Completable putUsers(Response users);
}
