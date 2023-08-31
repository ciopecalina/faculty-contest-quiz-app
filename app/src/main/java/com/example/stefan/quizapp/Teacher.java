package com.example.stefan.quizapp;

import android.database.sqlite.SQLiteDatabase;

public class Teacher // Helper class for the Teacher
{
    private String teacherName;
    private String teacherEmail;
    private String teacherBirthDate;
    private String teacherPassword;
    private String teacherFaculty;

    public Teacher(String teacherName, String teacherEmail, String teacherBirthDate, String teacherPassword, String teacherFaculty)
    {
        this.teacherName = teacherName;
        this.teacherEmail = teacherEmail;
        this.teacherBirthDate = teacherBirthDate;
        this.teacherPassword = teacherPassword;
        this.teacherFaculty = teacherFaculty;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherEmail() {
        return teacherEmail;
    }

    public void setTeacherEmail(String teacherEmail) {
        this.teacherEmail = teacherEmail;
    }

    public String getTeacherBirthDate() {
        return teacherBirthDate;
    }

    public void setTeacherBirthDate(String teacherBirthDate)
    {
        this.teacherBirthDate = teacherBirthDate;
    }

    public String getTeacherPassword() {
        return teacherPassword;
    }

    public void setTeacherPassword(String teacherPassword)
    {
        this.teacherPassword = teacherPassword;
    }

    public String getTeacherFaculty() {
        return teacherFaculty;
    }

    public void setTeacherFaculty(String teacherFaculty) {
        this.teacherFaculty = teacherFaculty;
    }

    @Override
    public String toString()
    {
        return "Teacher{" +
                "teacherName='" + teacherName + '\'' +
                ", teacherEmail='" + teacherEmail + '\'' +
                ", teacherBirthDate='" + teacherBirthDate + '\'' +
                ", teacherPassword='" + teacherPassword + '\'' +
                ", teacherFaculty='" + teacherFaculty + '\'' +
                '}';
    }

    //Data Definition Language!(DDL) --- CREATE/DROP

    public static final String TABLE_NAME = "teachers"; // table name
    public static final String COLUMN_NAME = "teacherName"; // attribute
    public static final String COLUMN_EMAIL = "teacherEmail";//attribute
    public static final String COLUMN_DATE = "teacherDateOfBirth"; // attribute
    public static final String COLUMN_PASSWORD= "teacherPassword";//attribute
    public static final String COLUMN_FACULTY = "teacherFaculty";//attribute


    private static final String TABLE_CREATE = "create table "
            + TABLE_NAME
            + "(" + COLUMN_NAME + " text not null, "
            + COLUMN_EMAIL + " text primary key not null, "
            + COLUMN_DATE + " datetime not null, "
            + COLUMN_PASSWORD + " text not null, "
            + COLUMN_FACULTY + " text not null);";


    public static void onCreate(SQLiteDatabase db)
    {
        db.execSQL(TABLE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase db,int oldVers,int newVers)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); // in the situation that the table exists

        onCreate(db);

    }



}
