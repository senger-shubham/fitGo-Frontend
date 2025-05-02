package com.example.fitgo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ProfileSettingsActivity extends AppCompatActivity {

    private EditText nameEditText, weightEditText, heightEditText, goalEditText;
    private ImageView profileImageView;
    private Button saveButton;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_settings);

        // Initialize Views
        nameEditText = findViewById(R.id.name_edit_text);
        weightEditText = findViewById(R.id.weight_edit_text);
        heightEditText = findViewById(R.id.height_edit_text);
        goalEditText = findViewById(R.id.goal_edit_text);
        profileImageView = findViewById(R.id.profile_image);
        saveButton = findViewById(R.id.save_button);

        // Handle the save action here
        saveButton.setOnClickListener(v -> {
            // Save the data
            String name = nameEditText.getText().toString();
            String weight = weightEditText.getText().toString();
            String height = heightEditText.getText().toString();
            String goal = goalEditText.getText().toString();

            // Implement saving the data, for example, saving it to SharedPreferences or a database.
        });
    }
}
