package com.example.memhelper.entity;
//字符类
public class Char {
    private String ch; //一个字符
    private boolean hidden; //如果hidden==true，则字符显示为黑块

    public Char(String ch){
        this.ch = ch;
        hidden = false;
    }

    public Char(String ch, boolean hidden){
        this.ch = ch;
        this.hidden = hidden;
    }

    public void flip(){
        hidden = !hidden;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

}
