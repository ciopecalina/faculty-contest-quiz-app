package com.example.stefan.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TeacherAccount extends AppCompatActivity {

    private TextView tvWelcome;
    private Button btnSubjects;
    private Button btnQuestions;
    private Button btnDetails;
    private Button btnAddQuiz;
    private String welcomeText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_page);

        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        tvWelcome = findViewById(R.id.tvTeacherName);
        btnSubjects = findViewById(R.id.btnTeacherSubjects);
        btnQuestions = findViewById(R.id.btnTeacherQuestions);
        btnDetails=findViewById(R.id.bttnTeacherAccountDetails);
        btnAddQuiz=findViewById(R.id.bttnAddQuiz);

        if(getIntent().hasExtra("email")){
           welcomeText = getIntent().getStringExtra("email");
            tvWelcome.setText("Hello " + welcomeText+ "!");
        }


        btnSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);
                startActivity(intent);
            }
        });

        btnQuestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), QuestionActivity.class);
                startActivity(intent);
            }
        });

        btnDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),TeacherDetailsActivity.class);
                intent.putExtra("email", welcomeText);
                startActivity(intent);
            }
        });

        btnAddQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),CreateQuiz.class);
                startActivity(intent);
            }
        });







    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        if(item.getItemId() == R.id.go_back)
        {
            Intent intent1=new Intent(getApplicationContext(),MainActivity.class);
            startActivity(intent1);
            finish();
        }
        else if(item.getItemId()==R.id.changePass)
        {
            Intent intent2=new Intent(getApplicationContext(),ChangePasswordActivity.class);
            intent2.putExtra("email",welcomeText);
            startActivity(intent2);
            finish();
        }
        else  if(item.getItemId()==R.id.customizeColor)
        {
            Intent intent=new Intent(getApplicationContext(),CustomizeActivity.class);
            startActivity(intent);

        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_student_teacher_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    private int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        return sharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
    }

}
