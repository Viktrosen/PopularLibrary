package com.hfrad.popularlibrary.mvp.presenter;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Scheduler;
import moxy.MvpPresenter;
import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.mvp.model.entity.GithubRepository;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubRepositoriesRepo;
import com.hfrad.popularlibrary.mvp.presenter.list.IRepositoryListPresenter;
import com.hfrad.popularlibrary.mvp.view.UserView;
import com.hfrad.popularlibrary.mvp.view.list.RepositoryItemView;
import com.hfrad.popularlibrary.navigation.Screens;
import ru.terrakok.cicerone.Router;

public class UserPresenter extends MvpPresenter<UserView> {
    private static final String TAG = UserPresenter.class.getSimpleName();

    private static final boolean VERBOSE = true;
    @Inject
    IGithubRepositoriesRepo githubRepositoriesRepo;
    @Inject
    Router router;
    @Inject
    Scheduler scheduler;

    private final GithubUser user;

    public UserPresenter(GithubUser user) {
        this.user = user;
        GithubApplication.INSTANCE.initRepositoriesSubcomponent().inject(this);
    }

    private class RepositoriesListPresenter implements IRepositoryListPresenter {
        private List<GithubRepository> repositories = new ArrayList<>();

        @Override
        public void onItemClick(RepositoryItemView view) {
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());
            }

            final GithubRepository repository = repositories.get(view.getPos());
            router.navigateTo(new Screens.RepositoryScreen(repository));
        }

        @Override
        public void bindView(RepositoryItemView view) {
            GithubRepository repository = repositories.get(view.getPos());
            view.setName(repository.getName());
        }

        @Override
        public int getCount() {
            return repositories.size();
        }
    }

    private UserPresenter.RepositoriesListPresenter repositoriesListPresenter = new UserPresenter.RepositoriesListPresenter();

    public IRepositoryListPresenter getPresenter() {
        return repositoriesListPresenter;
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();
        loadData();
    }

    private void loadData() {
        githubRepositoriesRepo.getRepositories(user).observeOn(scheduler).subscribe(repositories-> {
            repositoriesListPresenter.repositories.clear();
            repositoriesListPresenter.repositories.addAll(repositories);
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
