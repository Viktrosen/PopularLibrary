package com.hfrad.popularlibrary.ui.fragments;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.hfrad.popularlibrary.R;
import com.hfrad.popularlibrary.mvp.presenter.UsersPresenter;
import com.hfrad.popularlibrary.mvp.view.UsersView;
import com.hfrad.popularlibrary.ui.BackButtonListener;
import com.hfrad.popularlibrary.ui.adapter.UserRVAdapter;

import java.io.IOException;

import moxy.MvpAppCompatFragment;
import moxy.presenter.InjectPresenter;


public class UsersFragment extends MvpAppCompatFragment implements UsersView, BackButtonListener {
    public static boolean isViewed = false;
    private ImageView imageView;
    private RecyclerView recyclerView;
    private UserRVAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;

    private View view;

    @InjectPresenter
    UsersPresenter usersPresenter;

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

        imageView = view.findViewById(R.id.imageView1);
        imageView.setImageDrawable(Drawable.createFromPath(usersPresenter.getImage()));
        isViewed = true;
        try {
            usersPresenter.convert();
        } catch (IOException e) {
            e.printStackTrace();
        }


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
