package com.example.stefan.quizapp;

import android.database.sqlite.SQLiteDatabase;

public class Quiz
{
    private String QuizId;
    private String QuizName;

    public Quiz(String quizId, String quizName)
    {
        QuizId = quizId;
        QuizName = quizName;
    }

    public String getQuizId() {
        return QuizId;
    }

    public void setQuizId(String quizId) {
        QuizId = quizId;
    }

    public String getQuizName() {
        return QuizName;
    }

    public void setQuizName(String quizName) {
        QuizName = quizName;
    }

    @Override
    public String toString() {
        return "Quiz{" +
                "QuizId='" + QuizId + '\'' +
                ", QuizName='" + QuizName + '\'' +
                '}';
    }




}
