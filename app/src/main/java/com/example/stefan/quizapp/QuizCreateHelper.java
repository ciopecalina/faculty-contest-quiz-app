package com.example.stefan.quizapp;

import android.database.sqlite.SQLiteDatabase;

public class QuizCreateHelper
{
    //Data Definition Language!(DDL) --- CREATE/DROP


    public static final String TABLE_QUIZ = "quizzes"; // table name
    public static final String COLUMN_ID_1 = "quizId"; // attribute
    public static final String COLUMN_NAME = "quizName";//attribute

    public static final String QUIZ_TABLE_CREATE = "create table " + TABLE_QUIZ + "(" + COLUMN_ID_1 + " integer primary key autoincrement, " + COLUMN_NAME + " text not null);";


    public static void onCreate(SQLiteDatabase db)
    {
        db.execSQL(QUIZ_TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db,int oldVers,int newVers)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUIZ);
        onCreate(db);
    }

}

