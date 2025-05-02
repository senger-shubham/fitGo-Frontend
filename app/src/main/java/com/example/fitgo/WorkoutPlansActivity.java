package com.example.fitgo;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class WorkoutPlansActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private WorkoutPlanAdapter adapter;
    private List<WorkoutPlan> workoutPlans;
    private Button btnAddWorkout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_plans);

        recyclerView = findViewById(R.id.recyclerViewWorkouts);
        btnAddWorkout = findViewById(R.id.btnAddWorkout);

        workoutPlans = new ArrayList<>();
        workoutPlans.add(new WorkoutPlan("Beginner Plan", "Basic exercises for starters"));
        workoutPlans.add(new WorkoutPlan("Intermediate Plan", "Increase intensity and endurance"));
        workoutPlans.add(new WorkoutPlan("Advanced Plan", "High-intensity workouts"));

        adapter = new WorkoutPlanAdapter(workoutPlans);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        btnAddWorkout.setOnClickListener(v -> showAddWorkoutDialog());

        // Swipe to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int position = viewHolder.getAdapterPosition();
                workoutPlans.remove(position);
                adapter.notifyItemRemoved(position);
                Toast.makeText(WorkoutPlansActivity.this, "Workout Plan Deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
    }

    private void showAddWorkoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Add New Workout Plan");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_workout, null);
        EditText etWorkoutName = view.findViewById(R.id.etWorkoutName);
        EditText etWorkoutDescription = view.findViewById(R.id.etWorkoutDescription);
        builder.setView(view);

        builder.setPositiveButton("Add", (dialog, which) -> {
            String name = etWorkoutName.getText().toString().trim();
            String description = etWorkoutDescription.getText().toString().trim();

            if (!name.isEmpty() && !description.isEmpty()) {
                workoutPlans.add(new WorkoutPlan(name, description));
                adapter.notifyItemInserted(workoutPlans.size() - 1);
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    private void showEditWorkoutDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Edit Workout Plan");

        View view = LayoutInflater.from(this).inflate(R.layout.dialog_add_workout, null);
        EditText etWorkoutName = view.findViewById(R.id.etWorkoutName);
        EditText etWorkoutDescription = view.findViewById(R.id.etWorkoutDescription);

        // Pre-fill existing data
        WorkoutPlan workoutPlan = workoutPlans.get(position);
        etWorkoutName.setText(workoutPlan.getName());
        etWorkoutDescription.setText(workoutPlan.getDescription());

        builder.setView(view);

        builder.setPositiveButton("Update", (dialog, which) -> {
            String newName = etWorkoutName.getText().toString().trim();
            String newDescription = etWorkoutDescription.getText().toString().trim();

            if (!newName.isEmpty() && !newDescription.isEmpty()) {
                workoutPlans.get(position).setName(newName);
                workoutPlans.get(position).setDescription(newDescription);
                adapter.notifyItemChanged(position);
            }
        });

        builder.setNegativeButton("Cancel", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    public static class WorkoutPlan {
        private String name, description;

        public WorkoutPlan(String name, String description) {
            this.name = name;
            this.description = description;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getDescription() { return description; }
        public void setDescription(String description) { this.description = description; }
    }

    public class WorkoutPlanAdapter extends RecyclerView.Adapter<WorkoutPlanAdapter.ViewHolder> {

        private List<WorkoutPlan> workoutPlans;

        public WorkoutPlanAdapter(List<WorkoutPlan> workoutPlans) {
            this.workoutPlans = workoutPlans;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_2, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            WorkoutPlan plan = workoutPlans.get(position);
            holder.name.setText(plan.getName());
            holder.description.setText(plan.getDescription());

            // Long press to edit
            holder.itemView.setOnLongClickListener(v -> {
                showEditWorkoutDialog(position);
                return true;
            });
        }

        @Override
        public int getItemCount() {
            return workoutPlans.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView name, description;

            public ViewHolder(@NonNull View itemView) {
                super(itemView);
                name = itemView.findViewById(android.R.id.text1);
                description = itemView.findViewById(android.R.id.text2);
            }
        }
    }
}
