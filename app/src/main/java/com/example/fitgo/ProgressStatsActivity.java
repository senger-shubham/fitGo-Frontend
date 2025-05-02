package com.example.fitgo;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;


import java.util.ArrayList;
import java.util.List;

public class ProgressStatsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    BarChart barChart;
    PieChart pieChart;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_progress_stats);

        // Set window insets
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        recyclerView = findViewById(R.id.recyclerViewCalorieHistory);
        barChart = findViewById(R.id.barChartCalories);
        pieChart = findViewById(R.id.pieChartMealBreakdown);


        // ➤ Dummy data for daily logs
        List<String> dailyLogs = new ArrayList<>();
        dailyLogs.add("Mon: 1800 kcal");
        dailyLogs.add("Tue: 2000 kcal");
        dailyLogs.add("Wed: 1500 kcal");
        dailyLogs.add("Thu: 2200 kcal");
        dailyLogs.add("Fri: 1900 kcal");

// ➤ RecyclerView adapter (inline)
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(new RecyclerView.Adapter<RecyclerView.ViewHolder>() {
            @NonNull
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                // Inflate a simple item layout
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(android.R.layout.simple_list_item_1, parent, false);
                return new RecyclerView.ViewHolder(view) {
                    // Find the TextView inside the item layout
                    TextView textView = view.findViewById(android.R.id.text1);
                };
            }

            @Override
            public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
                // Set the text from dailyLogs data to the TextView
                ((TextView) holder.itemView.findViewById(android.R.id.text1)).setText(dailyLogs.get(position));
            }

            @Override
            public int getItemCount() {
                // Return the size of the dailyLogs list
                return dailyLogs.size();
            }
        });


        // ➤ Bar Chart
        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(0, 1800));
        barEntries.add(new BarEntry(1, 2000));
        barEntries.add(new BarEntry(2, 1500));
        barEntries.add(new BarEntry(3, 2200));
        barEntries.add(new BarEntry(4, 1900));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Daily Calories");
        BarData barData = new BarData(barDataSet);
        barChart.setData(barData);
        Description barDesc = new Description();

        barDesc.setPosition(500, 30); // Set the position of the description, if needed
        barDesc.setTextSize(14f); // Set the text size of the description
        // Optional: Set a top margin for the chart to create space between description and x-axis
        barChart.setExtraTopOffset(30); // Increase this value if necessary

        barDesc.setText("Calorie Intake This Week");
        barChart.setDescription(barDesc);
        barChart.invalidate();



        // ➤ Pie Chart
        ArrayList<PieEntry> pieEntries = new ArrayList<>();
        pieEntries.add(new PieEntry(40f, "Breakfast"));
        pieEntries.add(new PieEntry(30f, "Lunch"));
        pieEntries.add(new PieEntry(20f, "Dinner"));
        pieEntries.add(new PieEntry(10f, "Snacks"));

        PieDataSet pieDataSet = new PieDataSet(pieEntries, "Meals");

        // ✅ Fix the label on click/tap
        pieDataSet.setValueFormatter(new ValueFormatter() {
            @Override
            public String getPieLabel(float value, PieEntry pieEntry) {
                return pieEntry.getLabel() + ": " + (int)value + "%";
            }
        });
        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        Description pieDesc = new Description();
        pieDesc.setText("Meal-wise Calorie Distribution");
        pieDesc.setTextSize(12f); // optional
        pieDesc.setPosition(500f, 30); // adjust X, Y to place it elsewhere
        pieChart.setExtraTopOffset(5); // Increase this value if necessary
        pieChart.setDescription(pieDesc);
        // ✅ Add the marker view here
        MarkerView markerView = new MarkerView(this, R.layout.marker_view) {

            private MPPointF mOffset;
            TextView tvContent;

            @Override
            public void refreshContent(Entry e, Highlight highlight) {
                if (tvContent == null)
                    tvContent = findViewById(R.id.tvContent);
                tvContent.setText(((int) e.getY()) + "%");
                super.hashCode();
            }

            @Override
            public MPPointF getOffset() {
                tvContent.post(() -> {
                    int width = getWidth();
                    int height = getHeight();
                    setOffset(new MPPointF(-width / 2f, -height));
                });
                return new MPPointF(0, 0);
            }

        };

        pieChart.setMarker(markerView);
        pieChart.invalidate();
    }
}
