package com.example.fitgo;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import android.text.TextUtils;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.example.fitgo.api.ApiClient;
import com.example.fitgo.api.ApiService;
import com.example.fitgo.model.LoginResponse;
import com.example.fitgo.model.User;

import retrofit2.Call;
import retrofit2.Response;

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
            String email = usernameInput.getText().toString().trim();
            String password = passwordInput.getText().toString().trim();

            // Check if email or password is empty
            if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                showToast("Please fill all fields");
                return;
            }

            // Check for valid email format
            if (!isValidEmail(email)) {
                showToast("Invalid email format");
                return;
            }

            // Show loading indicator
            showLoading(true);

            // Create user object
            User user = new User(email, password);

            // Make API call
            ApiService apiService = ApiClient.getRetrofit().create(ApiService.class);
            Call<LoginResponse> call = apiService.login(user);  // Now using LoginResponse instead of String

            call.enqueue(new Callback<LoginResponse>() {
                @Override
                public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                    showLoading(false);  // Hide loading indicator

                    if (response.isSuccessful() && response.body() != null) {
                        LoginResponse loginResponse = response.body(); // Get LoginResponse object
                        String message = loginResponse.getMessage(); // Assuming your LoginResponse has a 'message' field
                        showToast(message);

                        if (message.equalsIgnoreCase("Login successful!")) {
                            SharedPreferences prefs = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                            SharedPreferences.Editor editor = prefs.edit();
                            editor.putBoolean("isLoggedIn", true);
                            editor.putString("username", email);
                            editor.apply();

                            navigateToDashboard(); // Call this method
                        }

                    } else {
                        showToast("Login failed! Please check your credentials.");
                    }
                }

                @Override
                public void onFailure(Call<LoginResponse> call, Throwable t) {
                    showLoading(false);  // Hide loading indicator
                    Log.e("LoginError", "Login failed: " + t.getMessage(), t);
                    showToast("Login error: " + t.getMessage());
                }
            });
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

    private void showToast(String message) {
        Toast.makeText(Activity_login.this, message, Toast.LENGTH_SHORT).show();
    }

    private boolean isValidEmail(String email) {
        // A simple regex for validating the email format
        String emailPattern = "[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}";
        return email.matches(emailPattern);
    }
    private void navigateToDashboard() {
        Intent intent = new Intent(Activity_login.this, UserDashboardActivity.class);
        startActivity(intent);
        finish(); // Optional: finish the login activity so user can't go back
    }


    // Method to show or hide the ProgressBar
    private void showLoading(boolean isLoading) {
        ProgressBar progressBar = findViewById(R.id.progress_bar); // Get ProgressBar by ID
        if (isLoading) {
            ((View) progressBar).setVisibility(View.VISIBLE); // Show ProgressBar
        } else {
            progressBar.setVisibility(View.GONE); // Hide ProgressBar
        }
    }


}
