package com.hfrad.popularlibrary.mvp.model.cache.room;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;
import com.hfrad.popularlibrary.mvp.model.cache.IGithubRepositoriesCache;
import com.hfrad.popularlibrary.mvp.model.entity.GithubRepository;
import com.hfrad.popularlibrary.mvp.model.entity.GithubUser;
import com.hfrad.popularlibrary.mvp.model.entity.Result;
import com.hfrad.popularlibrary.mvp.model.entity.room.Database;
import com.hfrad.popularlibrary.mvp.model.entity.room.RoomGithubRepository;
import com.hfrad.popularlibrary.mvp.model.entity.room.RoomGithubUser;

public class RoomGithubRepositoriesCache implements IGithubRepositoriesCache {
    private final Database db;

    public RoomGithubRepositoriesCache(Database db) {
        this.db = db;
    }

    @Override
    public Single<List<GithubRepository>> getUserRepos(Result user) {
        return Single.fromCallable(()-> {

            RoomGithubUser roomUser = db.userDao().findByLogin(user.getName());

            if (roomUser == null) {
                throw new RuntimeException("No such user in cache");
            }

            List<RoomGithubRepository> roomGithubRepository = db.repositoryDao().findByUser(roomUser.getId());

            List<GithubRepository> githubRepositoryList = new ArrayList<>();

            for (RoomGithubRepository roomGithubrepository : roomGithubRepository) {
                GithubRepository githubRepository = new GithubRepository(roomGithubrepository.getId(),
                        roomGithubrepository.getName(),
                        roomGithubrepository.getType());

                githubRepositoryList.add(githubRepository);
            }

            return githubRepositoryList;
        });
    }

    @Override
    public Completable putUserRepos(Result user, List<GithubRepository> repositories) {
        return Completable.fromAction(()->{
            RoomGithubUser roomUser = db.userDao().findByLogin(user.getName());

            List<RoomGithubRepository> roomGithubRepositories = new ArrayList<>();

            for (GithubRepository repo: repositories) {
                RoomGithubRepository roomRepo = new RoomGithubRepository(repo.getId(), repo.getName(), repo.getType(), roomUser.getId());
                roomGithubRepositories.add(roomRepo);
            }

            db.repositoryDao().insert(roomGithubRepositories);
        }).subscribeOn(Schedulers.io());
    }
}
