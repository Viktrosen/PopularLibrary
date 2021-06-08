package com.hfrad.popularlibrary.mvp.presenter;

import com.hfrad.popularlibrary.mvp.model.entity.Number;
import com.hfrad.popularlibrary.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import moxy.MvpPresenter;
import moxy.MvpView;
import ru.terrakok.cicerone.Router;

public class NumberPresenterTest {

    private NumberPresenter presenter;

    @Mock
    private Router router;

    @Mock
    private RetrofitGithubUsersRepo repo;

    @Mock
    private Scheduler scheduler;

    @Mock
    private MvpPresenter mvpPresenter;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        presenter = new NumberPresenter();

        presenter.usersRepo = repo;
        presenter.scheduler = scheduler;
        presenter.router = router;


    }

    @Test
    public void getUsersListPresenter() {
        Assert.assertNotNull(presenter.getUsersListPresenter());
    }


    @Test
    public void backPressed() {

        presenter.backPressed();
        Mockito.verify(router, Mockito.times(1)).exit();
    }

    @Test
    public void onDestroy() {
        presenter.onDestroy();
        Mockito.verify(mvpPresenter,Mockito.atLeast(1)).getViewState();

    }
}