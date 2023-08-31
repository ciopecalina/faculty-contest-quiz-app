package com.example.stefan.quizapp;

public class Question2 {
    private String questionId;
    private String questionText;
    private String questionA1;
    private String questionA2;
    private String questionA3;
    private String questionA4;
    private String questionCorrect;

    public Question2(String questionId, String questionText, String questionA1, String questionA2, String questionA3, String questionA4, String questionCorrect) {
        this.questionId = questionId;
        this.questionText = questionText;
        this.questionA1 = questionA1;
        this.questionA2 = questionA2;
        this.questionA3 = questionA3;
        this.questionA4 = questionA4;
        this.questionCorrect = questionCorrect;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionA1() {
        return questionA1;
    }

    public void setQuestionA1(String questionA1) {
        this.questionA1 = questionA1;
    }

    public String getQuestionA2() {
        return questionA2;
    }

    public void setQuestionA2(String questionA2) {
        this.questionA2 = questionA2;
    }

    public String getQuestionA3() {
        return questionA3;
    }

    public void setQuestionA3(String questionA3) {
        this.questionA3 = questionA3;
    }

    public String getQuestionA4() {
        return questionA4;
    }

    public void setQuestionA4(String questionA4) {
        this.questionA4 = questionA4;
    }

    public String getQuestionCorrect() {
        return questionCorrect;
    }

    public void setQuestionCorrect(String questionCorrect) {
        this.questionCorrect = questionCorrect;
    }

    @Override
    public String toString() {
        return "Question2{" +
                "questionId='" + questionId + '\'' +
                ", questionText='" + questionText + '\'' +
                ", questionA1='" + questionA1 + '\'' +
                ", questionA2='" + questionA2 + '\'' +
                ", questionA3='" + questionA3 + '\'' +
                ", questionA4='" + questionA4 + '\'' +
                '}';
    }

}
