package com.hfrad.popularlibrary.mvp.presenter;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


import com.hfrad.popularlibrary.GithubApplication;
import com.hfrad.popularlibrary.R;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUserRepo;
import com.hfrad.popularlibrary.mvp.presenter.list.IUserListPresenter;
import com.hfrad.popularlibrary.mvp.view.UserItemView;
import com.hfrad.popularlibrary.mvp.view.UsersView;
import com.hfrad.popularlibrary.navigation.Screens;
import com.hfrad.popularlibrary.ui.fragments.UsersFragment;

import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import moxy.MvpPresenter;
import ru.terrakok.cicerone.Router;
import io.reactivex.rxjava3.core.Observable;

public class UsersPresenter extends MvpPresenter<UsersView>  {

    private GithubUserRepo repos = new GithubUserRepo();
    private List<String> logins = new ArrayList<>();

    boolean success=false;

    public String getImage() {
        return image;
    }

    private String image;

    public void convert() throws IOException {
        if (UsersFragment.isViewed){
            Bitmap bmp = BitmapFactory.decodeFile(image);
            File convertedImage = new File(Environment.getExternalStorageDirectory()+"/leopard.png");
            FileOutputStream outStream=new FileOutputStream(convertedImage);
            success=bmp.compress(Bitmap.CompressFormat.PNG, 100, outStream);
            outStream.flush();
            outStream.close();
        }
    }


    private final Observer userObserver = (Observer)(new Observer() {
        @Nullable
        private Disposable disposable;

        @Nullable
        public final Disposable getDisposable() {
            return this.disposable;
        }

        public final void setDisposable(@Nullable Disposable var1) {
            this.disposable = var1;
        }

        public void onComplete() {
            Log.d("Consumer", "onComplete: ");
        }

        public void onSubscribe(@Nullable Disposable d) {
            this.disposable = d;
            Log.d("Consumer", "onSubscribe: ");
        }

        public void onNext(@Nullable GithubUser s) {
            image = s.getImage();
            Log.d("Consumer", "onNext: " +image);


        }


        public void onNext(Object var1) {
            this.onNext((GithubUser)var1);
        }

        public void onError(@Nullable Throwable e) {
            Log.d("Consumer", "onError: " + (e != null ? e.getMessage() : null));
        }
    });



    private static final String TAG = UsersPresenter.class.getSimpleName();
    private static String login;



    public static String getLogin() {
        return login;
    }

    private static final boolean VERBOSE = true;

    private GithubUserRepo usersRepo = new GithubUserRepo();
    private Router router = GithubApplication.getApplication().getRouter();

    private class UsersListPresenter implements IUserListPresenter {

        private List<GithubUser> users = new ArrayList<>();

        @Override
        public void onItemClick(UserItemView view) {
            if (VERBOSE) {
                Log.v(TAG, " onItemClick " + view.getPos());
                login = logins.get(view.getPos());
                //login = usersRepo.getUsers().get(view.getPos()).getLogin();
                router.replaceScreen(new Screens.UserScreen());
                Log.v(TAG, " LOGINPUT " + users.get(view.getPos()).getLogin());
            }
        }


        @Override
        public void bindView(UserItemView view) {
            repos.fromCallable().subscribe(userObserver);
            GithubUser user = users.get(view.getPos());
            view.setLogin(user.getLogin());
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
       // List<GithubUser> users = usersRepo.getUsers();
        //usersListPresenter.users.addAll(users);
        getViewState().updateList();
    }

    public boolean backPressed() {
        router.exit();
        return true;

    }
}
