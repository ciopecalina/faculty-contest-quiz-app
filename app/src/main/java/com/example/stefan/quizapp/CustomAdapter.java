package com.example.stefan.quizapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class CustomAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Question> questions;
    private HashMap<String,Integer> itemStates;


    public CustomAdapter(Context mContext, ArrayList<Question> questions)
    {
        this.mContext = mContext;
        this.questions=questions;
        this.itemStates=new HashMap<>();

        for(Question question:questions)
        {
            itemStates.put(question.getText(),question.getCorrect_answer());
        }
    }

    @Override
    public int getCount()
    {
        return questions.size();
    }

    @Override
    public Object getItem(int position)
    {
        return questions.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return questions.get(position).getId();
    }

    @Override
    public View getView(int position, View list_view, ViewGroup parent)
    {
        if(list_view==null)
        {
            list_view=LayoutInflater.from(mContext).inflate(R.layout.list_item_questions,parent,false);
        }
        //so we also need this
        TextView txtId=list_view.findViewById(R.id.id);
        TextView txtTxt=list_view.findViewById(R.id.text);
        TextView ans1=list_view.findViewById(R.id.answer1);
        TextView ans2=list_view.findViewById(R.id.answer2);
        TextView ans3=list_view.findViewById(R.id.answer3);
        TextView correct=list_view.findViewById(R.id.correct_answer);

        final Question question=(Question)getItem(position);

        txtId.setText("The question number "+ question.getId().toString()+" is: ");
        txtTxt.setText(question.getText());
        ans1.setText("A:"+question.getAnswer(0));
        ans2.setText("B:"+question.getAnswer(1));
        ans3.setText("C:"+question.getAnswer(2));

        if(question.getCorrect_answer()==0)
        {
            correct.setText("The correct answer for the question is A");
        }
        else if (question.getCorrect_answer()==1)
        {
            correct.setText("The correct answer for the question is B");
        }
        else if (question.getCorrect_answer()==2)
        {
            correct.setText("The correct answer is c");
        }

        return list_view;
    }
}
