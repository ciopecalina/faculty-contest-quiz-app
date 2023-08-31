package com.example.stefan.quizapp;

import android.database.sqlite.SQLiteDatabase;

public class QuestionCreateHelper
{
    public static final String TABLE_QUESTION = "questions"; // table name
    public static final String COLUMN_ID_2 = "questionId"; // attribute
    public static final String COLUMN_TEXT = "questionText";//attribute
    public static final String COLUMN_A1 = "questionA1"; // attribute
    public static final String COLUMN_A2 = "questionA2"; // attribute
    public static final String COLUMN_A3 = "questionA3"; // attribute
    public static final String COLUMN_A4 = "questionA4"; // attribute
    public static final String COLUMN_AC = "questionAC"; // attribute
    public static final String COLUMN_QuizId = "questionQuizId"; // attribute

    public static final String TABLE_QUIZ = "quizzes"; // table name

    public static final String QUESTION_TABLE_CREATE = "create table "
            + TABLE_QUESTION
            + "(" + COLUMN_ID_2 + " integer primary key autoincrement, "
            + COLUMN_TEXT + " text not null, "
            + COLUMN_A1 + " text not null, "
            + COLUMN_A2 + " text not null, "
            + COLUMN_A3 + " text not null, "
            + COLUMN_A4 + " text not null, "
            + COLUMN_AC + " text not null, "
            + COLUMN_QuizId + " integer not null , FOREIGN KEY(questionQuizId) REFERENCES " + TABLE_QUIZ + "(quizId)" + ");";


    public static void onUpgrade(SQLiteDatabase db,int oldVers,int newVers)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUESTION);
        onCreate(db);
    }

    public static void onCreate(SQLiteDatabase db) {
        db.execSQL(QUESTION_TABLE_CREATE);
    }
}
