package com.example.stefan.quizapp;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ChangePasswordActivity extends AppCompatActivity
{
    private TextView tvWelcome;
    private TextView tvOld;
    private TextView tvNew;
    private Button btnDone;
    private String welcomeText;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);

        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        tvWelcome=findViewById(R.id.tvWelcomePass);
        tvOld=findViewById(R.id.tvOldPass);
        tvNew=findViewById(R.id.tvNewPass);
        btnDone=findViewById(R.id.bttnPassDone);

        if(getIntent().hasExtra("email"))
        {
            welcomeText = getIntent().getStringExtra("email");
            tvWelcome.setText("Hello "+welcomeText+",you will be automatically disconnected after changing the password!");
        }

        btnDone.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String TeacherPass;
                String StudentPass;

                DatabaseContract database=new DatabaseContract(getApplicationContext());
                Cursor cursorTeacher=database.getTeacherLoginCursor(welcomeText);
                Cursor cursorStudent=database.getStudentLoginCursor(welcomeText);

                if(cursorTeacher.getCount()>0)
                {
                    cursorTeacher.moveToNext();
                    TeacherPass=cursorTeacher.getString(cursorTeacher.getColumnIndex(Teacher.COLUMN_PASSWORD));

                    if (!cursorTeacher.isClosed())
                    {
                        cursorTeacher.close();
                    }
                    if(tvOld.getText().toString().equals(TeacherPass))
                    {
                        if(tvNew.getText().length() > 5)
                        {
                            database.updateTeacherPassword(welcomeText,tvNew.getText().toString());
                            database.close();
                            Toast.makeText(getApplicationContext(),"Your password was changed succesfully, you are now again at the login screen! ",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Error,the new password is too short! ",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Error,please insert the correct actual password!",Toast.LENGTH_SHORT).show();
                    }
                }
                else if(cursorStudent.getCount()>0)
                {
                    cursorStudent.moveToNext();
                    StudentPass=cursorStudent.getString(cursorStudent.getColumnIndex(Student.COLUMN_PASSWORD));

                    if (!cursorStudent.isClosed())
                    {
                        cursorStudent.close();
                    }
                    if(tvOld.getText().toString().equals(StudentPass))
                    {
                        if(tvNew.getText().length() > 5)
                        {
                            database.updateStudentPassword(welcomeText,tvNew.getText().toString());
                            database.close();
                            Toast.makeText(getApplicationContext(),"Your password was changed succesfully, you are now again at the login screen! ",Toast.LENGTH_LONG).show();
                            finish();
                        }
                        else
                        {
                            Toast.makeText(getApplicationContext(),"Error,the new password is too short! ",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(),"Error,please insert the correct actual password!",Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        return sharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
    }


}
