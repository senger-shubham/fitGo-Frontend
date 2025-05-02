package com.example.fitgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class AdminDashboardActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_dashboard);

        Button manageUsersBtn = findViewById(R.id.manage_users_btn);
        Button manageWorkoutsBtn = findViewById(R.id.manage_workouts_btn);
        Button viewReportsBtn = findViewById(R.id.view_reports_btn);
        Button logoutButton = findViewById(R.id.logout_button);

        manageUsersBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, ManageUsersActivity.class);
            startActivity(intent);
        });

        manageWorkoutsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, ManageWorkoutsActivity.class);
            startActivity(intent);
        });

        viewReportsBtn.setOnClickListener(v -> {
            Intent intent = new Intent(AdminDashboardActivity.this, ViewReportsActivity.class);
            startActivity(intent);
        });

        logoutButton.setOnClickListener(v -> {
            SharedPreferences.Editor editor = getSharedPreferences("UserPrefs", MODE_PRIVATE).edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(AdminDashboardActivity.this, Activity_login.class);
            startActivity(intent);
            finish();
        });
    }
}
