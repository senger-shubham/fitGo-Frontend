package com.example.fitgo;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class NotificationsActivity extends AppCompatActivity {

    private TextView workoutTimeText, calorieTimeText;
    private Button btnSetWorkoutTime, btnSetCalorieTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        setTitle("Notifications");

        workoutTimeText = findViewById(R.id.text_workout_time);
        calorieTimeText = findViewById(R.id.text_calorie_time);
        btnSetWorkoutTime = findViewById(R.id.btn_set_workout_time);
        btnSetCalorieTime = findViewById(R.id.btn_set_calorie_time);

        btnSetWorkoutTime.setOnClickListener(v -> showTimePickerDialog(workoutTimeText));
        btnSetCalorieTime.setOnClickListener(v -> showTimePickerDialog(calorieTimeText));
    }

    private void showTimePickerDialog(TextView targetTextView) {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (TimePicker view, int selectedHour, int selectedMinute) -> {
                    String time = String.format("%02d:%02d", selectedHour, selectedMinute);
                    targetTextView.setText(time);
                }, hour, minute, true);

        timePickerDialog.show();
    }
}
