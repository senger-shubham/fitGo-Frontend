package com.example.fitgo;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splashscreen extends AppCompatActivity {
    private Animation up, down;
    private ImageView imageView;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splashscreen);

        // Initialize views
        imageView = findViewById(R.id.appsplash);
        textView = findViewById(R.id.appname);

        // Set animations
        setAnimations();

        // Launch MainActivity after delay
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(Splashscreen.this, MainActivity.class);
            startActivity(intent);
            finish(); // Finish splash screen activity
        }, 3500);

        // Handle system bars insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

// Create a LinearGradient with desired colors
        Shader textShader = new LinearGradient(
                0, 0, textView.getPaint().measureText(textView.getText().toString()), 0,
                new int[]{Color.parseColor("#FF4E8E"), Color.parseColor("#A05BFF"), Color.parseColor("#00CFFF")},
                null,
                Shader.TileMode.CLAMP);

        textView.getPaint().setShader(textShader);



    }

    // Set animations for the splash screen elements
    private void setAnimations() {
        up = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.up);
        down = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.down);

        imageView.setAnimation(up);
        textView.setAnimation(down);
    }
}
