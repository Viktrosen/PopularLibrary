package com.hfrad.popularlibrary.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Data implements Parcelable {
    @Expose private int offset;
    @Expose private int limit;
    @Expose private int total;
    @Expose private int count;
    @SerializedName("results")
    @Expose private List<Result> result = new ArrayList<>();

    public Data(int offset, int limit, int total, int count, List<Result> result) {
        this.offset = offset;
        this.limit = limit;
        this.total = total;
        this.count = count;
        this.result = result;
    }

    protected Data(Parcel in) {
        offset = in.readInt();
        limit = in.readInt();
        total = in.readInt();
        count = in.readInt();
        in.readList(this.result, Result.class.getClassLoader());

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(offset);
        dest.writeInt(limit);
        dest.writeInt(total);
        dest.writeInt(count);
        dest.writeList(result);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Data> CREATOR = new Creator<Data>() {
        @Override
        public Data createFromParcel(Parcel in) {
            return new Data(in);
        }

        @Override
        public Data[] newArray(int size) {
            return new Data[size];
        }
    };

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<Result> getResult() {
        return result;
    }

    public void setResult(List<Result> result) {
        this.result = result;
    }
}
