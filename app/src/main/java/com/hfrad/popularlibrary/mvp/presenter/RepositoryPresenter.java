package com.hfrad.popularlibrary.mvp.presenter;

import javax.inject.Inject;

import moxy.MvpPresenter;
import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.mvp.model.entity.GithubRepository;
import com.hfrad.popularlibrary.mvp.view.RepositoryView;
import ru.terrakok.cicerone.Router;

public class RepositoryPresenter extends MvpPresenter<RepositoryView> {

    private final GithubRepository githubRepository;

    @Inject
    Router router;

    public RepositoryPresenter(GithubRepository githubRepository) {
        this.githubRepository = githubRepository;
        GithubApplication.INSTANCE.initRepositoriesSubcomponent().inject(this);
    }

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();

        getViewState().init();

        String id = githubRepository.getId();
        String title = githubRepository.getName();
        int forks = githubRepository.getForksCount();

        getViewState().setId(id != null ? id : "");
        getViewState().setTitle(title != null ? title : "");
        getViewState().setForksCount(String.valueOf(forks));
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}
