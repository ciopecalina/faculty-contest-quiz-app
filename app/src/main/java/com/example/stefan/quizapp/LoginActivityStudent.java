package com.example.stefan.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivityStudent extends AppCompatActivity {

    private Button btnCancel;
    private TextView tvEmail;
    private TextView tvPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_student);

        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        btnCancel = findViewById(R.id.btnCancel);

        tvEmail = findViewById(R.id.inputLoginStudentEmail);
        tvPass = findViewById(R.id.inputLoginStudentPass);

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
                Toast.makeText(getApplicationContext(),"One of the fields is emply!",Toast.LENGTH_SHORT);
            }
            else
            {
                //DATABASE
                String email=tvEmail.getText().toString();
                DatabaseContract database=new DatabaseContract(getApplicationContext());
                Cursor cursor = database.getStudentLoginCursor(email);
                if(cursor.getCount()<1)
                {
                    Toast.makeText(getApplicationContext(), "The email "+email+" is not in our database!", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    cursor.moveToFirst();

                    String emailStudent = cursor.getString(cursor.getColumnIndex(Student.COLUMN_EMAIL));
                    String password = cursor.getString(cursor.getColumnIndex(Student.COLUMN_PASSWORD));

                    if((tvEmail.getText().toString().equals(emailStudent))&&(tvPass.getText().toString().equals(password)))
                    {
                        Intent intent = new Intent(getApplicationContext(),StudentAccount.class);
                        intent.putExtra("email", tvEmail.getText().toString());
                        startActivity(intent);
                    }
                    else if((!tvEmail.getText().toString().equals(emailStudent)))
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
