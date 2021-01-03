package com.hfrad.popularlibrary.mvp.model.cache;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import com.hfrad.popularlibrary.mvp.model.entity.GithubRepository;
import com.hfrad.popularlibrary.mvp.model.entity.Number;

public interface IGithubRepositoriesCache {
    Single<List<GithubRepository>> getUserRepos(Number user);
    Completable putUserRepos(Number user, List<GithubRepository> repositories);
}
