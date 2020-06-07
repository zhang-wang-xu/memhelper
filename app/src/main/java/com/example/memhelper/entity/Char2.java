package com.example.memhelper.entity;

public class Char2 extends Char {
    private boolean erased = false;

    public Char2(String ch) {
        super(ch);
    }

    public Char2(String ch, boolean hidden) {
        super(ch, hidden);
    }

    public boolean isErased() {
        return erased;
    }

    public void setErased(boolean erased) {
        this.erased = erased;
    }
}
