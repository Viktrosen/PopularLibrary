package com.hfrad.popularlibrary.mvp.model.entity;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Info implements Parcelable{
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("pages")
    @Expose
    private int pages;
    @SerializedName("next")
    @Expose
    private String next;
    @SerializedName("prev")
    @Expose
    private Object prev;
    public final static Parcelable.Creator<Info> CREATOR = new Creator<Info>() {


        @SuppressWarnings({
                "unchecked"
        })
        public Info createFromParcel(Parcel in) {
            return new Info(in);
        }

        public Info[] newArray(int size) {
            return (new Info[size]);
        }

    }
            ;

    protected Info(Parcel in) {
        this.count = ((int) in.readValue((int.class.getClassLoader())));
        this.pages = ((int) in.readValue((int.class.getClassLoader())));
        this.next = ((String) in.readValue((String.class.getClassLoader())));
        this.prev = ((Object) in.readValue((Object.class.getClassLoader())));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Info() {
    }

    /**
     *
     * @param next
     * @param pages
     * @param prev
     * @param count
     */
    public Info(int count, int pages, String next, Object prev) {
        super();
        this.count = count;
        this.pages = pages;
        this.next = next;
        this.prev = prev;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public Object getPrev() {
        return prev;
    }

    public void setPrev(Object prev) {
        this.prev = prev;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(count);
        dest.writeValue(pages);
        dest.writeValue(next);
        dest.writeValue(prev);
    }

    public int describeContents() {
        return 0;
    }
}
