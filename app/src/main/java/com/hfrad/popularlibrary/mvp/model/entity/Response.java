package com.hfrad.popularlibrary.mvp.model.entity;

import java.util.ArrayList;
import java.util.List;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Response implements Parcelable {

    @SerializedName("info")
    @Expose
    private Info info;
    @SerializedName("results")
    @Expose
    private List<Result> results = new ArrayList<>();
    public final static Creator<Response> CREATOR = new Creator<Response>() {

        public Response createFromParcel(Parcel in) {
            return new Response(in);
        }

        public Response[] newArray(int size) {
            return (new Response[size]);
        }

    };

    protected Response(Parcel in) {
        this.info = ((Info) in.readValue((Info.class.getClassLoader())));
        in.readList(this.results, (Result.class.getClassLoader()));
    }

    /**
     * No args constructor for use in serialization
     *
     */
    public Response() {
    }

    /**
     *
     * @param results
     * @param info
     */
    public Response(Info info, List<Result> results) {
        super();
        this.info = info;
        this.results = results;
    }

    public Info getInfo() {
        return info;
    }

    public void setInfo(Info info) {
        this.info = info;
    }

    public List<Result> getResults() {
        return results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(info);
        dest.writeList(results);
    }

    public int describeContents() {
        return 0;
    }

}
