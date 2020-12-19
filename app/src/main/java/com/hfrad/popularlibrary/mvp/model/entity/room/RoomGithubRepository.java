package com.hfrad.popularlibrary.mvp.model.entity.room;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity (
        foreignKeys = {
                @ForeignKey(
                        entity = RoomGithubUser.class,
                        parentColumns = {"id"},
                        childColumns = {"userId"},
                        onDelete = ForeignKey.CASCADE
                )
        }
)
public class RoomGithubRepository {
    @PrimaryKey
    @NonNull
    public String id;
    public String name;
    public String type;
    public String userId;

    public RoomGithubRepository(@NonNull String id, String name, String type, String userId) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.userId = userId;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getUserId() {
        return userId;
    }
}
