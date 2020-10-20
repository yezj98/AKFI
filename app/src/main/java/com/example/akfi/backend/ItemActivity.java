package com.example.akfi.backend;

public class ItemActivity {

    String title, data, date;

    public ItemActivity(String title, String data, String date) {

        this.title = title;
        this.data = data;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public String gettitle() {
        return title;
    }

    public String getdata() {
        return data;
    }
}
