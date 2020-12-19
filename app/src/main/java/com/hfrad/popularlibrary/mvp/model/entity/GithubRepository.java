package com.hfrad.popularlibrary.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class GithubRepository implements Parcelable {
    @Expose private String id;
    @Expose private String name;
    @Expose private String type;

    public GithubRepository(String id, String name, String type) {
        this.id = id;
        this.name = name;
        this.type = type;
    }

    protected GithubRepository(Parcel in) {
        id = in.readString();
        name = in.readString();
        type = in.readString();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getForksCount() {
        return type;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(name);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<GithubRepository> CREATOR = new Creator<GithubRepository>() {
        @Override
        public GithubRepository createFromParcel(Parcel in) {
            return new GithubRepository(in);
        }

        @Override
        public GithubRepository[] newArray(int size) {
            return new GithubRepository[size];
        }
    };
}
