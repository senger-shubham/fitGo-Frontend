package com.example.fitgo;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ReportAdapter extends RecyclerView.Adapter<ReportAdapter.ReportViewHolder> {
    private List<Report> reportList;
    private Context context;

    public ReportAdapter(List<Report> reportList, Context context) {
        this.reportList = reportList;
        this.context = context;
    }

    @NonNull
    @Override
    public ReportViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_report, parent, false);
        return new ReportViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReportViewHolder holder, int position) {
        Report report = reportList.get(position);
        holder.bind(report);
    }

    @Override
    public int getItemCount() {
        return reportList.size();
    }

    public class ReportViewHolder extends RecyclerView.ViewHolder {
        TextView reportTitle, reportDate;

        public ReportViewHolder(@NonNull View itemView) {
            super(itemView);
            reportTitle = itemView.findViewById(R.id.reportTitle);
            reportDate = itemView.findViewById(R.id.reportDate);
        }

        public void bind(Report report) {
            reportTitle.setText(report.getTitle());
            reportDate.setText(report.getDate());

            itemView.setOnClickListener(v -> {
                Intent intent;
                switch (report.getTitle()) {
                    case "Weekly Workout Report":
                        intent = new Intent(context, WeeklyReportActivity.class);
                        intent.putExtra("reportTitle", report.getTitle()); // Pass report title
                        intent.putExtra("reportDate", report.getDate());   // Pass report date
                        break;
                    // Add cases for other report types and their corresponding activities
                    default:
                        return;
                }
                context.startActivity(intent);
            });
        }
    }
}
