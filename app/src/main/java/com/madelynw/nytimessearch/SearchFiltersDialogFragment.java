package com.madelynw.nytimessearch;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.madelynw.nytimessearch.models.SearchFilters;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by madelynw on 6/23/16.
 */
public class SearchFiltersDialogFragment extends DialogFragment
        implements View.OnClickListener, DatePickerDialog.OnDateSetListener {
    // ...other views declared here...

    private SearchFilters mFilters;

    EditText etSelectDate;
    Button btnSave;
    CheckBox cbArts;
    CheckBox cbSports;
    CheckBox cbFashion;
    Spinner spSort;

    String newsDesks;
    String sortOrder;
    String beginDate;

    public SearchFiltersDialogFragment() {
        // Empty constructor is required for DialogFragment
        // Make sure not to add arguments to the constructor
        // Use `newInstance` instead as shown below
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup parent, Bundle savedInstanceState) {
        // Defines the xml file for the fragment
        return inflater.inflate(R.layout.fragment_filters, parent, false);
    }


    // SearchFiltersDialogFragment.newInstance(filters);
    public static SearchFiltersDialogFragment newInstance(SearchFilters filters) {
        SearchFiltersDialogFragment frag = new SearchFiltersDialogFragment();
        Bundle args = new Bundle();
        args.putParcelable("filters", filters);
        // or putParcelable(...) and then later getParcelable(...)
        frag.setArguments(args);
        return frag;
    }

    // 1. Defines the listener interface with a method
    //    passing back filters as result.
    public interface OnFilterSearchListener {
        void onUpdateFilters(SearchFilters filters);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        // Store the filters to a member variable
        mFilters = getArguments().getParcelable("filters");
        // ... any other view lookups here...

        etSelectDate = (EditText) view.findViewById(R.id.etSelectDate);
        etSelectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(view);
            }
        });

        spSort = (Spinner) view.findViewById(R.id.spSortOrder);

        cbArts = (CheckBox) view.findViewById(R.id.cbArts);
        cbFashion = (CheckBox) view.findViewById(R.id.cbFashion);
        cbSports = (CheckBox) view.findViewById(R.id.cbSports);

        // Get access to the button
        btnSave = (Button) view.findViewById(R.id.btnSave);
        // 2. Attach a callback when the button is pressed
        btnSave.setOnClickListener(this);
    }

    // This is fired the button in the activity is clicked
    // This is the time to apply the filters by
    // sending back to the search activity
    @Override
    public void onClick(View v) {
        // Update the mFilters based on the input views
        // ...

        Toast.makeText(getContext(), "test", Toast.LENGTH_SHORT).show();

        // Return filters back to activity through the implemented listener
        OnFilterSearchListener listener = (OnFilterSearchListener) getActivity();
        listener.onUpdateFilters(mFilters);
        // Close the dialog to return back to the parent activity
        dismiss();
    }

    public void showDatePickerDialog(View v) {
        DatePickerFragment newFragment = new DatePickerFragment();
        newFragment.setTargetFragment(this, 300);
        newFragment.show(getFragmentManager(), "date_picker");
    }

    // handle the date selected
    @Override
    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
    {
        // store the values selected into a Calendar instance
        final Calendar c = Calendar.getInstance();
        c.set(Calendar.YEAR, year);
        c.set(Calendar.MONTH, monthOfYear);
        c.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        // Get the beginDate here from the calendar parsed to correct format
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        String urlDate = format.format(c.getTime());
        // => "20160405"
        // Store this date into the filters object



    }

    public void onCheckboxClicked(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        // Check which checkbox was clicked
        switch(view.getId()) {
            case R.id.cbArts:
                if (checked) {
                    cbArts.setChecked(true);
                    //newsDesks = mFilters.getnewsDesks();
                }
                else {

                }
                break;
            case R.id.cbFashion:
                if (checked) {
                    cbFashion.setChecked(true);
                }
                else {

                }
                break;
            case R.id.cbSports:
                if (checked) {
                    cbSports.setChecked(true);
                }
                else {

                }
                break;
        }
    }
}