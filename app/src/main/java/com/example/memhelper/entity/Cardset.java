package com.example.memhelper.entity;

import java.io.Serializable;

public class Cardset implements Serializable {
    private int CardsetId;
    private String title;
    private String username;

    public Cardset(){}

    public Cardset(int id, String t){
        CardsetId = id;
        title = t;
    }

    public Cardset(String title) {
        this.title = title;
    }



    public Cardset(int passageId, String title, String username) {
        this.CardsetId = passageId;
        this.title = title;
        this.username = username;
    }

    public int getPassageId() {
        return CardsetId;
    }

    public void setPassageId(int passageId) {
        this.CardsetId = passageId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
