package com.example.memhelper;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.List;
import java.util.LinkedList;

import com.example.memhelper.activity.CarsetActivity;
import com.example.memhelper.entity.Cardset;
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
}
