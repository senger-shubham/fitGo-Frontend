package com.example.fitgo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ManageWorkoutsActivity extends AppCompatActivity {
    private ListView workoutListView;
    private Button addWorkoutButton, backButton;
    private ArrayList<String> workoutList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_workouts);

        workoutListView = findViewById(R.id.workout_list_view);
        addWorkoutButton = findViewById(R.id.add_workout_button);
        backButton = findViewById(R.id.back_to_dashboard);

        // Dummy workout list (Replace this with actual database data later)
        workoutList = new ArrayList<>();
        workoutList.add("Workout 1 - Cardio");
        workoutList.add("Workout 2 - Strength Training");
        workoutList.add("Workout 3 - Yoga");

        // Set up adapter to display workouts
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, workoutList);
        workoutListView.setAdapter(adapter);

        // Handle list item click (For future edit/delete features)
        workoutListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedWorkout = workoutList.get(position);
            Toast.makeText(ManageWorkoutsActivity.this, "Clicked: " + selectedWorkout, Toast.LENGTH_SHORT).show();
        });

        // Handle add workout button click
        addWorkoutButton.setOnClickListener(v -> {
            Toast.makeText(ManageWorkoutsActivity.this, "Feature: Add Workout", Toast.LENGTH_SHORT).show();
            // Here, you can navigate to another activity for adding a workout
            Intent intent = new Intent(ManageWorkoutsActivity.this, AddWorkoutActivity.class);
            startActivity(intent);
            finish();
        });

        // Handle back button click
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ManageWorkoutsActivity.this, AdminDashboardActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
