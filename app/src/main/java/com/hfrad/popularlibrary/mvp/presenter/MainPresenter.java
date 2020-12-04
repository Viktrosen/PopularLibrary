package com.hfrad.popularlibrary.mvp.presenter;

import javax.inject.Inject;

import moxy.MvpPresenter;
import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.mvp.view.MainView;
import com.hfrad.popularlibrary.navigation.Screens;
import ru.terrakok.cicerone.Router;

public class MainPresenter extends MvpPresenter<MainView> {

    @Inject
    Router router;

    public MainPresenter() {
        super();

        GithubApplication.INSTANCE.getAppComponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        // TODO: Nothing to do

        router.replaceScreen(new Screens.UsersScreen());
    }

    public void backClicked() {
        router.exit();
    }

}
