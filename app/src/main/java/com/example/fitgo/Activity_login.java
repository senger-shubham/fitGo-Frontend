package com.example.fitgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

public class Activity_login extends AppCompatActivity {
    private EditText usernameInput, passwordInput;
    private Button loginButton;
    private boolean isPasswordVisible = false; // To track eye toggle


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        usernameInput = findViewById(R.id.username);
        passwordInput = findViewById(R.id.password);
        loginButton = findViewById(R.id.login_button);
        TextView signupText = findViewById(R.id.signup_text);
        TextView forgotPasswordText = findViewById(R.id.forgot_password);

        // 👁️ Add eye toggle logic here
        Drawable eyeOpen = ContextCompat.getDrawable(this, R.drawable.ic_eye_open);
        Drawable eyeClosed = ContextCompat.getDrawable(this, R.drawable.ic_eye_closed);

        passwordInput.setCompoundDrawablesWithIntrinsicBounds(null, null, eyeClosed, null); // default eye closed

        passwordInput.setOnTouchListener((v, event) -> {
            final int DRAWABLE_END = 2;
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (event.getRawX() >= (passwordInput.getRight() - passwordInput.getCompoundDrawables()[DRAWABLE_END].getBounds().width())) {
                    if (isPasswordVisible) {
                        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                        passwordInput.setCompoundDrawablesWithIntrinsicBounds(null, null, eyeClosed, null);
                        isPasswordVisible = false;
                    } else {
                        passwordInput.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                        passwordInput.setCompoundDrawablesWithIntrinsicBounds(null, null, eyeOpen, null);
                        isPasswordVisible = true;
                    }
                    passwordInput.setSelection(passwordInput.getText().length());
                    return true;
                }
            }
            return false;
        });

        //  Login Logic
        loginButton.setOnClickListener(view -> {
            String username = usernameInput.getText().toString();
            String password = passwordInput.getText().toString();

            if (username.equals("admin") && password.equals("1234")) {
                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putString("username", username);
                editor.apply();

                Intent intent = new Intent(Activity_login.this, MainActivity.class);
                startActivity(intent);
                finish();
            } else if (username.equals("user") && password.equals("0000")) {
                SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putBoolean("isLoggedIn", true);
                editor.putString("username", username);
                editor.putString("role", "user");
                editor.apply();

                Intent intent = new Intent(Activity_login.this, UserDashboardActivity.class);
                startActivity(intent);
                finish();
            } else {
                Toast.makeText(Activity_login.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
            }


        });

        //click for signup
        signupText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Activity_login.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
        //clicklistner for forget pass
        forgotPasswordText.setOnClickListener(v -> {
            Intent intent = new Intent(Activity_login.this, ForgotPasswordActivity.class);
            startActivity(intent);
        });
    }
}
