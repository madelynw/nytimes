package com.madelynw.nytimessearch.models;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by madelynw on 6/24/16.
 */
public class TopArticle implements Parcelable {

    String title;
    String url;
    String thumbnail;

    public String getTitle() { return title; }

    public String getUrl() {return url; }

    public String getThumbnail() {
        return thumbnail;
    }

    public TopArticle (JSONArray jsonArray) {
        try {
            this.url = jsonArray.getJSONObject(4).toString();

            this.title = jsonArray.getJSONObject(2).toString();

            JSONArray multimedia = jsonArray.getJSONArray(16);

            if (multimedia.length() > 0) {
                JSONObject multimediaJson = multimedia.getJSONObject(0);
                //JSONObject multimediaJson = multimedia.getJSONObject(new Random().nextInt(multimedia.length()));
                this.thumbnail = "http://www.nytimes.com/" + multimediaJson.getString("url");
            } else {
                this.thumbnail = "";
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public static ArrayList<Article> fromJSONArray (JSONArray array) {
        ArrayList<Article> results = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            try {
                results.add(new Article(array.getJSONObject(i)));

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.url);
        dest.writeString(this.thumbnail);
    }

    protected TopArticle(Parcel in) {
        this.title = in.readString();
        this.url = in.readString();
        this.thumbnail = in.readString();
    }

    public static final Creator<TopArticle> CREATOR = new Creator<TopArticle>() {
        @Override
        public TopArticle createFromParcel(Parcel source) {
            return new TopArticle(source);
        }

        @Override
        public TopArticle[] newArray(int size) {
            return new TopArticle[size];
        }
    };
}
