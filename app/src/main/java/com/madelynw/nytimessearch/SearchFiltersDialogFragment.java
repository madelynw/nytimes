package com.madelynw.nytimessearch;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.madelynw.nytimessearch.models.SearchFilters;

/**
 * Created by madelynw on 6/23/16.
 */
public class SearchFiltersDialogFragment extends DialogFragment
        implements View.OnClickListener {
    // ...other views declared here...

    private SearchFilters mFilters;


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
        //mFilters = (SearchFilters) getArguments().getParcelable("filters");
        mFilters = getArguments().getParcelable("filters");
        // ... any other view lookups here...
        // Get access to the button
        Button btnSave = (Button) view.findViewById(R.id.btnSave);
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
        // Return filters back to activity through the implemented listener
        OnFilterSearchListener listener = (OnFilterSearchListener) getActivity();
        listener.onUpdateFilters(mFilters);
        // Close the dialog to return back to the parent activity
        dismiss();
    }

}