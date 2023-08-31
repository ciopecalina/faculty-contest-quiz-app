package com.example.stefan.quizapp;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    private String TAG = QuestionActivity.class.getSimpleName();
    private ListView lv;

    private ProgressBar progressBar;
    Handler handler = new Handler();

    ArrayList<Question> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        progressBar =findViewById(R.id.progressBarQ);

        final ActionBar actionBar = getSupportActionBar();

        if (getColor() != getResources().getColor(R.color.colorPrimary)){
            actionBar.setBackgroundDrawable(new ColorDrawable(getColor()));
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                getWindow().setStatusBarColor(getColor());
            }
        }

        Toast.makeText(getApplicationContext(),"Json Data is downloading",Toast.LENGTH_LONG).show();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if(progressBar!=null)
                    progressBar.setVisibility(View.INVISIBLE);

                questionList = new ArrayList<>();
                lv = findViewById(R.id.lvQuestions);

                new GetSubject().execute();
            }
        }, 2500);

    }

    private class GetSubject extends AsyncTask<Void, Void, Void>
    {
        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0)
        {
            HttpHandler sh = new HttpHandler();
            // Making a request to url and getting response
            String url = "https://pastebin.com/raw/d9hVdCnx/";
            String jsonStr = sh.makeServiceCall(url);

            Log.e(TAG, "Response from url: " + jsonStr);
            if (jsonStr != null)
            {

                try {
                    JSONObject jsonObj = new JSONObject(jsonStr);

                    JSONArray questions = jsonObj.getJSONArray("questions");

                    for (int i = 0; i < questions.length(); i++)
                    {
                        JSONObject q = questions.getJSONObject(i);
                        Integer id = q.getInt("id");
                        String text = q.getString("text");

                        ArrayList<String> answers = new ArrayList<String>();
                        JSONArray arr = q.getJSONArray("answers");
                        for(int j = 0; j < arr.length(); j++){
                            answers.add(arr.getString(j));
                        }

                        Integer correct_index = Integer.parseInt(q.getString("correct_answer"));

                        Question question = new Question(id, text, answers, correct_index);


                        questionList.add(question);
                    }
                } catch (final JSONException e)
                {
                    Log.e(TAG, "Json parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable()
                    {
                        @Override
                        public void run()
                        {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG).show();
                        }
                    });

                }

            } else
            {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        Toast.makeText(getApplicationContext(),
                                "Couldn't get json from server. Check LogCat for possible errors!",
                                Toast.LENGTH_LONG).show();
                    }
                });
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);
            CustomAdapter adapter = new CustomAdapter(getApplicationContext(),questionList);
            lv.setAdapter(adapter);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.options_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.go_back)
            finish();
        return true;
    }

    private int getColor(){
        SharedPreferences sharedPreferences = getSharedPreferences("ActionBar", MODE_PRIVATE);
        return sharedPreferences.getInt("color", getResources().getColor(R.color.colorPrimary));
    }
}
