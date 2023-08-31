package com.example.stefan.quizapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Build;
import android.provider.Settings;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private MediaPlayer musicPlayer;
    private Switch sw;
    private SeekBar seekVolume;
    private ToggleButton darkMode;
    int maxVolume = 100;

    ConstraintLayout mLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLayout = findViewById(R.id.myScreen);

        darkMode = findViewById(R.id.toggleDarkMode);
        sw = findViewById(R.id.switchMain);
        seekVolume = findViewById(R.id.seekVolume);
        seekVolume.setMax(99);
        seekVolume.setProgress(50);

        DatabaseContract database=new DatabaseContract(getApplicationContext());
        Cursor cursor1=database.getStudentCount();
        Cursor cursor2=database.getTeacherCount();
        if((cursor1.getCount()<1)&&(cursor2.getCount()<1))
        {
            database.insertStudent("s_admin1","s_admin1@mail.com","10/10/2000","123456","CSIE");
            database.insertStudent("s_admin2","s_admin2@mail.com","10/10/2000","123456","CSIE");
            database.insertTeacher("t_admin1","t_admin1@mail.com","10/10/2000","123456","CSIE");
            database.insertTeacher("t_admin2","t_admin2@mail.com","10/10/2000","123456","CSIE");
        }
        if (!cursor1.isClosed())
        {
            cursor1.close();
        }
        if (!cursor2.isClosed())
        {
            cursor2.close();
        }



        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        seekVolume.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

                float logVol = (float)(Math.log(maxVolume-seekVolume.getProgress())/Math.log(maxVolume));
                if(musicPlayer!=null&&musicPlayer.isPlaying()){
                    musicPlayer.setVolume(1-logVol, 1-logVol);
                }
            }
        });

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw.isChecked()){
                    Toast.makeText(getApplicationContext(), "Merry christmas!", Toast.LENGTH_SHORT).show();
                    musicPlayer = MediaPlayer.create(getApplicationContext(), R.raw.christmas);
                    musicPlayer.start();
                    float logVol = (float)(Math.log(maxVolume-seekVolume.getProgress())/Math.log(maxVolume));
                    musicPlayer.setVolume(1-logVol, 1-logVol);
                }
                else
                {
                    Toast.makeText(getApplicationContext(), "Carols are off", Toast.LENGTH_SHORT).show();
                    musicPlayer.release();
                    musicPlayer=null;
                }
            }
        });

        darkMode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(darkMode.getText().toString().equals("On")){
                    mLayout.setBackgroundColor(0xFF00FF80);
                }
                else if(darkMode.getText().toString().equals("Off")){
                    mLayout.setBackgroundColor(Color.WHITE);
                }
                Toast.makeText(getApplicationContext(), "Lemonade: " + darkMode.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });

        final TextView textView = findViewById(R.id.tvUsers);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference logRef = dbRef.child("logs");
        logRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                long nbUsers = dataSnapshot.getChildrenCount();
                textView.setText(String.format(getString(R.string.wwusers),nbUsers));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        String uniqueId=Settings.Secure.getString(MainActivity.this.getContentResolver(),Settings.Secure.ANDROID_ID);
        DatabaseReference phoneRef=logRef.child(uniqueId);
        phoneRef.setValue(new Date());

    }

    @Override
    public void onBackPressed() {
        //something
    }

    public void navigateToRating(View view){
        Intent intent = new Intent(getApplicationContext(), RateActivity.class);
        startActivity(intent);
    }

    public void navigateToInfo(View view){
        Intent intent = new Intent(getApplicationContext(), InfoActivity.class);
        startActivity(intent);
    }

    public void navigateToContact(View view){
        Intent intent = new Intent(getApplicationContext(), ContactActivity.class);
        startActivity(intent);
    }

    public void navigateToSelection(View view){
        Intent intent = new Intent(getApplicationContext(), SelectActivity.class);
        startActivity(intent);
    }

    public void navigateToRegister(View view){
        Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
        startActivity(intent);
    }

    private int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        return sharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
    }
}
