package com.hfrad.popularlibrary.di.user;

import dagger.Subcomponent;
import com.hfrad.popularlibrary.di.repository.RepositorySubcomponent;
import com.hfrad.popularlibrary.di.user.module.UsersModule;
import com.hfrad.popularlibrary.mvp.presenter.NumberPresenter;
import com.hfrad.popularlibrary.ui.adapter.UserRVAdapter;

@UsersScope
@Subcomponent(
        modules = {
                UsersModule.class
        }
)
public interface UsersSubcomponent {
    RepositorySubcomponent repositorySubComponent();

    void inject(NumberPresenter numberPresenter);
    void inject(UserRVAdapter adapter);
}
