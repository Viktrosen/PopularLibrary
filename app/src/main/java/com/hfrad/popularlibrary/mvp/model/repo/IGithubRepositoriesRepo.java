package com.hfrad.popularlibrary.mvp.model.repo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import com.hfrad.popularlibrary.mvp.model.entity.GithubRepository;
import com.hfrad.popularlibrary.mvp.model.entity.Number;

public interface IGithubRepositoriesRepo {
    Single<List<GithubRepository>> getRepositories(Number user);
}
