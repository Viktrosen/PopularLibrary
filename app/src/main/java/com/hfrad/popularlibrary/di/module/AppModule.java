package com.hfrad.popularlibrary.di.module;

import dagger.Module;
import dagger.Provides;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import com.hfrad.popularlibrary.GithubApplication;

@Module
public class AppModule {
    private GithubApplication app;

    public AppModule(GithubApplication app) {
        this.app = app;
    }

    @Provides
    public GithubApplication app() {
        return app;
    }

    @Provides
    public Scheduler mainThreadScheduler() {
        return AndroidSchedulers.mainThread();
    }
}
