package com.hfrad.popularlibrary.mvp.model.entity;

import android.media.Image;
import android.widget.ImageView;

import com.hfrad.popularlibrary.tests.Creation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;

public class GithubUserRepo {
    static private String image = "C:\\Users\\Viktor\\AndroidStudioProjects\\PopularLibrary\\app\\src\\main\\res\\leopard.jpeg";
    /*private List<GithubUser> repositories = new ArrayList<>(Arrays.asList(
            new GithubUser("login1"),
            new GithubUser("login2"),
            new GithubUser("login3"),
            new GithubUser("login4"),
            new GithubUser("login5")));*/

    /*public Observable<GithubUser> just(){
        return Observable.just(new GithubUser("login1"),
                new GithubUser("login2"),
                new GithubUser("login3"),
                new GithubUser("login4"),
                new GithubUser("login5"));
    }*/
    public Observable<GithubUser> just(){
        return Observable.just(new GithubUser("login1",image));}

   public Observable fromCallable() {
        return Observable.fromCallable(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return GithubUserRepo.randomResult();
            }
        });
    }

    static String randomResult() {
        return image;
    }

    /*public List<GithubUser> getUsers() {
        return Collections.unmodifiableList(repositories);
    }*/
}
