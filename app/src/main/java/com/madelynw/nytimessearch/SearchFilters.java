package com.madelynw.nytimessearch;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;

import java.util.ArrayList;

/**
 * Created by madelynw on 6/23/16.
 */


public class SearchFilters implements Parcelable {

    String beginDate;
    String newsDesks;

    /**
    Button save;

    Spinner spinner; // = (Spinner) findViewById(R.id.spSortOrder);
    String value = spinner.getSelectedItem().toString();

    Spinner spinner = (Spinner) findViewById(R.id.spSortOrder);
    // Create an ArrayAdapter using the string array and a default spinner layout
    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
            R.array.sort, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
    spinner.setAdapter(adapter);



    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cbArts:
                if (checked)
                // Put some meat on the sandwich
                else
                // Remove the meat
                break;
            case R.id.cbFashion:
                if (checked)
                // Cheese me
                else
                // I'm lactose intolerant
                break;
            case R.id.cbSports:
                if (checked)
                // Cheese me
                else
                // I'm lactose intolerant
                break;
        }
    }
    */
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.beginDate);
        dest.writeString(this.newsDesks);
    }

    public SearchFilters() {
    }

    protected SearchFilters(Parcel in) {
        this.beginDate = in.readString();
        this.newsDesks = in.readString();
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