// MoreActivity.java
package com.example.fitgo;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MoreActivity extends AppCompatActivity {

    TextView optionProfile, optionNotifications, optionReset, optionShare, optionPrivacy, optionAbout, optionLogout, optionAchievements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        optionProfile = findViewById(R.id.option_profile);
        optionNotifications = findViewById(R.id.option_notifications);
        optionReset = findViewById(R.id.option_reset);
        optionShare = findViewById(R.id.option_share);
        optionPrivacy = findViewById(R.id.option_privacy);
        optionAbout = findViewById(R.id.option_about);
        optionLogout = findViewById(R.id.option_logout);
        optionAchievements = findViewById(R.id.option_achievements);

        //  Navigation setup
        optionProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        optionNotifications.setOnClickListener(v -> startActivity(new Intent(this, NotificationsActivity.class)));
        optionAchievements.setOnClickListener(v -> startActivity(new Intent(this, AchievementsActivity.class)));

        optionReset.setOnClickListener(v -> {
            new AlertDialog.Builder(this)
                    .setTitle("Reset App")
                    .setMessage("Are you sure you want to clear all data?")
                    .setPositiveButton("Yes", (dialog, which) -> {
                        SharedPreferences prefs = getSharedPreferences("UserData", MODE_PRIVATE);
                        prefs.edit().clear().apply();
                    })
                    .setNegativeButton("No", null)
                    .show();
        });

        optionShare.setOnClickListener(v -> {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("text/plain");
            shareIntent.putExtra(Intent.EXTRA_SUBJECT, "FitGo App");
            shareIntent.putExtra(Intent.EXTRA_TEXT, "Check out the FitGo App: https://play.google.com/store/apps/details?id=com.example.fitgo");
            startActivity(Intent.createChooser(shareIntent, "Share via"));
        });

        optionPrivacy.setOnClickListener(v -> {
            String url = "https://example.com/privacy-policy";
            Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(browserIntent);
        });

        optionAbout.setOnClickListener(v -> startActivity(new Intent(this, AboutAppActivity.class)));

        optionLogout.setOnClickListener(v -> {
            // Handle logout logic here
        });
    }
}
