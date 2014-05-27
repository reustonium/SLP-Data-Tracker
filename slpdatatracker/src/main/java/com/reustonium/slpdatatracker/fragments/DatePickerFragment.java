package com.reustonium.slpdatatracker.fragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;

import com.reustonium.slpdatatracker.R;

/**
 * Created by Andrew on 5/27/2014.
 */
public class DatePickerFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View v =  getActivity().getLayoutInflater().inflate(R.layout.dialog_date, null);

        return new AlertDialog.Builder(getActivity())
                .setTitle("Date of Session")
                .setPositiveButton(android.R.string.ok, null)
                .setView(v)
                .create();
    }
}
