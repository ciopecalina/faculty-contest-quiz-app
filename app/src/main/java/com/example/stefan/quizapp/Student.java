package com.example.stefan.quizapp;

import android.database.sqlite.SQLiteDatabase;

public class Student // Helper class for the Student
{
    private String studentName;
    private String studentEmail;
    private String studentBirthDate;
    private String studentPassword;
    private String studentFaculty;

    public Student(String studentName, String studentEmail, String studentBirthDate, String studentPassword, String studentFaculty)
    {
        this.studentName = studentName;
        this.studentEmail = studentEmail;
        this.studentBirthDate = studentBirthDate;
        this.studentPassword = studentPassword;
        this.studentFaculty = studentFaculty;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentEmail() {
        return studentEmail;
    }

    public void setStudentEmail(String studentEmail) {
        this.studentEmail = studentEmail;
    }

    public String getStudentBirthDate() {
        return studentBirthDate;
    }

    public void setStudentBirthDate(String studentBirthDate)
    {
        this.studentBirthDate = studentBirthDate;
    }

    public String getStudentPassword() {
        return studentPassword;
    }

    public void setStudentPassword(String studentPassword)
    {
        this.studentPassword = studentPassword;
    }

    public String getStudentFaculty() {
        return studentFaculty;
    }

    public void setStudentFaculty(String studentFaculty) {
        this.studentFaculty = studentFaculty;
    }

    @Override
    public String toString()
    {
        return "Student{" +
                "studentName='" + studentName + '\'' +
                ", studentEmail='" + studentEmail + '\'' +
                ", studentBirthDate='" + studentBirthDate + '\'' +
                ", studentPassword='" + studentPassword + '\'' +
                ", studentFaculty='" + studentFaculty + '\'' +
                '}';
    }

    //Data Definition Language!(DDL) --- CREATE/DROP

    public static final String TABLE_NAME = "students"; // table name
    public static final String COLUMN_NAME = "studentName"; // attribute
    public static final String COLUMN_EMAIL = "studentEmail";//attribute
    public static final String COLUMN_DATE = "studentDateOfBirth"; // attribute
    public static final String COLUMN_PASSWORD= "studentPassword";//attribute
    public static final String COLUMN_FACULTY = "studentFaculty";//attribute
    public static final String COLUMN_GRADES = "studentGrades";//attribute

    private static final String TABLE_CREATE = "create table "
            // Here you create the string that you use in order to create that table that you are working with
            + TABLE_NAME
            + "(" + COLUMN_NAME + " text not null, "
            + COLUMN_EMAIL + " text primary key not null, "
            + COLUMN_DATE + " datetime not null, "
            + COLUMN_PASSWORD + " text not null, "
            + COLUMN_GRADES + " text , "
            + COLUMN_FACULTY + " text not null);";


    public static void onCreate(SQLiteDatabase db)
    // in onCreate method you give a database object as parameter
    {
        db.execSQL(TABLE_CREATE); // you create the database object using the .execSQL command
        // and give the TABLE_CREATE String as parameter in order to create the table
    }

    public static void onUpgrade(SQLiteDatabase db,int oldVers,int newVers)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME); // in the situation that the table exists
        // .execSQL can directly execute queries or execute strings
        // it deletes it

        // here you can have modifications of your local table
        onCreate(db);
        // and it re-writes it by calling onCreate(where you create the table)
    }

    //THE ID'S OF YOUR RECORDS ARE AUTOMATICALLY GENERATED
    //THE column_id is pk


}
