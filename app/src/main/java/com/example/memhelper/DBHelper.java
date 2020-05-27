package com.example.memhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "memhelper.db";
    private static final int DB_VERSION = 1;
    public DBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableUser =
                "create table user(username primary key, password text not null);";
        String createTablePassage =
                "create table passage(" +
                        "passageId integer primary key autoincrement," +
                        "title text not null," +
                        "content text not null," +
                        "username text default 'zzl');";
        String createTableBar =
                "create table bar(" +
                        "barId integer primary key autoincrement," +
                        "passageId integer not null," +
                        "start integer not null," +
                        "end integer not null);";
        String createTableCardset =
                "create table cardset(" +
                        "cardsetId integer primary key autoincrement," +
                        "name text not null," +
                        "username text default 'zzl');";
        String createTableCard =
                "create table card(" +
                        "cardId integer primary key autoincrement," +
                        "front text not null," +
                        "back text not null," +
                        "wrong integer);";
        String createTableCardmapping =
                "create table cardmapping(" +
                        "cardId integer not null," +
                        "cardsetId integer not null);";
        db.execSQL(createTableBar);
        db.execSQL(createTableCard);
        db.execSQL(createTableCardmapping);
        db.execSQL(createTableCardset);
        db.execSQL(createTablePassage);
        db.execSQL(createTableUser);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
