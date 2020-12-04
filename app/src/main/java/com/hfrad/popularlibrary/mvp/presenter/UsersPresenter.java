package com.hfrad.popularlibrary.mvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubUsersRepo;
import com.hfrad.popularlibrary.mvp.presenter.list.IUserListPresenter;
import com.hfrad.popularlibrary.mvp.view.UsersView;
import com.hfrad.popularlibrary.mvp.view.list.UserItemView;
import com.hfrad.popularlibrary.navigation.Screens;
import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView>  {
    private static final String TAG = UsersPresenter.class.getSimpleName();

    private static final boolean VERBOSE = true;

    @Inject
    Router router;
    @Inject
    IGithubUsersRepo usersRepo;
    @Inject
    Scheduler scheduler;

    public UsersPresenter() {
        GithubApplication.INSTANCE.initUserSubcomponent().inject(this);
    }

    private class UsersListPresenter implements IUserListPresenter {

        private List<GithubUser> users = new ArrayList<>();

        @Override
        public void onItemClick(UserItemView view) {
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());
            }

            GithubUser user = users.get(view.getPos());
            router.navigateTo(new Screens.UserScreen(user));
        }

        @Override
        public void bindView(UserItemView view) {
            GithubUser user = users.get(view.getPos());
            view.setLogin(user.getLogin());
            view.loadAvatar(user.getAvatarUrl());
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
        usersRepo.getUsers().observeOn(scheduler).subscribe(repos -> {
            usersListPresenter.users.clear();
            usersListPresenter.users.addAll(repos);
            getViewState().updateList();
        }, (e) -> {
            Log.w(TAG, "Error" + e.getMessage());
        });
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        getViewState().release();
    }
}
