package com.hfrad.popularlibrary.mvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUserRepo;
import com.hfrad.popularlibrary.mvp.model.entity.Repos;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubUsersRepo;
import com.hfrad.popularlibrary.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;
import com.hfrad.popularlibrary.mvp.presenter.list.IUserListPresenter;
import com.hfrad.popularlibrary.mvp.view.UserItemView;
import com.hfrad.popularlibrary.mvp.view.UsersView;
import com.hfrad.popularlibrary.navigation.Screens;
import com.hfrad.popularlibrary.ui.fragments.UserFragment;

import ru.terrakok.cicerone.Router;

public class UsersPresenter extends MvpPresenter<UsersView>  {
    private static final String TAG = UsersPresenter.class.getSimpleName();

    private static final boolean VERBOSE = true;

    private String login = "";



    private Router router = GithubApplication.getApplication().getRouter();

    private final IGithubUsersRepo usersRepo;

    private final Scheduler scheduler;

    public UsersPresenter(Scheduler scheduler) {
        this.scheduler = scheduler;

        this.usersRepo = new RetrofitGithubUsersRepo(GithubApplication.INSTANCE.getApi());
    }

    private class UsersListPresenter implements IUserListPresenter {

        private List<GithubUser> users = new ArrayList<>();


        @Override
        public void onItemClick(UserItemView view) {

            UserFragment.login = users.get(view.getPos()).getLogin();
            UserFragment.repo = users.get(view.getPos()).getRepos().get(0).getName();
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());

                Log.v(TAG, " onItemClick " + login);
            }


            router.navigateTo(new Screens.UserScreen());

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
        //List<GithubUser> users = usersRepo.getUsers();

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
}

