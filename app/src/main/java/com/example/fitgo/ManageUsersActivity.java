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

public class ManageUsersActivity extends AppCompatActivity {
    private ListView userListView;
    private Button backButton;
    private ArrayList<String> userList; // Dummy user data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_users);

        userListView = findViewById(R.id.user_list_view);
        backButton = findViewById(R.id.back_to_dashboard);

        // Dummy user list (Replace this with actual database data later)
        userList = new ArrayList<>();
        userList.add("User1 - user1@example.com");
        userList.add("User2 - user2@example.com");
        userList.add("User3 - user3@example.com");

        // Set up adapter to display users
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, userList);
        userListView.setAdapter(adapter);

        // Handle list item click (For future user management features)
        userListView.setOnItemClickListener((parent, view, position, id) -> {
            String selectedUser = userList.get(position);
            Toast.makeText(ManageUsersActivity.this, "Clicked: " + selectedUser, Toast.LENGTH_SHORT).show();
            // Here, you can add options like "Edit User" or "Delete User"
        });

        // Back button functionality
        backButton.setOnClickListener(v -> {
            Intent intent = new Intent(ManageUsersActivity.this, AdminDashboardActivity.class);
            startActivity(intent);
            finish();
        });
    }
}
