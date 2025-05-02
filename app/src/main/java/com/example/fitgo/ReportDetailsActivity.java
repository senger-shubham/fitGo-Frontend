package com.example.fitgo;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ReportDetailsActivity extends AppCompatActivity {
    private TextView reportTitle, reportDate, reportContent;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);

        reportTitle = findViewById(R.id.reportTitle);
        reportDate = findViewById(R.id.reportDate);
        reportContent = findViewById(R.id.reportContent);

        // Get data from intent
        Intent intent = getIntent();
        if (intent != null) {
            reportTitle.setText(intent.getStringExtra("title"));
            reportDate.setText("Date: " + intent.getStringExtra("date"));
            reportContent.setText(intent.getStringExtra("content"));
        }
    }
}
