package com.example.memhelper.entity;

public class Passage {
    private int passageId;
    private String title;
    private String content;
    private String username;

    public Passage(){}

    public Passage(int id, String t){
        passageId = id;
        title = t;
    }

    public Passage(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Passage(int passageId, String title, String content) {
        this.passageId = passageId;
        this.title = title;
        this.content = content;
    }

    public Passage(int passageId, String title, String content, String username) {
        this.passageId = passageId;
        this.title = title;
        this.content = content;
        this.username = username;
    }

    public int getPassageId() {
        return passageId;
    }

    public void setPassageId(int passageId) {
        this.passageId = passageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
