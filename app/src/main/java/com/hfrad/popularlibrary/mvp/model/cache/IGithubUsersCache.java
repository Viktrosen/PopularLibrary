package com.hfrad.popularlibrary.mvp.model.cache;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;

import com.hfrad.popularlibrary.mvp.model.entity.Number;

public interface IGithubUsersCache {
    Single<Number> getUsers();
    Completable putUsers(Number users);
}

