package com.example.stefan.quizapp;

import java.util.ArrayList;

public class Question {
    private Integer id;
    private String text;
    private ArrayList<String> answers;
    private Integer correct_answer;

    public Question(Integer id, String text, ArrayList<String> answers, Integer correct_answer) {
        this.id = id;
        this.text = text;
        this.answers = answers;
        this.correct_answer = correct_answer;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public ArrayList<String> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<String> answers) {
        this.answers = answers;
    }

    public Integer getCorrect_answer() {
        return correct_answer;
    }

    public void setCorrect_answer(Integer correct_answer) {
        this.correct_answer = correct_answer;
    }

    public String getAnswer(Integer x)
    {
        return answers.get(x);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", text='" + text + '\'' +
                ", answers=" + answers +
                ", correct_answer=" + correct_answer +
                '}';
    }
}
