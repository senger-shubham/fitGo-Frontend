package com.example.fitgo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddWorkoutActivity extends AppCompatActivity {

    private EditText workoutNameInput, workoutDurationInput;
    private Button saveWorkoutButton, cancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_workout);

        // Initialize UI components
        workoutNameInput = findViewById(R.id.workout_name_input);
        workoutDurationInput = findViewById(R.id.workout_duration_input);
        saveWorkoutButton = findViewById(R.id.save_workout_button);
        cancelButton = findViewById(R.id.cancel_button);

        // Save Workout button functionality
        saveWorkoutButton.setOnClickListener(view -> {
            String workoutName = workoutNameInput.getText().toString().trim();
            String workoutDuration = workoutDurationInput.getText().toString().trim();

            if (workoutName.isEmpty() || workoutDuration.isEmpty()) {
                Toast.makeText(AddWorkoutActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            } else {
                // Here, you can save the workout data to a database
                Toast.makeText(AddWorkoutActivity.this, "Workout Added!", Toast.LENGTH_SHORT).show();
                finish(); // Close activity after saving
            }
        });

        // Cancel button functionality
        cancelButton.setOnClickListener(view -> finish());
    }
}
