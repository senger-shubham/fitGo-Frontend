package com.example.fitgo;

public class Report {
    private String title;
    private String date;

    public Report(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }
}
