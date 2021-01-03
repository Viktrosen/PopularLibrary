package com.hfrad.popularlibrary.mvp.model.repo;

import io.reactivex.rxjava3.core.Single;

import com.hfrad.popularlibrary.mvp.model.entity.Number;

public interface IGithubUsersRepo {
    Single <Number> getCharacters();
}
