package com.example.firebasetodoapp;

import java.io.Serializable;

public class toDoItem implements Serializable {

    private String date;
    private String title;

    @Override
    public String toString() {
        return "Message{" +
                "title='" + title + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public toDoItem() {
// Default constructor required for calls to snapshot.toObject(Message.class)
    }

    public toDoItem(String title, String date) {
        this.title = title;
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}