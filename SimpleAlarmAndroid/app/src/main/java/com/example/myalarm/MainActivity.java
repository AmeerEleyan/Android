package com.example.myalarm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.myalarm.databinding.MainAlarmPageBinding;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity {

    // protected TextInputLayout txtDifferenceBetweenAlarm, txtNumberOfAlarms;
    MainAlarmPageBinding binding;
    private int hour, minutes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainAlarmPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }

    public void handle_select_time(View view) {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                hour = selectedHour;
                minutes = selectedMinute;
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, AlertDialog.THEME_DEVICE_DEFAULT_DARK, onTimeSetListener, hour, minutes, false);
        timePickerDialog.setTitle("Select Time");

        timePickerDialog.show();


    }

    public void handle_set_alarm(View view) {

        if (!binding.txtDifferenceBetweenAlarm.getText().toString().isEmpty()
                && !binding.txtNumberOfAlarms.getText().toString().isEmpty()) {
            Intent intent = new Intent(AlarmClock.ACTION_SET_ALARM);
            int difference = Integer.parseInt(this.binding.txtDifferenceBetweenAlarm.getText().toString().trim());
            int numberOfAlarms = Integer.parseInt(this.binding.txtNumberOfAlarms.getText().toString().trim());


            for (int i = 0; i < numberOfAlarms; ++i) {
                intent.putExtra(AlarmClock.EXTRA_HOUR, hour);
                intent.putExtra(AlarmClock.EXTRA_MINUTES, minutes);
                minutes += difference;
                startActivity(intent);
            }
            this.binding.txtDifferenceBetweenAlarm.getText().clear();
            this.binding.txtNumberOfAlarms.getText().clear();
            this.binding.txtNumberOfAlarms.onEditorAction(EditorInfo.IME_ACTION_DONE);
            Toast.makeText(this, "Alarms was set successfully", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(this, "Please enter values", Toast.LENGTH_SHORT).show();
        }


    }
}