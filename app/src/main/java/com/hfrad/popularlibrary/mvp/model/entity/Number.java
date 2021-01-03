package com.hfrad.popularlibrary.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Number implements Parcelable {
    @Expose private String text;
    @Expose private String number;
    @Expose private String found;
    @Expose private String type;


    public Number(String id, String login, String avatar, String location) {
        this.text = id;
        this.number = login;
        this.found = avatar;
        this.type = location;

    }

    protected Number(Parcel in) {
        text = in.readString();
        number = in.readString();
        found = in.readString();
        type = in.readString();

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(text);
        dest.writeString(number);
        dest.writeString(found);
        dest.writeString(type);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Number> CREATOR = new Creator<Number>() {
        @Override
        public Number createFromParcel(Parcel in) {
            return new Number(in);
        }

        @Override
        public Number[] newArray(int size) {
            return new Number[size];
        }
    };

    public String getLogin() {
        return text;
    }

    public String getId() {
        return number;
    }

    public String getAvatarUrl() {
        return found;
    }

    public String getLocUrl() {
        return type;
    }


}
