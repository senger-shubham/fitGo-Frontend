package com.example.fitgo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;
import android.widget.TextView;

public class WeeklyReportActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReportAdapter reportAdapter;
    private List<Report> weeklyReportList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weekly_report);

        TextView tvTitle = findViewById(R.id.weeklyReportTitle);

        // Retrieve data from Intent
        String reportTitle = getIntent().getStringExtra("reportTitle");
        String reportDate = getIntent().getStringExtra("reportDate");

        // Set values in UI
        if (reportTitle != null) {
            tvTitle.setText(reportTitle + "\n" + reportDate); // Display title & date
        }
    }

    private void loadWeeklyReports() {
        // Sample data - Replace with actual data from a database
        weeklyReportList.add(new Report("Day 1: 30 mins Cardio", "2025-03-18"));
        weeklyReportList.add(new Report("Day 2: Strength Training", "2025-03-19"));
        weeklyReportList.add(new Report("Day 3: Yoga & Stretching", "2025-03-20"));
    }
}
