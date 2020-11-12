package com.hfrad.popularlibrary.mvp.presenter;

import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.mvp.view.MainView;
import com.hfrad.popularlibrary.navigation.Screens;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class MainPresenter extends MvpPresenter<MainView> {
    private Router router = GithubApplication.getApplication().getRouter();

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
