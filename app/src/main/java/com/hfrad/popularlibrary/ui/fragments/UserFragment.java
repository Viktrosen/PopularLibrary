package com.hfrad.popularlibrary.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.R;
import com.hfrad.popularlibrary.mvp.presenter.UsersPresenter;
import com.hfrad.popularlibrary.mvp.view.UsersView;
import com.hfrad.popularlibrary.ui.BackButtonListener;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;
import moxy.presenter.ProvidePresenter;


public class UserFragment extends MvpAppCompatFragment implements UsersView, BackButtonListener {

    private View view;

    private TextView textViewLogin;
    private TextView textViewRepos;

    public static String login = "";
    public static String repo = "";

    @InjectPresenter
    UsersPresenter userPresenter;

    @ProvidePresenter
    UsersPresenter provideUsersPresenter() {
        return new UsersPresenter(AndroidSchedulers.mainThread());
    }


    public static UserFragment getInstance(int data) {
        UserFragment fragment = new UserFragment();

        Bundle bundle = new Bundle();
        bundle.putInt("key", data);





        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);


        Log.v("TAG", " LOGINGET " + login);

        textViewLogin = (TextView)view.findViewById(R.id.login);
        textViewLogin.setText(login);

        textViewRepos = (TextView)view.findViewById(R.id.repos_url);
        textViewRepos.setText(repo);

        return view;
    }



    @Override
    public boolean backPressed() {
        return userPresenter.backPressed();
    }

    @Override
    public void init() {

    }

    @Override
    public void updateList() {

    }
}