package com.example.fitgo;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.android.volley.*;
import com.android.volley.toolbox.*;

import org.json.JSONObject;

public class SignUpActivity extends AppCompatActivity {

    EditText fullNameET, emailET, usernameET, passwordET, confirmPasswordET;
    Button signUpBtn;

    String signupUrl = "http://192.168.29.95:8080/api/auth/register";  // replace with your backend URL

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        fullNameET = findViewById(R.id.fullname);
        emailET = findViewById(R.id.email);
        usernameET = findViewById(R.id.username);
        passwordET = findViewById(R.id.password);
        confirmPasswordET = findViewById(R.id.confirm_password);
        signUpBtn = findViewById(R.id.signup_button);

        signUpBtn.setOnClickListener(v -> registerUser());
    }

    private void registerUser() {
        String fullName = fullNameET.getText().toString().trim();
        String email = emailET.getText().toString().trim();
        String username = usernameET.getText().toString().trim();
        String password = passwordET.getText().toString().trim();
        String confirmPassword = confirmPasswordET.getText().toString().trim();

        if (!password.equals(confirmPassword)) {
            Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            JSONObject jsonBody = new JSONObject();
            jsonBody.put("fullName", fullName);
            jsonBody.put("email", email);
            jsonBody.put("username", username);
            jsonBody.put("password", password);

            // Use StringRequest here instead of JsonObjectRequest
            StringRequest request = new StringRequest(
                    Request.Method.POST, signupUrl,
                    response -> {
                        try {
                            Log.d("SignUp", "Response: " + response);
                            // If response is a string, simply show it or use it as needed
                            Toast.makeText(this, "Response: " + response, Toast.LENGTH_SHORT).show();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    },
                    error -> {
                        // Log full error message to understand the issue better
                        if (error.networkResponse != null) {
                            int statusCode = error.networkResponse.statusCode;
                            String responseBody = new String(error.networkResponse.data);
                            Log.e("VolleyError", "Status Code: " + statusCode);
                            Log.e("VolleyError", "Response: " + responseBody);
                        } else {
                            Log.e("VolleyError", error.toString());
                        }

                        // Display error message
                        Toast.makeText(this, "Signup failed. Please try again.", Toast.LENGTH_SHORT).show();
                    }
            ) {
                @Override
                public byte[] getBody() {
                    return jsonBody.toString().getBytes();
                }

                @Override
                public String getBodyContentType() {
                    return "application/json";
                }
            };

            // Set timeout and retry policy as before
            int socketTimeout = 30000;  // 30 seconds timeout
            RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                    DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                    DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
            request.setRetryPolicy(policy);
            Volley.newRequestQueue(this).add(request);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}