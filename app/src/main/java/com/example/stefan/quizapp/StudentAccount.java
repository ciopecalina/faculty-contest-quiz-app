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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CursorAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class StudentAccount extends AppCompatActivity {

    TextView tvWelcome;
    private Button btnSubjects;
    private Button bttnStudentAccountDetails;
    private String welcomeText;
    private Spinner spinnerQuiz;
    private Button bttnTakeQuiz;
    private TextView tvQuizSelection;
    private String selectedItem;
    private Button bttnGrades;
    public static String EmailToBePassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_page);

        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        tvWelcome = findViewById(R.id.tvStudentName);
        btnSubjects = findViewById(R.id.btnStudentSubjects);
        bttnStudentAccountDetails = findViewById(R.id.bttnStudentAccountDetails);
        spinnerQuiz = findViewById(R.id.spinnerQuizes);
        bttnTakeQuiz = findViewById(R.id.bttnTakeTest);
        bttnGrades = findViewById(R.id.buttonGrades);
        tvQuizSelection=findViewById(R.id.tvQuizSelection);

        List<String> quizes = new ArrayList<String>();

        DatabaseContract db = new DatabaseContract(getApplicationContext());
        Cursor cursor1=db.getQuizId();

        bttnGrades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewGrades.class);
                startActivity(intent);
            }
        });

        if(cursor1.getCount()==0)
        {
            Toast.makeText(getApplicationContext(),"There are no quizzes to take yet!",Toast.LENGTH_LONG).show();
        }
        else
        {
            cursor1.moveToFirst();

            while(!cursor1.isAfterLast())
            {
                quizes.add(cursor1.getString(cursor1.getColumnIndex("quizId")));
                cursor1.moveToNext();
            }
            ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item,quizes);
            spinnerQuiz.setAdapter(dataAdapter);
            spinnerQuiz.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
            {
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
                {
                    selectedItem = parent.getItemAtPosition(position).toString();
                    tvQuizSelection.setText("Quiz "+ selectedItem +" is selected!");
                }
                // to close the onItemSelected
                public void onNothingSelected(AdapterView<?> parent)
                {

                }
            });
        }

        if(getIntent().hasExtra("email"))
        {
            welcomeText = getIntent().getStringExtra("email");
            EmailToBePassed=welcomeText;
            tvWelcome.setText(welcomeText);
        }
        EmailToBePassed=welcomeText;

        btnSubjects.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), SubjectActivity.class);
                startActivity(intent);
            }
        });

        bttnStudentAccountDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),StudentDetailsActivity.class);
                intent.putExtra("email", welcomeText);
                startActivity(intent);

            }
        });

        bttnTakeQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),QuizActivity.class);
                intent.putExtra("quizId",selectedItem);
                intent.putExtra("email",welcomeText);
                startActivity(intent);
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.options_student_teacher_menu, menu);
        return super.onCreateOptionsMenu(menu);
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

    private int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        return sharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
    }

}
