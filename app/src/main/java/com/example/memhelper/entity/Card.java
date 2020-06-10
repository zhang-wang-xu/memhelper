package com.example.memhelper.entity;

public class Card {
    protected int cardId;
    protected int cardsetId;
    protected String front;
    protected String back;
    protected int wrong; //错误次数

    public Card(int cardsetId, String front, String back){
        this.cardsetId = cardsetId;
        this.front = front;
        this.back = back;
        wrong = 0;
    }

    public Card(){
        wrong = 0;
    }

    public int getCardId() {
        return cardId;
    }

    public void setCardId(int cardId) {
        this.cardId = cardId;
    }

    public int getCardsetId() {
        return cardsetId;
    }

    public void setCardsetId(int cardsetId) {
        this.cardsetId = cardsetId;
    }

    public String getFront() {
        return front;
    }

    public void setFront(String front) {
        this.front = front;
    }

    public String getBack() {
        return back;
    }

    public void setBack(String back) {
        this.back = back;
    }

    public int getWrong() {
        return wrong;
    }

    public void setWrong(int wrong) {
        this.wrong = wrong;
    }

    @Override
    public String toString() {
        return "Card{" +
                "cardsetId=" + cardsetId +
                ", front='" + front + '\'' +
                ", back='" + back + '\'' +
                '}';
    }
}
