package com.hfrad.popularlibrary.di;

import javax.inject.Singleton;

import dagger.Component;
import com.hfrad.popularlibrary.MainActivity;
import com.hfrad.popularlibrary.di.module.ApiModule;
import com.hfrad.popularlibrary.di.module.AppModule;
import com.hfrad.popularlibrary.di.module.CacheModule;
import com.hfrad.popularlibrary.di.module.CiceroneModule;
import com.hfrad.popularlibrary.di.module.ImageModule;
import com.hfrad.popularlibrary.di.user.UsersSubcomponent;
import com.hfrad.popularlibrary.mvp.presenter.MainPresenter;

@Singleton
@Component (
        modules = {
                ApiModule.class,
                AppModule.class,
                CacheModule.class,
                CiceroneModule.class,
                ImageModule.class
        }
)

public interface AppComponent {
    UsersSubcomponent userSubComponent();

    void inject(MainActivity mainActivity);
    void inject(MainPresenter mainPresenter);
}
