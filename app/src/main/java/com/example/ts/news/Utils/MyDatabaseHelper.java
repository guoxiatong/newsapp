package com.example.ts.news.Utils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

//*
// 用于创建一个继承SQLiteHelper的类
// 用于获取到我们的数据库类
// 或者用于执行sql语句
// */

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private Context mContext;//上下文
    //创造我们的user表
    public static final String CREATE_USER = "create table User ("
            + "id integer primary key autoincrement,"
            + "name text,"
            + "password text)";
    //创造收集新闻的表
    public static final String CREATE_COLLECTION_NEWS = "create table Collection_News ("
            + "id integer primary key autoincrement,"
            + "news_title text,"
            + "news_date text,"
            + "news_author text,"
            + "news_picurl text,"
            + "news_url text)";


    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_USER);
        sqLiteDatabase.execSQL(CREATE_COLLECTION_NEWS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
