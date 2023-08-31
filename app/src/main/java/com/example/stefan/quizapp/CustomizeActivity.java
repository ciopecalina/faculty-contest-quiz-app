package com.example.stefan.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;



public class CustomizeActivity extends AppCompatActivity {

    private Button bttnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customize);

        Spinner colorSpinner = findViewById(R.id.spinnerColor);
        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        bttnBack=findViewById(R.id.bttnCustomizeBack);

        List<String> colors = new ArrayList<String>();
        colors.add("Default");
        colors.add("Blue");
        colors.add("Chartreuse");
        colors.add("Spring");
        colors.add("Azure");
        colors.add("Violet");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, colors);
        colorSpinner.setAdapter(dataAdapter);
        colorSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                if(!selectedItem.equals("")){
                    if(selectedItem.equals("Default")){
                        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.colorPrimary)));
                        storeColor(getResources().getColor(R.color.colorPrimary));
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            getWindow().setStatusBarColor(getColor());
                        }
                    }
                    else if(selectedItem.equals("Blue")){
                        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Blue)));
                        storeColor(getResources().getColor(R.color.Blue));
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            getWindow().setStatusBarColor(getColor());
                        }
                    }
                    else if(selectedItem.equals("Chartreuse")){
                        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Chartreuse)));
                        storeColor(getResources().getColor(R.color.Chartreuse));
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            getWindow().setStatusBarColor(getColor());
                        }
                    }
                    else if(selectedItem.equals("Spring")){
                        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Spring)));
                        storeColor(getResources().getColor(R.color.Spring));
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            getWindow().setStatusBarColor(getColor());
                        }
                    }
                    else if(selectedItem.equals("Azure")){
                        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Azure)));
                        storeColor(getResources().getColor(R.color.Azure));
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            getWindow().setStatusBarColor(getColor());
                        }
                    }
                    else if(selectedItem.equals("Violet")){
                        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.Violet)));
                        storeColor(getResources().getColor(R.color.Violet));
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                            getWindow().setStatusBarColor(getColor());
                        }
                    }

                }
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });

        bttnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void storeColor(int color){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("color", color);
        editor.apply();
    }
    private int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        return sharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
    }


}
