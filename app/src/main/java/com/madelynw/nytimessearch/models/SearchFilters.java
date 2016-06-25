package com.madelynw.nytimessearch.models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.madelynw.nytimessearch.R;

/**
 * Created by madelynw on 6/23/16.
 */


public class SearchFilters implements Parcelable {

    String beginDate;
    String newsDesks;
    String sortOrder;

    public String getBeginDate() {
        return beginDate;
    }

    public String getNewsDesks() {
        return newsDesks;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public SearchFilters() {
        try {

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.beginDate);
        dest.writeString(this.newsDesks);
        dest.writeString(this.sortOrder);
    }

    protected SearchFilters(Parcel in) {
        this.beginDate = in.readString();
        this.newsDesks = in.readString();
        this.sortOrder = in.readString();
    }

    public static final Creator<SearchFilters> CREATOR = new Creator<SearchFilters>() {
        @Override
        public SearchFilters createFromParcel(Parcel source) {
            return new SearchFilters(source);
        }

        @Override
        public SearchFilters[] newArray(int size) {
            return new SearchFilters[size];
        }
    };
}