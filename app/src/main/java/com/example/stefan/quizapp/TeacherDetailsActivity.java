package com.example.stefan.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.stefan.quizapp.DatabaseContract;

public class TeacherDetailsActivity extends AppCompatActivity {

    private TextView tvNm;
    private TextView tvEmail;
    private TextView tvBirthDate;
    private TextView tvFaculty;
    private String email;
    private Button btnDeleteTeacher;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);

        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        tvNm=findViewById(R.id.tvDetailsTeacherName);
        tvEmail=findViewById(R.id.tvDetailsTeacherEmail);
        tvBirthDate=findViewById(R.id.tvDetailsTeacherBirthDate);
        tvFaculty=findViewById(R.id.tvDetailsTeacherFaculty);
        btnDeleteTeacher=findViewById(R.id.buttonDeleteTeacherAccount);

        if(getIntent().hasExtra("email"))
        {
            email = getIntent().getStringExtra("email");
        }

        DatabaseContract database=new DatabaseContract(getApplicationContext());
        Cursor cursor = database.getTeacherDataCursor(email);
        cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndex(Teacher.COLUMN_NAME));
        final String email2 = cursor.getString(cursor.getColumnIndex(Teacher.COLUMN_EMAIL));
        String date = cursor.getString(cursor.getColumnIndex(Teacher.COLUMN_DATE));
        String faculty=cursor.getString(cursor.getColumnIndex(Teacher.COLUMN_FACULTY));

        if (!cursor.isClosed())
        {
            cursor.close();
        }

        tvNm.setText("Account name: " + name);
        tvEmail.setText("Email : " + email2);
        tvBirthDate.setText("Date of birth: " + date);
        tvFaculty.setText("Department: " + faculty);

        btnDeleteTeacher.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(TeacherDetailsActivity.this);
                builder.setTitle("Deletion");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        DatabaseContract database=new DatabaseContract(getApplicationContext());
                        database.deleteTeacherById(email2);
                        Toast.makeText(getApplicationContext(),"Your account has been deleted!",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                });
                builder.setNegativeButton("NO",null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

    }


    private int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        return sharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
    }
}
