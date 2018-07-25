package com.example.dell.sphinx_project;

import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Dell on 16-01-2018.
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    public MyDataBaseHelper(Context context)
    {	super(context, "quizdb", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table resultmaster(subjectid int,topicid int,emailid text,total int,correct int,wrong int,na int,daytime bigint)");
        db.execSQL("create table questioncount(emailid text,qno text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
        db.execSQL("drop table if exists resultmaster");
        db.execSQL("drop table if exists questioncount");
        db.execSQL("create table resultmaster(subjectid int,topicid int,emailid text,total int,correct int,wrong int,na int,daytime bigint)");
        db.execSQL("create table questioncount(emailid text,qno text)");
    }

}
