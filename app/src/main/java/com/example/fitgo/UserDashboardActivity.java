package com.example.fitgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;


public class UserDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_dashboard);

        Button workoutPlansBtn = findViewById(R.id.workout_plans_btn);
        Button calorieTrackerBtn = findViewById(R.id.calorie_tracker_btn);
        Button progressBtn = findViewById(R.id.progress_btn);
        Button logoutButton = findViewById(R.id.logout_button);

        LinearLayout home = findViewById(R.id.nav_home);
        LinearLayout workout = findViewById(R.id.nav_workout);
        LinearLayout calories = findViewById(R.id.nav_calories);
        LinearLayout progress = findViewById(R.id.nav_progress);
        LinearLayout more = findViewById(R.id.nav_more);

        // Get all icon and text views
        ImageView iconHome = findViewById(R.id.icon_home);
        ImageView iconWorkout = findViewById(R.id.icon_workout);
        ImageView iconCalories = findViewById(R.id.icon_calories);
        ImageView iconProgress = findViewById(R.id.icon_progress);
        ImageView iconMore = findViewById(R.id.icon_more);

        TextView textHome = findViewById(R.id.text_home);
        TextView textWorkout = findViewById(R.id.text_workout);
        TextView textCalories = findViewById(R.id.text_calories);
        TextView textProgress = findViewById(R.id.text_progress);
        TextView textMore = findViewById(R.id.text_more);

        // Highlight the active tab (e.g., Home)
        iconHome.setColorFilter(ContextCompat.getColor(this, R.color.active_blue));
        textHome.setTextColor(ContextCompat.getColor(this, R.color.active_blue));

        // Reset others (optional)
        iconWorkout.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        textWorkout.setTextColor(ContextCompat.getColor(this, R.color.gray));

        iconCalories.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        textCalories.setTextColor(ContextCompat.getColor(this, R.color.gray));

        iconProgress.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        textProgress.setTextColor(ContextCompat.getColor(this, R.color.gray));

        iconMore.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        textMore.setTextColor(ContextCompat.getColor(this, R.color.gray));

        home.setOnClickListener(v -> {
            // Already on home, so you might just refresh or do nothing
            // Highlight the active tab (e.g., Home)
            iconHome.setColorFilter(ContextCompat.getColor(this, R.color.active_blue));
            textHome.setTextColor(ContextCompat.getColor(this, R.color.active_blue));

            // Reset others (optional)
            iconWorkout.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textWorkout.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconCalories.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textCalories.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconProgress.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textProgress.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconMore.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textMore.setTextColor(ContextCompat.getColor(this, R.color.gray));
        });

        workoutPlansBtn.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, WorkoutPlansActivity.class);
            startActivity(intent);
        });


        workout.setOnClickListener(v -> {
            // Highlight Workout tab
            iconWorkout.setColorFilter(ContextCompat.getColor(this, R.color.active_blue));
            textWorkout.setTextColor(ContextCompat.getColor(this, R.color.active_blue));

// Reset others
            iconHome.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textHome.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconCalories.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textCalories.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconProgress.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textProgress.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconMore.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textMore.setTextColor(ContextCompat.getColor(this, R.color.gray));

            startActivity(new Intent(UserDashboardActivity.this, WorkoutPlansActivity.class));
        });


        calorieTrackerBtn.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, CalorieTrackerActivity.class);
            startActivity(intent);
        });

        calories.setOnClickListener(v -> {
            // Highlight Calories tab
            iconCalories.setColorFilter(ContextCompat.getColor(this, R.color.active_blue));
            textCalories.setTextColor(ContextCompat.getColor(this, R.color.active_blue));

// Reset others
            iconHome.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textHome.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconWorkout.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textWorkout.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconProgress.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textProgress.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconMore.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textMore.setTextColor(ContextCompat.getColor(this, R.color.gray));
            startActivity(new Intent(UserDashboardActivity.this, CalorieTrackerActivity.class));
        });

        progressBtn.setOnClickListener(v -> {
            Intent intent = new Intent(UserDashboardActivity.this, ProgressStatsActivity.class);
            startActivity(intent);
        });

        progress.setOnClickListener(v -> {
            // Highlight Profile tab
            iconProgress.setColorFilter(ContextCompat.getColor(this, R.color.active_blue));
            textProgress.setTextColor(ContextCompat.getColor(this, R.color.active_blue));

// Reset others
            iconHome.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textHome.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconWorkout.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textWorkout.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconCalories.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textCalories.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconMore.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textMore.setTextColor(ContextCompat.getColor(this, R.color.gray));

            startActivity(new Intent(UserDashboardActivity.this, ProgressStatsActivity.class));
        });

        more.setOnClickListener(v -> {
            // Highlight More tab
            iconMore.setColorFilter(ContextCompat.getColor(this, R.color.active_blue));
            textMore.setTextColor(ContextCompat.getColor(this, R.color.active_blue));

// Reset others
            iconHome.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textHome.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconWorkout.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textWorkout.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconCalories.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textCalories.setTextColor(ContextCompat.getColor(this, R.color.gray));

            iconProgress.setColorFilter(ContextCompat.getColor(this, R.color.gray));
            textProgress.setTextColor(ContextCompat.getColor(this, R.color.gray));

            startActivity(new Intent(UserDashboardActivity.this, MoreActivity.class));
        });




        logoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("UserPrefs", MODE_PRIVATE).edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(UserDashboardActivity.this, Activity_login.class);
            startActivity(intent);
            finish();
        });





// Reset others (optional)
        iconWorkout.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        textWorkout.setTextColor(ContextCompat.getColor(this, R.color.gray));

        iconCalories.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        textCalories.setTextColor(ContextCompat.getColor(this, R.color.gray));

        iconProgress.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        textProgress.setTextColor(ContextCompat.getColor(this, R.color.gray));

        iconMore.setColorFilter(ContextCompat.getColor(this, R.color.gray));
        textMore.setTextColor(ContextCompat.getColor(this, R.color.gray));


    }
}
