package com.hfrad.popularlibrary.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Thumbnail implements Parcelable {
    @Expose private String path;
    @Expose private String extension;
    private String imgUrl;

    public String getImgUrl() {
        return imgUrl;
    }

    public Thumbnail(String path, String extension) {
        this.path = path;
        this.extension = extension;
        this.imgUrl = this.path+"."+this.extension;
    }

    protected Thumbnail(Parcel in) {
        path = in.readString();
        extension = in.readString();
        imgUrl = path+"."+extension;


    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(path);
        dest.writeString(extension);

    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<Thumbnail> CREATOR = new Parcelable.Creator<Thumbnail>() {
        @Override
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }

        @Override
        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
