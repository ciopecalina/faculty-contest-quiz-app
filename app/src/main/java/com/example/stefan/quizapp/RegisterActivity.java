package com.example.stefan.quizapp;

import android.app.DatePickerDialog;
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
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class RegisterActivity extends AppCompatActivity {

    private Button btnCancel;
    private Button btnRegister;

    private CheckBox cbStudent;
    private CheckBox cbTeacher;

    private TextView tvName;
    private TextView tvEmail;
    private TextView tvPass;
    private TextView tvPassC;
    private TextView tvDate;
    private TextView tvFaculty;
    final Calendar myCalendar = Calendar.getInstance();

    private Spinner spinnerFaculties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        final DatabaseContract database=new DatabaseContract(getApplicationContext());

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        btnCancel=findViewById(R.id.btnCancel);
        btnRegister=findViewById(R.id.btnRegister);

        cbStudent=findViewById(R.id.cbRegisterStudent);
        cbTeacher=findViewById(R.id.cbRegisterTeacher);
        tvDate=findViewById(R.id.inputRegisterDate);

        tvName=findViewById(R.id.inputRegisterName);
        tvEmail=findViewById(R.id.inputRegisterEmail);
        tvPass=findViewById(R.id.inputRegisterPass);
        tvPassC=findViewById(R.id.inputRegisterPassConfirm);
        tvFaculty=findViewById(R.id.tvFaculty);
        spinnerFaculties=findViewById(R.id.spinnerRegisterFaculty);

        List<String> faculties = new ArrayList<String>();
        faculties.add("CSIE");
        faculties.add("REI");
        faculties.add("ETA");
        faculties.add("Marketing");
        faculties.add("Management");
        faculties.add("Finante&Banci");
        faculties.add("Contabilitate");

        //region Spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<>(getApplicationContext(), R.layout.support_simple_spinner_dropdown_item, faculties);
        spinnerFaculties.setAdapter(dataAdapter);
        spinnerFaculties.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id)
            {
                String selectedItem = parent.getItemAtPosition(position).toString();
                tvFaculty.setText(selectedItem);
            } // to close the onItemSelected
            public void onNothingSelected(AdapterView<?> parent)
            {

            }
        });
        //endregion

        //region Datepicker

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth)
            {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String myFormat = "dd/MM/yy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat,Locale.US);
                tvDate.setText(sdf.format(myCalendar.getTime()));
            }
        };

        tvDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    new DatePickerDialog(RegisterActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Registration aborted", Toast.LENGTH_SHORT).show();
                finish();
            }
        });


        btnRegister.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if(cbTeacher.isChecked()&&!cbStudent.isChecked())
                {
                    if(tvName.getText().length() < 2 || tvName.getText().length() > 50)
                    {
                        Toast.makeText(getApplicationContext(), "Invalid name!", Toast.LENGTH_SHORT).show();
                    }
                    else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(tvEmail.getText()).matches())
                    {
                        Toast.makeText(getApplicationContext(), "Invalid email!", Toast.LENGTH_SHORT).show();
                    }
                    else if(tvPass.getText().length() < 5)
                    {
                        Toast.makeText(getApplicationContext(), "Password must be larger than 4 characters!", Toast.LENGTH_SHORT).show();
                    }
                    else if(!tvPassC.getText().toString().equals(tvPass.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(), "Passwords do not match!", Toast.LENGTH_SHORT).show();
                    }
                    else if(tvDate.getText().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Invalid birth date!", Toast.LENGTH_SHORT).show();
                    }
                    else if(tvFaculty.getText().length()==0)
                    {
                        Toast.makeText(getApplicationContext(), "No department selected!", Toast.LENGTH_SHORT).show();
                    }

                    else {
                        String teacherName = tvName.getText().toString();
                        String teacherEmail = tvEmail.getText().toString();
                        String teacherDate = tvDate.getText().toString();
                        String teacherPassword = tvPass.getText().toString();
                        String teacherFaculty = tvFaculty.getText().toString();

                        Cursor cursorTeacherEmail = database.getTeacherLoginCursor(teacherEmail);
                        Cursor cursorTeacherName = database.getTeacherRegisterCursor(teacherName);

                        if (cursorTeacherEmail.getCount() >= 1)
                        {
                            Toast.makeText(getApplicationContext(), "The email " + teacherEmail + " is allready in our database!", Toast.LENGTH_SHORT).show();
                        }
                        else if (cursorTeacherName.getCount() >= 1)
                        {
                            Toast.makeText(getApplicationContext(), "There is allready someone with this username!", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            database.insertTeacher(teacherName, teacherEmail, teacherDate, teacherPassword, teacherFaculty); // Here you hardcode dem values from the DemoDatabase

                            Toast.makeText(getApplicationContext(), "New teacher account created", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
                else if((cbStudent.isChecked()&&!cbTeacher.isChecked())||(cbTeacher.isChecked()&&!cbStudent.isChecked()))
                {
                    if(tvName.getText().length() < 2 || tvName.getText().length() > 50)
                    {
                        Toast.makeText(getApplicationContext(), "Invalid name", Toast.LENGTH_SHORT).show();
                    }
                    else if(!android.util.Patterns.EMAIL_ADDRESS.matcher(tvEmail.getText()).matches())
                    {
                        Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();
                    }
                    else if(tvPass.getText().length() < 5)
                    {
                        Toast.makeText(getApplicationContext(), "Password must be larger than 4 characters", Toast.LENGTH_SHORT).show();
                    }
                    else if(!tvPassC.getText().toString().equals(tvPass.getText().toString()))
                    {
                        Toast.makeText(getApplicationContext(), "Passwords do not match", Toast.LENGTH_SHORT).show();
                    }
                    else if(tvDate.getText().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Invalid birth date!", Toast.LENGTH_SHORT).show();
                    }
                    else if(tvFaculty.getText().length()==0)
                    {
                        Toast.makeText(getApplicationContext(), "No department selected!", Toast.LENGTH_SHORT).show();
                    }
                    else
                    {
                        String studentName=tvName.getText().toString();
                        String studentEmail=tvEmail.getText().toString();
                        String studentDate=tvDate.getText().toString();
                        String studentPassword=tvPass.getText().toString();
                        String studentFaculty=tvFaculty.getText().toString();

                        Cursor cursorEmail = database.getStudentLoginCursor(studentEmail);
                        Cursor cursorName=database.getStudentRegisterCursor(studentName);

                        if(cursorEmail.getCount()>=1)
                        {
                            Toast.makeText(getApplicationContext(), "The email "+studentEmail+" is allready in our database!", Toast.LENGTH_SHORT).show();
                        }
                        else if(cursorName.getCount()>=1)
                        {
                            Toast.makeText(getApplicationContext(),"There is allready someone with this username!",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            database.insertStudent(studentName,studentEmail,studentDate,studentPassword,studentFaculty); // Here you hardcode dem values from the DemoDatabase

                            Toast.makeText(getApplicationContext(), "New student account created", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }
                }
                else if (cbStudent.isChecked()&&cbTeacher.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "You can't select more than 1 account option", Toast.LENGTH_SHORT).show();
                }
                else if (!cbStudent.isChecked()&&!cbTeacher.isChecked())
                {
                    Toast.makeText(getApplicationContext(), "Please select 1 account option", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        return sharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
    }


}
