package com.example.stefan.quizapp;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class CreateQuiz extends AppCompatActivity {

    private TextView q1;
    private TextView q1a1;
    private TextView q1a2;
    private TextView q1a3;
    private TextView q1a4;
    private TextView q1ac;

    private TextView q2;
    private TextView q2a1;
    private TextView q2a2;
    private TextView q2a3;
    private TextView q2a4;
    private TextView q2ac;

    private TextView q3;
    private TextView q3a1;
    private TextView q3a2;
    private TextView q3a3;
    private TextView q3a4;
    private TextView q3ac;

    private TextView q4;
    private TextView q4a1;
    private TextView q4a2;
    private TextView q4a3;
    private TextView q4a4;
    private TextView q4ac;

    private TextView q5;
    private TextView q5a1;
    private TextView q5a2;
    private TextView q5a3;
    private TextView q5a4;
    private TextView q5ac;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        final DatabaseContract database=new DatabaseContract(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_quiz);

        Button bttnAddQuiz = findViewById(R.id.buttonAddQuiz);

        q1=findViewById(R.id.tvQ1);
        q1a1=findViewById(R.id.tvQ1A1);
        q1a2=findViewById(R.id.tvQ1A2);
        q1a3=findViewById(R.id.tvQ1A3);
        q1a4=findViewById(R.id.tvQ1A4);
        q1ac=findViewById(R.id.tvQ1AC);

        q2=findViewById(R.id.tvQ2);
        q2a1=findViewById(R.id.tvQ2A1);
        q2a2=findViewById(R.id.tvQ2A2);
        q2a3=findViewById(R.id.tvQ2A3);
        q2a4=findViewById(R.id.tvQ2A4);
        q2ac=findViewById(R.id.tvQ2AC);

        q3=findViewById(R.id.tvQ3);
        q3a1=findViewById(R.id.tvQ3A1);
        q3a2=findViewById(R.id.tvQ3A2);
        q3a3=findViewById(R.id.tvQ3A3);
        q3a4=findViewById(R.id.tvQ3A4);
        q3ac=findViewById(R.id.tvQ3AC);

        q4=findViewById(R.id.tvQ4);
        q4a1=findViewById(R.id.tvQ4A1);
        q4a2=findViewById(R.id.tvQ4A2);
        q4a3=findViewById(R.id.tvQ4A3);
        q4a4=findViewById(R.id.tvQ4A4);
        q4ac=findViewById(R.id.tvQ4AC);

        q5=findViewById(R.id.tvQ5);
        q5a1=findViewById(R.id.tvQ5A1);
        q5a2=findViewById(R.id.tvQ5A2);
        q5a3=findViewById(R.id.tvQ5A3);
        q5a4=findViewById(R.id.tvQ5A4);
        q5ac=findViewById(R.id.tvQ5AC);

        bttnAddQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Question

                String Question1=q1.getText().toString();
                String Question2=q2.getText().toString();
                String Question3=q3.getText().toString();
                String Question4=q4.getText().toString();
                String Question5=q5.getText().toString();

                String []questions={Question1,Question2,Question3,Question4,Question5};
                for(int i=0;i<5;i++)
                {
                    if(questions[i].length()<1)
                    {
                        Toast.makeText(getApplicationContext(),"one of the Question inputs is erronated!",Toast.LENGTH_LONG).show();
                    }
                }

                String question1a1=q1a1.getText().toString();
                String question1a2=q1a2.getText().toString();
                String question1a3=q1a3.getText().toString();
                String question1a4=q1a4.getText().toString();

                String []question1answers={question1a1,question1a2,question1a3,question1a4};
                for(int i=0;i<4;i++)
                {
                    if(question1answers[i].length()<1)
                    {
                        Toast.makeText(getApplicationContext(),"one of the answer inputs is erronated!",Toast.LENGTH_LONG).show();
                    }
                }

                String question2a1=q2a1.getText().toString();
                String question2a2=q2a2.getText().toString();
                String question2a3=q2a3.getText().toString();
                String question2a4=q2a4.getText().toString();

                String []question2answers={question2a1,question2a2,question2a3,question2a4};
                for(int i=0;i<4;i++)
                {
                    if(question2answers[i].length()<1)
                    {
                        Toast.makeText(getApplicationContext(),"one of the answer inputs is erronated!",Toast.LENGTH_LONG).show();
                    }
                }

                String question3a1=q3a1.getText().toString();
                String question3a2=q3a2.getText().toString();
                String question3a3=q3a3.getText().toString();
                String question3a4=q3a4.getText().toString();

                String []question3answers={question3a1,question3a2,question3a3,question3a4};
                for(int i=0;i<4;i++)
                {
                    if(question3answers[i].length()<1)
                    {
                        Toast.makeText(getApplicationContext(),"one of the answer inputs is erronated!",Toast.LENGTH_LONG).show();
                    }
                }

                String question4a1=q4a1.getText().toString();
                String question4a2=q4a2.getText().toString();
                String question4a3=q4a3.getText().toString();
                String question4a4=q4a4.getText().toString();

                String []question4answers={question4a1,question4a2,question4a3,question4a4};
                for(int i=0;i<4;i++)
                {
                    if(question4answers[i].length()<1)
                    {
                        Toast.makeText(getApplicationContext(),"one of the answer inputs is erronated!",Toast.LENGTH_LONG).show();
                    }
                }

                String question5a1=q5a1.getText().toString();
                String question5a2=q5a2.getText().toString();
                String question5a3=q5a3.getText().toString();
                String question5a4=q5a4.getText().toString();

                String []question5answers={question5a1,question5a2,question5a3,question5a4};
                for(int i=1;i<4;i++)
                {
                    if(question5answers[i].length()<1)
                    {
                        Toast.makeText(getApplicationContext(),"one of the answer inputs is erronated!",Toast.LENGTH_LONG).show();
                    }
                }

                String question1ac=q1ac.getText().toString();
                String question2ac=q2ac.getText().toString();
                String question3ac=q3ac.getText().toString();
                String question4ac=q4ac.getText().toString();
                String question5ac=q5ac.getText().toString();
                String []correctAnswers={question1ac,question2ac,question3ac,question4ac,question5ac};
                for(int i=0;i<5;i++)
                {
                    if(correctAnswers[i].length()<1)
                    {
                        Toast.makeText(getApplicationContext(),"one of the answer inputs is erronated!",Toast.LENGTH_LONG).show();
                    }
                }

                if(!(question1ac.equals(question1a1)||question1ac.equals(question1a2)||question1ac.equals(question1a3)||question1ac.equals(question1a4)))
                {
                    Toast.makeText(getApplicationContext(),"Please specify the correct answer for the first question correctly!",Toast.LENGTH_LONG).show();
                }
                else if(!(question2ac.equals(question2a1)||question2ac.equals(question2a2)||question2ac.equals(question2a3)||question2ac.equals(question2a4)))
                {
                    Toast.makeText(getApplicationContext(),"Please specify the correct answer for the second question correctly!",Toast.LENGTH_LONG).show();
                }
                else if(!(question3ac.equals(question3a1)||question3ac.equals(question3a2)||question3ac.equals(question3a3)||question3ac.equals(question3a4)))
                {
                    Toast.makeText(getApplicationContext(),"Please specify the correct answer for the third question correctly!",Toast.LENGTH_LONG).show();
                }
                else if(!(question4ac.equals(question4a1)||question4ac.equals(question4a2)||question4ac.equals(question4a3)||question4ac.equals(question4a4)))
                {
                    Toast.makeText(getApplicationContext(),"Please specify the correct answer for the fourth question correctly!",Toast.LENGTH_LONG).show();
                }
                else if(!(question4ac.equals(question4a1)||question4ac.equals(question4a2)||question4ac.equals(question4a3)||question4ac.equals(question4a4)))
                {
                    Toast.makeText(getApplicationContext(),"Please specify the correct answer for the fifth question correctly!",Toast.LENGTH_LONG).show();
                }
                else
                {
                    database.insertQuiz("Quiz");
                    Cursor cursor=database.getQuizId();
                    Integer QuizID=cursor.getCount();
                    database.insertQuestion(Question1,question1a1,question1a2,question1a3,question1a4,question1ac,QuizID);
                    database.insertQuestion(Question2,question2a1,question2a2,question2a3,question2a4,question2ac,QuizID);
                    database.insertQuestion(Question3,question3a1,question3a2,question3a3,question3a4,question3ac,QuizID);
                    database.insertQuestion(Question4,question4a1,question4a2,question4a3,question4a4,question4ac,QuizID);
                    database.insertQuestion(Question5,question5a1,question5a2,question5a3,question5a4,question5ac,QuizID);
                    database.close();
                    Toast.makeText(getApplicationContext(),"Quiz created succesfully!",Toast.LENGTH_LONG).show();
                    finish();
                }
            }
        });
    }

}
