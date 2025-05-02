package com.example.fitgo;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.view.ViewGroup;
import android.view.LayoutInflater;
import android.view.View;


import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import android.content.Intent;

public class CalorieTrackerActivity extends AppCompatActivity {

    private EditText etCalories;
    private TextView tvTotalCalories;
    private Button btnAddCalories, btnProgressStates, btnDeleteAll;
    private RecyclerView recyclerViewCalories;
    private CalorieAdapter adapter;
    private List<Integer> calorieList;
    private int totalCalories = 0;
    private ProgressBar progressBarCalories;
    private int dailyCalorieGoal = 2000;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_calories_tracker);

        // Initialize UI Elements
        etCalories = findViewById(R.id.etCalories);
        tvTotalCalories = findViewById(R.id.tvTotalCalories);
        btnAddCalories = findViewById(R.id.btnAddCalories);
        btnProgressStates = findViewById(R.id.btnProgressStates);
        btnDeleteAll = findViewById(R.id.btnDeleteAll);
        recyclerViewCalories = findViewById(R.id.recyclerViewCalories);
        progressBarCalories = findViewById(R.id.progressBarCalories);

        recyclerViewCalories.setBackgroundColor(Color.BLACK);

        // Setup RecyclerView
        calorieList = new ArrayList<>();
        adapter = new CalorieAdapter(calorieList);
        recyclerViewCalories.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCalories.setAdapter(adapter);

        // Set max progress for progress bar
        progressBarCalories.setMax(dailyCalorieGoal);
        progressBarCalories.setProgress(0);

        // Button Click Listener for Adding Calories
        btnAddCalories.setOnClickListener(v -> {
            String calorieInput = etCalories.getText().toString().trim();
            if (!calorieInput.isEmpty()) {
                int calories = Integer.parseInt(calorieInput);
                calorieList.add(calories);
                totalCalories += calories;
                tvTotalCalories.setText("Total Calories: " + totalCalories);
                progressBarCalories.setProgress(totalCalories);

                if (totalCalories > dailyCalorieGoal) {
                    new androidx.appcompat.app.AlertDialog.Builder(CalorieTrackerActivity.this)
                            .setTitle("⚠ Warning: Calorie Limit Exceeded!")
                            .setMessage("You have consumed more than " + dailyCalorieGoal + " kcal today. Consider maintaining a balanced diet.")
                            .setPositiveButton("OK", null)
                            .show();
                }
                adapter.notifyDataSetChanged();
                etCalories.setText("");
            } else {
                Toast.makeText(CalorieTrackerActivity.this, "Enter calories!", Toast.LENGTH_SHORT).show();
            }
        });



        // Button Click Listener for Progress States
        btnProgressStates.setOnClickListener(v -> {
            Intent intent = new Intent(CalorieTrackerActivity.this, ProgressStatesActivity.class);
            intent.putExtra("TOTAL_CALORIES", totalCalories);
            startActivity(intent);
        });

        // Button Click Listener for Deleting All Entries
        btnDeleteAll.setOnClickListener(v -> {
            calorieList.clear();
            totalCalories = 0;
            updateUI();
            adapter.notifyDataSetChanged();
        });

        btnDeleteAll.setOnClickListener(v -> {
            calorieList.clear(); // Clear all calories
            totalCalories = 0;   // Reset total
            tvTotalCalories.setText("Total Calories: " + totalCalories);
            progressBarCalories.setProgress(0);
            adapter.notifyDataSetChanged();
            Toast.makeText(this, "All calories deleted", Toast.LENGTH_SHORT).show();
        });

        // Enable Swipe to Delete
        enableSwipeToDelete();
    }

    // Update UI after any changes
    private void updateUI() {
        tvTotalCalories.setText("Total Calories: " + totalCalories);
        progressBarCalories.setProgress(totalCalories);
    }

    // Swipe-to-Delete Functionality
    private void enableSwipeToDelete() {
        ItemTouchHelper.SimpleCallback itemTouchHelper = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false; // Not needed
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                totalCalories -= calorieList.get(position);
                calorieList.remove(position);
                updateUI();
                adapter.notifyItemRemoved(position);
            }
        };

        new ItemTouchHelper(itemTouchHelper).attachToRecyclerView(recyclerViewCalories);
    }

    // Adapter Class for RecyclerView
    public static class CalorieAdapter extends RecyclerView.Adapter<CalorieAdapter.ViewHolder> {
        private List<Integer> calorieList;

        public CalorieAdapter(List<Integer> calorieList) {
            this.calorieList = calorieList;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            holder.calorieText.setText("Calories: " + calorieList.get(position));
        }

        @Override
        public int getItemCount() {
            return calorieList.size();
        }

        public static class ViewHolder extends RecyclerView.ViewHolder {
            TextView calorieText;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                calorieText = itemView.findViewById(android.R.id.text1);
            }
        }
    }


}
