package com.example.stefan.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class SelectActivity extends AppCompatActivity {

    private Button btnBack;
    private RadioGroup radioSelectGroup;
    private RadioButton radioType;
    private Button btnNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        btnBack=findViewById(R.id.btnSelectBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Went back to main screen from user selection", Toast.LENGTH_SHORT).show();
                finish();
            }

        });

        addListener();

    }

    public void addListener(){
        radioSelectGroup = findViewById(R.id.rgSelect);
        btnNext = findViewById(R.id.btnSelectNext);

        btnNext.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(!(radioSelectGroup.getCheckedRadioButtonId()==-1)){
                    int selected = radioSelectGroup.getCheckedRadioButtonId();
                    radioType = findViewById(selected);
                    Toast.makeText(SelectActivity.this, radioType.getText()+" selected", Toast.LENGTH_SHORT).show();
                    if(radioType.getText().equals("Student"))
                    {
                        Intent intent = new Intent(getApplicationContext(), LoginActivityStudent.class);
                        startActivityForResult(intent, RESULT_CANCELED);
                    }
                    else if(radioType.getText().equals("Teacher"))
                    {
                        Intent intent = new Intent(getApplicationContext(), LoginActivityProfessor.class);
                        startActivityForResult(intent, RESULT_CANCELED);
                    }
                }
                else
                    Toast.makeText(SelectActivity.this, "ERROR: no option selected!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    /*public void navigateToLogin(View view){
        Intent intent = new Intent(getApplicationContext(), LoginActivityStudent.class);
        startActivityForResult(intent, RESULT_CANCELED);
    }*/

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==RESULT_CANCELED && resultCode == RESULT_CANCELED)
            finish();
    }

    private int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        return sharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
    }

}
