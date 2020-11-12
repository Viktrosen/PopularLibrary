package com.hfrad.popularlibrary.mvp.presenter;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.R;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUserRepo;
import com.hfrad.popularlibrary.mvp.presenter.list.IUserListPresenter;
import com.hfrad.popularlibrary.mvp.view.UserItemView;
import com.hfrad.popularlibrary.mvp.view.UsersView;
import com.hfrad.popularlibrary.navigation.Screens;

import java.util.ArrayList;
import java.util.List;

import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView>  {
    private static final String TAG = UsersPresenter.class.getSimpleName();
    private static String login;

    public static String getLogin() {
        return login;
    }

    private static final boolean VERBOSE = true;

    private GithubUserRepo usersRepo = new GithubUserRepo();
    private Router router = GithubApplication.getApplication().getRouter();

    private class UsersListPresenter implements IUserListPresenter {

        private List<GithubUser> users = new ArrayList<>();

        @Override
        public void onItemClick(UserItemView view) {
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());
                login = usersRepo.getUsers().get(view.getPos()).getLogin();


                router.replaceScreen(new Screens.UserScreen());


                Log.v(TAG, " LOGINPUT " + users.get(view.getPos()).getLogin());
            }
        }

        @Override
        public void bindView(UserItemView view) {
            GithubUser user = users.get(view.getPos());
            view.setLogin(user.getLogin());
        }

        @Override
        public int getCount() {
            return users.size();
        }
    }

    private UsersListPresenter usersListPresenter = new UsersListPresenter();

    public UsersListPresenter getUsersListPresenter() {
        return usersListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();

    }

    private void loadData() {
        List<GithubUser> users = usersRepo.getUsers();
        usersListPresenter.users.addAll(users);
        getViewState().updateList();
    }

    public boolean backPressed() {
        router.exit();
        return true;

    }
}
