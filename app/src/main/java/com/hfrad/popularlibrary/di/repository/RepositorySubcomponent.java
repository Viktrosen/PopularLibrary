package com.hfrad.popularlibrary.di.repository;

import dagger.Subcomponent;
import com.hfrad.popularlibrary.di.repository.module.RepositoryModule;
import com.hfrad.popularlibrary.mvp.presenter.RepositoryPresenter;
import com.hfrad.popularlibrary.mvp.presenter.UserPresenter;

@RepositoryScope
@Subcomponent (
        modules = {
                RepositoryModule.class
        }
)
public interface RepositorySubcomponent {
    void inject(UserPresenter userPresenter);
    void inject(RepositoryPresenter repoPresenter);
}
