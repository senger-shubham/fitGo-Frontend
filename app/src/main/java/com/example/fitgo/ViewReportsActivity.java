package com.example.fitgo;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class ViewReportsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ReportAdapter reportAdapter;
    private List<Report> reportList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reports);

        recyclerView = findViewById(R.id.reportsRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        reportList = new ArrayList<>();
        loadReports(); // Load sample reports

        reportAdapter = new ReportAdapter(reportList, this);
        recyclerView.setAdapter(reportAdapter);
    }

    private void loadReports() {
        // Sample reports data (Replace with actual data from DB later)
        reportList.add(new Report("Weekly Workout Report", "2025-03-25"));
        reportList.add(new Report("Monthly Progress Summary", "2025-03-10"));
        reportList.add(new Report("Calorie Intake Report", "2025-03-01"));
    }
}
