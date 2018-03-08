package com.example.pc.topquiz.modele;

import java.util.List;

/**
 * Created by pc on 02/03/2018.
 */

public class Question {
    private String mQuestion;
    private List<String> mChoiceList;
    private int mAnswerIndex;

    public Question(String question, List<String> choiceList, int answerIndex) {
       this.setQuestion(question);
        this.setChoiceList(choiceList);
        this.setAnswerIndex (answerIndex);

    }

    public String getQuestion() {
        return mQuestion;
    }

    public List<String> getChoiceList() {
        return mChoiceList;
    }

    public int getAnswerIndex() {
        return mAnswerIndex;
    }

    public void setQuestion(String question) {
        mQuestion = question;
    }

    public void setAnswerIndex(int answerIndex) {


    if (answerIndex < 0 || answerIndex >= mChoiceList.size()) {
        throw new IllegalArgumentException("Answer index is out of bound");}
        mAnswerIndex = answerIndex;
    }

    public void setChoiceList(List<String> choiceList) {
        mChoiceList = choiceList;
    }
    @Override
    public String toString() {
        return "Question{" +
                "mQuestion='" + mQuestion + '\'' +
                ", mChoiceList=" + mChoiceList +
                ", mAnswerIndex=" + mAnswerIndex +
                '}';
    }
}
