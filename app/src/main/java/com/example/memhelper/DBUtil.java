package com.example.memhelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

import com.example.memhelper.activity.CarsetActivity;
import com.example.memhelper.entity.Card;
import com.example.memhelper.entity.Cardset;
import com.example.memhelper.entity.Char;
import com.example.memhelper.entity.Passage;

public class DBUtil {
    DBHelper helper;
    SQLiteDatabase database;

    public DBUtil(DBHelper h){
        helper = h;
    }

    //获取数据库句柄
    private void before(){
        database = helper.getWritableDatabase();
    }
    //关闭数据库
    private void after(){
        database.close();
    }

    public void deleteCard(int cardId){
        before();
        database.delete("card", "cardId=?", new String[]{cardId+""});
        after();
    }

    public void modifyCard(int cardId, String front, String back){
        before();
        ContentValues contentValues = new ContentValues();
        contentValues.put("front", front);
        contentValues.put("back", back);
        database.update("card",contentValues,"cardId=?",new String[]{cardId+""});
        after();
    }

    public List<Card> getCardByCardsetId(int cardsetId){
        before();
        Cursor cursor = database.query("card",null,"cardsetId=?", new String[]{cardsetId+""},null,null,null);
        if(cursor.isBeforeFirst()) cursor.moveToFirst();
        List<Card> list = new LinkedList<>();
        Card card;
        while(cursor.isAfterLast() == false){
            card = new Card();
            card.setCardsetId(cardsetId);
            card.setCardId(cursor.getInt(0));
            card.setFront(cursor.getString(1));
            card.setBack(cursor.getString(2));
            list.add(card);
            cursor.moveToNext();
        }
        cursor.close();
        after();
        return list;
    }

    public void addCard(Card card){
        before();
        ContentValues contentValues = new ContentValues();
        contentValues.put("cardsetId", card.getCardsetId());
        contentValues.put("front", card.getFront());
        contentValues.put("back", card.getBack());
        database.insert("card", null, contentValues);
        after();
    }

    public Passage getPassageById(int passageId){
        before();
        Cursor cursor = database.query("passage", null, "passageId=?", new String[]{passageId+""},null,null,null);
        cursor.moveToFirst();
        Passage passage = null;
        if(!cursor.isAfterLast()){
            passage = new Passage();
            passage.setPassageId(cursor.getInt(0));
            passage.setTitle(cursor.getString(1));
            passage.setContent(cursor.getString(2));
        }
        cursor.close();
        after();
        return passage;
    }

    public void modifyPassage(int passageId, ArrayList<Char> passage, String title){
        before();
        StringBuffer stringBuffer = new StringBuffer();
        for(Char ch : passage){
            stringBuffer.append(ch.isHidden() ? "1" : "0");
            stringBuffer.append(ch.getCh());
        }
        String text = stringBuffer.toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", text);
        database.update("passage",contentValues,"passageId=?",new String[]{passageId+""});
        after();
    }

    //将篇章插入数据库
    //如果篇章中的字符是隐藏的，就在前面加一个"1"，如果不是隐藏的，就在前面加一个"0"
    //插入的数据是这样的：
    //0床0前0明0月0光0，1疑1是1地1上1霜0。
    public void addPassage(ArrayList<Char> passage, String title){
        before();
        //database.delete("passage", null, null);
        StringBuffer stringBuffer = new StringBuffer();
        for(Char ch : passage){
            stringBuffer.append(ch.isHidden() ? "1" : "0");
            stringBuffer.append(ch.getCh());
        }
        String text = stringBuffer.toString();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", text);
        database.insert("passage", null, contentValues);
        after();
    }

    public String testPassage(){
        before();
        Cursor cursor = database.query("passage",null,null,null,null,null,"passageId", "0,1");
        cursor.moveToFirst();
        String text = cursor.getString(cursor.getColumnIndex("content"));
        after();
        return text;
    }

    //读取所有篇章
    public List<Passage> getPassages(){
        before();
        Cursor cursor = database.query("passage", null, null, null, null, null, null);
        if(cursor.isBeforeFirst()) cursor.moveToFirst();
        List<Passage> passages = new LinkedList<>();
        Passage passage;
        while(cursor.isAfterLast() == false){
            passage = new Passage();
            passage.setPassageId(cursor.getInt(0));
            passage.setTitle(cursor.getString(1));
            passages.add(passage);
            cursor.moveToNext();
        }
        cursor.close();
        after();
        return passages;
    }

    public List<Cardset> getCardsets(){
        before();
        Cursor cursor = database.query("cardset", null, null, null, null, null, null);
        if(cursor.isBeforeFirst()) cursor.moveToFirst();
        List<Cardset> cardsets = new LinkedList<>();
        Cardset cardset;
        while(cursor.isAfterLast() == false){
            cardset = new Cardset();
            cardset.setPassageId(cursor.getInt(0));
            cardset.setTitle(cursor.getString(1));
            cardsets.add(cardset);
            cursor.moveToNext();
        }
        cursor.close();
        after();
        return cardsets;
    }
    //添加篇章
    //输入：标题、正文
    public void insertPassage(Passage passage){
        before();
        String title = passage.getTitle();
        String content = passage.getContent();
        ContentValues contentValues = new ContentValues();
        contentValues.put("title", title);
        contentValues.put("content", content);
        database.insert("passage", null, contentValues);
        after();
    }
    public void insertCardset(Cardset cardset){
        before();
        String title = cardset.getTitle();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", title);
        database.insert("cardset", null, contentValues);
        after();
    }
    public void deleteCardset(Cardset cardset)
    {
        before();
        String title[]=new String[1];
        title[0]=cardset.getTitle();
        database.delete("cardset", "name=?", title);
        after();
    }
}
