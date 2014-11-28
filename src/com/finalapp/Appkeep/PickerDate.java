package com.finalapp.Appkeep;

import java.util.Calendar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

public class PickerDate extends Activity {
    static TextView mDateDisplay;
    private Button mPickDate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //sets the graphical view for this activity
        setContentView(R.layout.date_picker_dialog123);
        mDateDisplay = (TextView) findViewById(R.id.textView1);
        mPickDate = (Button) findViewById(R.id.bn_set_date);
        
        //set up onClickListener to 
        mPickDate.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
               // showDialog(DATE_DIALOG_ID);
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getFragmentManager(), "datePicker");
            }
        });
    }   
    public static class DatePickerFragment extends DialogFragment
    implements DatePickerDialog.OnDateSetListener {

        public EditText editText;
        DatePicker dpResult;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
    // Use the current date as the default date in the picker
    final Calendar c = Calendar.getInstance();
    int year = c.get(Calendar.YEAR);
    int month = c.get(Calendar.MONTH);
    int day = c.get(Calendar.DAY_OF_MONTH);
    return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public void onDateSet(DatePicker view, int year, int month, int day) {

        mDateDisplay .setText(String.valueOf(day) + "/"
                + String.valueOf(month + 1) + "/" + String.valueOf(year));
    }
    }
}

