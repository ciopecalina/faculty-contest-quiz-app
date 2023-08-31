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

public class StudentDetailsActivity extends AppCompatActivity {

    private TextView tvNm;
    private TextView tvEmail;
    private TextView tvBirthDate;
    private TextView tvFaculty;
    private String email;
    private Button btnDeleteStudent;
    private TextView tvTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_details);

        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        tvNm=findViewById(R.id.tvDetailsStudentName);
        tvEmail=findViewById(R.id.tvDetailsStudentEmail);
        tvBirthDate=findViewById(R.id.tvDetailsStudentBirthDate);
        tvFaculty=findViewById(R.id.tvDetailsStudentFaculty);
        btnDeleteStudent=findViewById(R.id.btnDeleteStudentAccount);
        tvTemp=findViewById(R.id.tvTemp);


        if(getIntent().hasExtra("email"))
        {
            email = getIntent().getStringExtra("email");
        }

        DatabaseContract database=new DatabaseContract(getApplicationContext());
        Cursor cursor = database.getStudentDataCursor(email);
        cursor.moveToFirst();

        String name = cursor.getString(cursor.getColumnIndex(Student.COLUMN_NAME));
        final String email2 = cursor.getString(cursor.getColumnIndex(Student.COLUMN_EMAIL));
        String date = cursor.getString(cursor.getColumnIndex(Student.COLUMN_DATE));
        String faculty=cursor.getString(cursor.getColumnIndex(Student.COLUMN_FACULTY));
        String grades=cursor.getString(cursor.getColumnIndex(Student.COLUMN_GRADES));

        if (!cursor.isClosed())
        {
            cursor.close();
        }

        tvNm.setText("Account name: " + name);
        tvEmail.setText("Email: " + email2);
        tvBirthDate.setText("Date of birth: " + date);
        tvFaculty.setText("Department: " + faculty);

        btnDeleteStudent.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(StudentDetailsActivity.this);
                builder.setTitle("Deletion");
                builder.setMessage("Are you sure?");
                builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        DatabaseContract database=new DatabaseContract(getApplicationContext());
                        database.deleteStudentById(email2);
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
