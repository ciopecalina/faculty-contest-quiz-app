package com.example.stefan.quizapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class DatabaseContract extends SQLiteOpenHelper
{

    // you will need these 2 for the constructor that is required because of the "extends SQLiteOpenHelper"

    public static final String DATABASE_NAME ="Database7.sqLite.db"; // string for the name of the database
    private static final int DB_VERS = 1;//Version of the database

    private static final String TAG = DatabaseContract.class.getSimpleName(); // TAG for the toast or the Logcat

    private Context lContext;
    // Context: to use for locating paths to the the database
    // you take the context of the class where you are located


    // Constructor with one attribute , only the context
    public DatabaseContract(Context context)// it gets this (the context) as a parameter
    {
        super(context,DATABASE_NAME, null, DB_VERS);
        this.lContext=context;
    }

    //onCreate and onUpgrade are methods that we need to overwrite because of the
    //extends SQLiteOpenHelper;
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        Teacher.onCreate(db);
        Student.onCreate(db);
        QuizCreateHelper.onCreate(db);
        QuestionCreateHelper.onCreate(db);

        // Called when the database is created for the first time.
        // Here we create the tables that we need to use
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        Teacher.onUpgrade(db,oldVersion,newVersion);
        Student.onUpgrade(db,oldVersion, newVersion);
        QuizCreateHelper.onUpgrade(db,oldVersion, newVersion);
        QuestionCreateHelper.onUpgrade(db,oldVersion,newVersion);
        // it updates by version
        // Called when the database needs to be upgraded.
        // The implementation should use this method to drop tables, add tables,
        // or do anything else it needs to upgrade to the new schema version.
    }

    // Data definition language (DDL):
    // 4 INSERT (1 x table )
    // 6 SELECT (3 x table )
    // 2 DELETE (1 x table )

    // Insert - 4

    //Insert for The Teacher Table

    public void insertTeacher(String name,String email,String date,String pass,String department)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Teacher.COLUMN_NAME,name);
        cv.put(Teacher.COLUMN_EMAIL,email);
        cv.put(Teacher.COLUMN_DATE,date);
        cv.put(Teacher.COLUMN_PASSWORD,pass);
        cv.put(Teacher.COLUMN_FACULTY,department);
        long result = db.insert(Teacher.TABLE_NAME,null,cv);

        Log.d(TAG,"Inserted value: " + result);
        Toast.makeText(lContext,"Inserted value:" + result,Toast.LENGTH_LONG).show();
    }

    //Insert for The Student Table

    public void insertStudent(String name,String email,String date,String pass,String department)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Student.COLUMN_NAME,name);
        cv.put(Student.COLUMN_EMAIL,email);
        cv.put(Student.COLUMN_DATE,date);
        cv.put(Student.COLUMN_PASSWORD,pass);
        cv.put(Student.COLUMN_FACULTY,department);
        cv.put(Student.COLUMN_GRADES,"");
        long result = db.insert(Student.TABLE_NAME,null,cv);

        Log.d(TAG,"Inserted value: " + result);
        Toast.makeText(lContext,"Inserted value:" + result,Toast.LENGTH_LONG).show();
    }

    //Insert for the Quiz Table

    public void insertQuiz(String name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(QuizCreateHelper.COLUMN_NAME,name);
        long result = db.insert(QuizCreateHelper.TABLE_QUIZ,null,cv);

        Log.d(TAG,"Inserted value: " + result);
        Toast.makeText(lContext,"Inserted value:" + result,Toast.LENGTH_LONG).show();
    }

    //Insert for the Question Table

    public void insertQuestion(String text,String a1,String a2,String a3,String a4,String aCorrect,Integer QuizId)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(QuestionCreateHelper.COLUMN_TEXT,text);
        cv.put(QuestionCreateHelper.COLUMN_A1,a1);
        cv.put(QuestionCreateHelper.COLUMN_A2,a2);
        cv.put(QuestionCreateHelper.COLUMN_A3,a3);
        cv.put(QuestionCreateHelper.COLUMN_A4,a4);
        cv.put(QuestionCreateHelper.COLUMN_AC,aCorrect);
        cv.put(QuestionCreateHelper.COLUMN_QuizId,QuizId);
        long result = db.insert(QuestionCreateHelper.TABLE_QUESTION,null,cv);

        Log.d(TAG,"Inserted value: " + result);
        Toast.makeText(lContext,"Inserted value:" + result,Toast.LENGTH_LONG).show();
    }

    // Select - 6

    //Selects for Teacher Table - 3

    public Cursor getTeacherDataCursor(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] args = new String[]{email};
        Cursor cursor=db.rawQuery(" select teacherName,teacherEmail,teacherDateOfBirth,teacherFaculty from teachers where teacherEmail=?",args);
        return cursor;
    }

    public Cursor getTeacherLoginCursor(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] args = new String[]{email};
        Cursor cursor=db.rawQuery(" select teacherEmail,teacherPassword from teachers where teacherEmail=?",args);
        return cursor;
    }

    public Cursor getTeacherRegisterCursor(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] args = new String[]{name};
        Cursor cursor=db.rawQuery(" select teacherName from teachers where teacherName=?",args);
        return cursor;
    }

    //Selects for Student Table - 3

    public Cursor getStudentCount()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" select studentName from students ",null);
        return cursor;
    }

    public Cursor getTeacherCount()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" select teacherName from teachers ",null);
        return cursor;
    }

    public Cursor getStudentDataCursor(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] args = new String[]{email};
        Cursor cursor=db.rawQuery(" select studentName,studentEmail,studentDateOfBirth,studentFaculty,StudentGrades from students where studentEmail=?",args);
        return cursor;
    }

    public Cursor getStudentLoginCursor(String email)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] args = new String[]{email};
        Cursor cursor=db.rawQuery(" select studentEmail,studentPassword from students where studentEmail=?",args);
        return cursor;
    }

    public Cursor getStudentRegisterCursor(String name)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String[] args = new String[]{name};
        Cursor cursor=db.rawQuery(" select studentName from students where studentName=?",args);
        return cursor;
    }

    //Selects for Quiz Table  -1

    public Cursor getQuizId()
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery(" select * from quizzes",null);
        return cursor;
    }

    //Selects for Question Table
    public Cursor getQuestionsByQuizId(Integer id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String []args={String.valueOf(id)};
        Cursor cursor=db.rawQuery("select questionText FROM questions a INNER JOIN quizzes b ON a.questionQuizId=b.quizId WHERE a.quizId=?",args);
        return cursor;
    }

    public Cursor getQAByQuizId(Integer id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String []args={String.valueOf(id)};
        Cursor cursor=db.rawQuery("select questionText,questionA1,questionA2,questionA3,questionA4,questionAC FROM questions a INNER JOIN quizzes b ON a.questionQuizId=b.quizId WHERE a.questionQuizId=?",args);
        return cursor;
    }

    public Cursor getQuestionCorrectAnswer(Integer id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        String []args={String.valueOf(id)};
        Cursor cursor=db.rawQuery("select questionId,questionText,questionAC FROM questions WHERE a.quizId=?",args);
        return cursor;
    }
    
    //Delete - 2

    //Delete for the Teacher Table

    public void deleteTeacherById(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String[] args = new String[]{email};
        int result = db.delete(Teacher.TABLE_NAME,"teacherEmail=? ",args);
        Log.d(TAG,"Item Deleted");
    }

    //Delete for the Student Table

    public void deleteStudentById(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String[] args = new String[]{email};
        int result = db.delete(Student.TABLE_NAME,"studentEmail=? ",args);
        Log.d(TAG,"Item Deleted");
    }

    //UPDATE

    //Update for Student Password
    public void updateStudentPassword(String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String[] args = new String[]{email};
        ContentValues contentValues = new ContentValues();
        contentValues.put("studentPassword",password);
        int result = db.update(Student.TABLE_NAME,contentValues,"studentEmail=?",args);
        Log.d(TAG,"Item Deleted");

    }

    public Cursor getGrades(String email)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String[] args = new String[]{email};
        Cursor cursor=db.rawQuery(" select studentGrades from students where studentEmail=?",args);
        return cursor;
    }

    public void updateStudentGrade(String email,Integer grade)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String[] args = new String[]{email};
        Cursor cursor=db.rawQuery(" select studentGrades from students where studentEmail=?",args);
        cursor.moveToNext();
        String grades=cursor.getString(cursor.getColumnIndex(Student.COLUMN_GRADES));
        ContentValues cv=new ContentValues();
        cv.put("studentGrades",grades+","+grade);
        int result = db.update(Student.TABLE_NAME,cv,"studentEmail=?",args);
        Log.d(TAG,"Grade added!");

    }

    //Update for Teacher Password
    public void updateTeacherPassword(String email,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        String[] args = new String[]{email};
        ContentValues contentValues = new ContentValues();
        contentValues.put("teacherPassword",password);
        int result = db.update(Teacher.TABLE_NAME,contentValues,"teacherEmail=?",args);
        Log.d(TAG,"Item Deleted");

    }

}
