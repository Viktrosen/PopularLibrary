package com.hfrad.popularlibrary.mvp.model.entity.room.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import com.hfrad.popularlibrary.mvp.model.entity.room.RoomGithubUser;

@Dao
public interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(RoomGithubUser user);
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<RoomGithubUser> users);

    @Update
    void update(RoomGithubUser user);
    @Update
    void update(List<RoomGithubUser> users);

    @Delete
    void delete(RoomGithubUser user);
    @Delete
    void delete(List<RoomGithubUser> users);

    @Query("SELECT * FROM RoomGithubUser")
    RoomGithubUser getAll();

    @Query("SELECT * FROM RoomGithubUser WHERE login = :login LIMIT 1")
    RoomGithubUser findByLogin(String login);
}
