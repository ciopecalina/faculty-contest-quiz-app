package com.example.stefan.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivityProfessor extends AppCompatActivity {

    private Button btnCancel;
    private TextView tvEmail;
    private TextView tvPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_professor);

        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        btnCancel = findViewById(R.id.btnCancel);

        tvEmail = findViewById(R.id.inputLoginTeacherEmail);
        tvPass = findViewById(R.id.inputLoginTeacherPass);


        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Cancel test!", Toast.LENGTH_SHORT).show();
                finish();
            }

        });

    }

    public void loginAttempt(View view)
    {
        if(tvEmail!=null&&tvPass!=null)
        {
            if(tvEmail.getText().toString().equals("")||tvPass.getText().toString().equals(""))
            {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setTitle("Invalid input!");
                builder.setMessage("one of the fields is invalid");
                builder.setNeutralButton("OK", null);
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            else
            {
                //DATABASE
                String email=tvEmail.getText().toString();
                DatabaseContract database=new DatabaseContract(getApplicationContext());
                Cursor cursor = database.getTeacherLoginCursor(email);
                if(cursor.getCount()<1)
                {
                    Toast.makeText(getApplicationContext(), "The email "+email+" is not in our database!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    cursor.moveToFirst();

                    String emailTeacher = cursor.getString(cursor.getColumnIndex(Teacher.COLUMN_EMAIL));
                    String password = cursor.getString(cursor.getColumnIndex(Teacher.COLUMN_PASSWORD));

                    if((tvEmail.getText().toString().equals(emailTeacher))&&(tvPass.getText().toString().equals(password)))
                    {
                        Intent intent = new Intent(getApplicationContext(), TeacherAccount.class);
                        intent.putExtra("email", tvEmail.getText().toString());
                        startActivity(intent);
                    }
                    else if((!tvEmail.getText().toString().equals(emailTeacher)))
                    {
                        Toast.makeText(getApplicationContext(), "The email input is wrong", Toast.LENGTH_SHORT).show();
                    }
                    else if (!tvPass.getText().toString().equals(password))
                    {
                        Toast.makeText(getApplicationContext(), "The password input is wrong", Toast.LENGTH_SHORT).show();
                    }
                    if (!cursor.isClosed())
                    {
                        cursor.close();
                    }

                }


            }
        }
    }

    private int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        return sharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
    }

}
