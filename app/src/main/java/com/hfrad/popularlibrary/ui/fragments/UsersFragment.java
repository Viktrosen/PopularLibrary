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
import com.hfrad.popularlibrary.mvp.model.entity.room.Database;
import com.hfrad.popularlibrary.mvp.model.repo.IGithubUsersRepo;
import com.hfrad.popularlibrary.mvp.model.repo.retrofit.RetrofitGithubUsersRepo;
import com.hfrad.popularlibrary.mvp.presenter.UsersPresenter;
import com.hfrad.popularlibrary.mvp.view.UsersView;
import com.hfrad.popularlibrary.ui.BackButtonListener;
import com.hfrad.popularlibrary.ui.adapter.UserRVAdapter;
import com.hfrad.popularlibrary.ui.network.AndroidNetworkStatus;
import ru.terrakok.cicerone.Router;

public class UsersFragment extends MvpAppCompatFragment implements UsersView, BackButtonListener {

    private RecyclerView recyclerView;
    private UserRVAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private View view;

    @InjectPresenter
    UsersPresenter usersPresenter;

    @ProvidePresenter
    UsersPresenter provideUsersPresenter() {
        IGithubUsersRepo usersRepo = new RetrofitGithubUsersRepo(GithubApplication.INSTANCE.getApi(),
                new AndroidNetworkStatus(),
                Database.getInstance());
        Router router = GithubApplication.getApplication().getRouter();

        return new UsersPresenter(AndroidSchedulers.mainThread(), usersRepo, router);
    }

    public static UsersFragment getInstance(int data) {
        UsersFragment fragment = new UsersFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("key", data);

        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();

        if (bundle != null) {
            // запоминаем
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_users, container, false);

        recyclerView = (RecyclerView)view.findViewById(R.id.rv_users);

        return view;
    }

    @Override
    public void init() {
        layoutManager = new LinearLayoutManager(view.getContext());
        adapter = new UserRVAdapter(usersPresenter.getUsersListPresenter());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void updateList() {
        adapter.notifyDataSetChanged();
    }

    @Override
    public boolean backPressed() {
        return usersPresenter.backPressed();
    }
}
