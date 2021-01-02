package com.hfrad.popularlibrary.mvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.Result;
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
        private List<Result> characters = new ArrayList<>();

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
            Result character = characters.get(view.getPos());
            view.setLogin(character.getName());
            view.loadAvatar(character.getImage());
        }

        @Override
        public int getCount() {
            return characters.size();
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
        usersRepo.getCharacters().observeOn(scheduler).subscribe(repos -> {
            usersListPresenter.characters.clear();
            usersListPresenter.characters.addAll(repos.getResults());
            getViewState().updateList();
        }, (e) -> {
            Log.w(TAG, "Error: " + e.getMessage());
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
