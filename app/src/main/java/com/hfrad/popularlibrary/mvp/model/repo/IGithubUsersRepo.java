package com.hfrad.popularlibrary.mvp.model.repo;

import java.util.List;

import io.reactivex.rxjava3.core.Single;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;

public interface IGithubUsersRepo {
    Single<List<GithubUser>> getUsers();
}
