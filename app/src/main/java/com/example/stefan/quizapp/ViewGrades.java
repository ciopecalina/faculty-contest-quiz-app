package com.example.stefan.quizapp;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class ViewGrades extends AppCompatActivity {

    private Button bttnSave;
    private String[] tokens;
    private String gradeStringArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_grades);

        bttnSave=findViewById(R.id.bttnSaveGrades);
        DatabaseContract db=new DatabaseContract(getApplicationContext());
        Cursor cursor=db.getGrades(StudentAccount.EmailToBePassed);
        cursor.moveToFirst();
        gradeStringArray=cursor.getString(cursor.getColumnIndex(Student.COLUMN_GRADES));
        if (!cursor.isClosed())
        {
            cursor.close();
        }

        tokens = gradeStringArray.split(",");
        int[] gradeNumberArray = new int[tokens.length-1];

        for(int i=1; i<tokens.length;i++){
            gradeNumberArray[i-1]=Integer.parseInt(tokens[i]);
        }

        int[] grades = gradeNumberArray;

        GraphView graph = findViewById(R.id.graph);

        DataPoint[] points = new DataPoint[grades.length];
        for(int i=0;i<grades.length;i++){
            points[i]= new DataPoint(i,grades[i]);
        }

        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(points);
        graph.addSeries(series);

        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
            @Override
            public int get(DataPoint data) {
                return Color.rgb((int) data.getX()*255/4, (int) Math.abs(data.getY()*255/6), 127);
            }
        });

        series.setSpacing(10);

        series.setDrawValuesOnTop(true);
        series.setValuesOnTopColor(Color.rgb(80, 0, 255));
        series.setValuesOnTopSize(50);



        bttnSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String filename = "myGrades.txt";
                String[] numbers = tokens;
                FileOutputStream outputStream;

                try
                {
                    outputStream = openFileOutput(filename, Context.MODE_APPEND);
                    for ( String s : numbers)
                    {
                        outputStream.write(s.getBytes());

                    }
                    outputStream.close();
                    Toast.makeText(getApplicationContext(),"Your file has been downloaded",Toast.LENGTH_LONG).show();
                } catch (Exception e)
                {
                    Toast.makeText(getApplicationContext(),"Error downloading file",Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                    Log.e("Exception", "File write failed: " + e.toString());
                }

                writeToFile(gradeStringArray);

            }
        });


    }

    public void writeToFile(String data)
    {
        try {

            FileOutputStream fou = openFileOutput("Grades.txt", MODE_APPEND);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(fou);
            outputStreamWriter.write(data);
            Toast.makeText(getApplicationContext(),"Download Succesfull!",Toast.LENGTH_LONG).show();
            outputStreamWriter.close();
        }
        catch (IOException e)
        {
            Log.e("Exception", "File write failed: " + e.toString());
            Toast.makeText(getApplicationContext(),"Download unsuccesfull!",Toast.LENGTH_LONG).show();
        }

    }

}
