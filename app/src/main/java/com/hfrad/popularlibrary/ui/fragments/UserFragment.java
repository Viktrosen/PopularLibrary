package com.hfrad.popularlibrary.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;
import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.R;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.room.Database;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubRepositoriesRepo;
import com.hfrad.popularlibrary.mvp.model.repo.retrofit.RetrofitGithubRepositoriesRepo;
import com.hfrad.popularlibrary.mvp.presenter.UserPresenter;
import com.hfrad.popularlibrary.mvp.view.UserView;
import com.hfrad.popularlibrary.ui.BackButtonListener;
import com.hfrad.popularlibrary.ui.adapter.ReposotoriesRVAdapter;
import com.hfrad.popularlibrary.ui.network.AndroidNetworkStatus;
import ru.terrakok.cicerone.Router;

public class UserFragment extends MvpAppCompatFragment implements UserView, BackButtonListener {
    private static final String USER_ARG = "user";

    private RecyclerView mRecyclerView;
    private ReposotoriesRVAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private View mView;

    @InjectPresenter
    UserPresenter mPresenter;

    @ProvidePresenter
    UserPresenter provideUserPresenter() {
        final GithubUser user = getArguments().getParcelable(USER_ARG);

        IGithubRepositoriesRepo githubRepositoriesRepo = new RetrofitGithubRepositoriesRepo(GithubApplication.INSTANCE.getApi(),
                new AndroidNetworkStatus(),
                Database.getInstance());

        Router router = GithubApplication.getApplication().getRouter();

        return new UserPresenter(user, AndroidSchedulers.mainThread(), githubRepositoriesRepo, router);
    }

    public static UserFragment newInstance(GithubUser user) {
        UserFragment fragment = new UserFragment();

        Bundle args = new Bundle();
        args.putParcelable(USER_ARG, user);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_user, container, false);

        mRecyclerView = (RecyclerView)mView.findViewById(R.id.rv_repositories);

        return mView;
    }

    @Override
    public void init() {
        mLayoutManager = new LinearLayoutManager(mView.getContext());

        mAdapter = new ReposotoriesRVAdapter(mPresenter.getPresenter());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void updateList() {
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public boolean backPressed() {
        return mPresenter.backPressed();
    }
}
