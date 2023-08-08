package com.example.mpr.tutorials.tut7.models;

public class Note {
    private long id;
    private String content;

    public Note(long id, String content) {
        this.id = id;
        this.content = content;
    }

    public Note(String content) {
        this.content = content;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
