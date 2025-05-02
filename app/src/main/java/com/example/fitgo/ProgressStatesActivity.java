package com.example.fitgo;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ProgressStatesActivity extends AppCompatActivity {

    private ProgressBar progressBarCircular;
    private TextView tvCaloriesPercentage;
    private int dailyCalorieGoal = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_states);

        // Initialize UI elements
        progressBarCircular = findViewById(R.id.progressBarCircular);
        tvCaloriesPercentage = findViewById(R.id.tvCaloriesPercentage);

        // Get total calories from Intent (sent from CalorieTrackerActivity)
        int totalCalories = getIntent().getIntExtra("TOTAL_CALORIES", 0);

        // Update UI
        updateProgress(totalCalories);
    }

    public void updateProgress(int totalCalories) {
        int progress = (int) ((totalCalories / (float) dailyCalorieGoal) * 100);
        progressBarCircular.setProgress(totalCalories); // Update progress bar
        tvCaloriesPercentage.setText(progress + "%"); // Show percentage
    }
}
