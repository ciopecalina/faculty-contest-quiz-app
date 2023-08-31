package com.example.stefan.quizapp;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;


public class QuizActivity extends AppCompatActivity {

    private TextView q1;
    private TextView q2;
    private TextView q3;
    private TextView q4;
    private TextView q5;

    private RadioGroup rgQ1;
    private RadioGroup rgQ2;
    private RadioGroup rgQ3;
    private RadioGroup rgQ4;
    private RadioGroup rgQ5;

    private RadioButton rbQ1A1;
    private RadioButton rbQ1A2;
    private RadioButton rbQ1A3;
    private RadioButton rbQ1A4;

    private RadioButton rbQ2A1;
    private RadioButton rbQ2A2;
    private RadioButton rbQ2A3;
    private RadioButton rbQ2A4;

    private RadioButton rbQ3A1;
    private RadioButton rbQ3A2;
    private RadioButton rbQ3A3;
    private RadioButton rbQ3A4;

    private RadioButton rbQ4A1;
    private RadioButton rbQ4A2;
    private RadioButton rbQ4A3;
    private RadioButton rbQ4A4;

    private RadioButton rbQ5A1;
    private RadioButton rbQ5A2;
    private RadioButton rbQ5A3;
    private RadioButton rbQ5A4;

    private RadioButton answer1;
    private RadioButton answer2;
    private RadioButton answer3;
    private RadioButton answer4;
    private RadioButton answer5;

    private Button btnSubmit;

    private String idQuiz;

    private String email;

    private Integer Score;

    private TextView tvQ;

    List<String> questions = new ArrayList<String>();
    List<String> ans1 = new ArrayList<>();
    List<String> ans2 = new ArrayList<String>();
    List<String> ans3 = new ArrayList<String>();
    List<String> ans4 = new ArrayList<String>();
    List<String> ansC = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        q1 = findViewById(R.id.tQ1);
        q2 = findViewById(R.id.tQ2);
        q3 = findViewById(R.id.tQ3);
        q4 = findViewById(R.id.tQ4);
        q5 = findViewById(R.id.tQ5);

        rgQ1 = findViewById(R.id.rgQ1);
        rgQ2 = findViewById(R.id.rgQ2);
        rgQ3 = findViewById(R.id.rgQ3);
        rgQ4 = findViewById(R.id.rgQ4);
        rgQ5 = findViewById(R.id.rgQ5);

        rbQ1A1 = findViewById(R.id.rbQ1A1);
        rbQ1A2 = findViewById(R.id.rbQ1A2);
        rbQ1A3 = findViewById(R.id.rbQ1A3);
        rbQ1A4 = findViewById(R.id.rbQ1A4);

        rbQ2A1 = findViewById(R.id.rbQ2A1);
        rbQ2A2 = findViewById(R.id.rbQ2A2);
        rbQ2A3 = findViewById(R.id.rbQ2A3);
        rbQ2A4 = findViewById(R.id.rbQ2A4);

        rbQ3A1 = findViewById(R.id.rbQ3A1);
        rbQ3A2 = findViewById(R.id.rbQ3A2);
        rbQ3A3 = findViewById(R.id.rbQ3A3);
        rbQ3A4 = findViewById(R.id.rbQ3A4);

        rbQ4A1 = findViewById(R.id.rbQ4A1);
        rbQ4A2 = findViewById(R.id.rbQ4A2);
        rbQ4A3 = findViewById(R.id.rbQ4A3);
        rbQ4A4 = findViewById(R.id.rbQ4A4);

        rbQ5A1 = findViewById(R.id.rbQ5A1);
        rbQ5A2 = findViewById(R.id.rbQ5A2);
        rbQ5A3 = findViewById(R.id.rbQ5A3);
        rbQ5A4 = findViewById(R.id.rbQ5A4);

        btnSubmit = findViewById(R.id.buttonSubmitQuiz);

        tvQ=findViewById(R.id.tvEmailQ);

        if(getIntent().hasExtra("quizId"))
        {
            idQuiz = getIntent().getStringExtra("quizId");
        }

        if(getIntent().hasExtra("email"))
        {
            email = getIntent().getStringExtra("email");
            tvQ.setText(email);
        }

        DatabaseContract db=new DatabaseContract(getApplicationContext());
        Cursor cursor=db.getQAByQuizId(Integer.parseInt(idQuiz));

        //----------------------------------------------

        cursor.moveToFirst();

        while(!cursor.isAfterLast())
        {
            questions.add(cursor.getString(cursor.getColumnIndex("questionText")));
            ans1.add(cursor.getString(cursor.getColumnIndex("questionA1")));
            ans2.add(cursor.getString(cursor.getColumnIndex("questionA2")));
            ans3.add(cursor.getString(cursor.getColumnIndex("questionA3")));
            ans4.add(cursor.getString(cursor.getColumnIndex("questionA4")));
            ansC.add(cursor.getString(cursor.getColumnIndex("questionAC")));
            cursor.moveToNext();
        }
        if(!cursor.isClosed())
        {
            cursor.close();
        }

        q1.setText(questions.get(0));
        q2.setText(questions.get(1));
        q3.setText(questions.get(2));
        q4.setText(questions.get(3));
        q5.setText(questions.get(4));

        rbQ1A1.setText(ans1.get(0));
        rbQ1A2.setText(ans2.get(0));
        rbQ1A3.setText(ans3.get(0));
        rbQ1A4.setText(ans4.get(0));

        rbQ2A1.setText(ans1.get(1));
        rbQ2A2.setText(ans2.get(1));
        rbQ2A3.setText(ans3.get(1));
        rbQ2A4.setText(ans4.get(1));

        rbQ3A1.setText(ans1.get(2));
        rbQ3A2.setText(ans2.get(2));
        rbQ3A3.setText(ans3.get(2));
        rbQ3A4.setText(ans4.get(2));

        rbQ4A1.setText(ans1.get(3));
        rbQ4A2.setText(ans2.get(3));
        rbQ4A3.setText(ans3.get(3));
        rbQ4A4.setText(ans4.get(3));

        rbQ5A1.setText(ans1.get(4));
        rbQ5A2.setText(ans2.get(4));
        rbQ5A3.setText(ans3.get(4));
        rbQ5A4.setText(ans4.get(4));

        final String validate1=ansC.get(0);
        final String validate2=ansC.get(1);
        final String validate3=ansC.get(2);
        final String validate4=ansC.get(3);
        final String validate5=ansC.get(4);


        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(rgQ1.getCheckedRadioButtonId()==-1)&&!(rgQ2.getCheckedRadioButtonId()==-1)&&!(rgQ3.getCheckedRadioButtonId()==-1)&&!(rgQ4.getCheckedRadioButtonId()==-1)&&!(rgQ5.getCheckedRadioButtonId()==-1)){

                    Integer Grade=0;


                    int selected1 = rgQ1.getCheckedRadioButtonId();
                    answer1 = findViewById(selected1);

                    if(answer1.getText().equals(validate1)) Grade++;

                    int selected2 = rgQ2.getCheckedRadioButtonId();
                    answer2 = findViewById(selected2);

                    if(answer2.getText().equals(validate2)) Grade++;

                    int selected3 = rgQ3.getCheckedRadioButtonId();
                    answer3 = findViewById(selected3);

                    if(answer3.getText().equals(validate3)) Grade++;

                    int selected4 = rgQ4.getCheckedRadioButtonId();
                    answer4 = findViewById(selected4);

                    if(answer4.getText().equals(validate4)) Grade++;

                    int selected5 = rgQ5.getCheckedRadioButtonId();
                    answer5 = findViewById(selected5);

                    if(answer5.getText().equals(validate5)) Grade++;

                    Score=Grade;


                    if(tvQ.getText().toString().length()>0)
                    {
                        DatabaseContract db=new DatabaseContract(getApplicationContext());
                        Toast.makeText(getApplicationContext(),"Grade added into the database!",Toast.LENGTH_LONG);
                        db.updateStudentGrade(tvQ.getText().toString(),Grade);
                    }
                    Toast.makeText(getApplicationContext(),"Your result is " + Score +".",Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(getApplicationContext(),StudentAccount.class);
                    intent.putExtra("grade",Score);
                    intent.putExtra("email",StudentAccount.EmailToBePassed);
                    startActivity(intent);
                    finish();
                }
                else
                {
                    Toast.makeText(QuizActivity.this, "All questions must have an answer selected!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
