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
        String type = githubRepository.getType();

        getViewState().setId(id != null ? id : "");
        getViewState().setTitle(title != null ? title : "");
        getViewState().setType(type != null ? type : "");
    }

    public boolean backPressed() {
        router.exit();
        return true;
    }
}