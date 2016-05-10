package com.wordzoo.uhr.utils;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.text.format.DateFormat;
import android.widget.TextView;
import android.widget.TimePicker;

import com.germanclock.time.Pieces;
import com.wordzoo.uhr.ActivityCustomSettings;
import com.wordzoo.uhr.R;

import java.util.Calendar;

public class TimePickerFragment extends DialogFragment
        implements TimePickerDialog.OnTimeSetListener {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        TextView tv = (TextView)getActivity().findViewById(R.id.preview);
        Pieces p = new Pieces(tv.getText()+"");
        return new TimePickerDialog(getActivity(), this, p.getHr24(), p.getMinutes(),
                DateFormat.is24HourFormat(getActivity()));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        String time = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute);
        TextView tv = (TextView)getActivity().findViewById(R.id.preview);
        tv.setText(time);
        ((ActivityCustomSettings)getActivity()).time = time;
        ((ActivityCustomSettings)getActivity()).drawTime();
    }
}
