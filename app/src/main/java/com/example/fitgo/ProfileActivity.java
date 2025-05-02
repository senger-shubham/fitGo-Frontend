package com.example.fitgo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfileActivity extends AppCompatActivity {

    ImageView imageProfile;
    EditText editName, editAge, editWeight, editHeight, editGoal;
    Button btnSave;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(android.R.id.content), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        imageProfile = findViewById(R.id.image_profile);
        editName = findViewById(R.id.edit_name);
        editAge = findViewById(R.id.edit_age);
        editWeight = findViewById(R.id.edit_weight);
        editHeight = findViewById(R.id.edit_height);
        editGoal = findViewById(R.id.edit_goal);
        btnSave = findViewById(R.id.btn_save);

        // Save button listener
        btnSave.setOnClickListener(v -> {
            String name = editName.getText().toString();
            String age = editAge.getText().toString();
            String weight = editWeight.getText().toString();
            String height = editHeight.getText().toString();
            String goal = editGoal.getText().toString();

            // You can validate or save the values here
            Toast.makeText(ProfileActivity.this, "Profile Saved", Toast.LENGTH_SHORT).show();
        });

        // Optional: Profile image click action
        imageProfile.setOnClickListener(v ->
                Toast.makeText(ProfileActivity.this, "Add image upload functionality", Toast.LENGTH_SHORT).show()
        );
    }
}
