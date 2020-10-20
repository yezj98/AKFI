package com.example.akfi.backend;

public class upload {
    String s, data, date;

    public upload(String title, String data, String date) {
        this.s = title;
        this.data = data;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return s;
    }

    public void setTitle(String title) {
        this.s = title;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
