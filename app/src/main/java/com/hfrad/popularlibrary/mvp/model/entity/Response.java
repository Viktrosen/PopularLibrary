package com.hfrad.popularlibrary.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;

public class Response implements Parcelable {
    @Expose String etag;
    @Expose Data data;

    public Response(String etag, Data data) {
        this.etag = etag;
        this.data = data;
    }

    protected Response(Parcel in) {
        etag = in.readString();
        data = (Data) in.readValue(Data.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(etag);
        dest.writeValue(data);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Response> CREATOR = new Creator<Response>() {
        @Override
        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        @Override
        public Response[] newArray(int size) {
            return new Response[size];
        }
    };

    public String getEtag() {
        return etag;
    }

    public void setEtag(String etag) {
        this.etag = etag;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
