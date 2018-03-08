package com.example.pc.topquiz.modele;

import java.util.Collections;
import java.util.List;

/**
 * Created by pc on 02/03/2018.
 */

public class QuestionBank {

        private List<Question> mQuestionList;
        private int mNextQuestionIndex;

        public QuestionBank(List<Question> questionList) {
            mQuestionList=questionList;
            Collections.shuffle(mQuestionList);
            mNextQuestionIndex=0;
        }

        public Question getQuestion() {
            if (mNextQuestionIndex==mQuestionList.size()){
                mNextQuestionIndex=0;}
                return mQuestionList.get(mNextQuestionIndex++);
            }
            // Loop over the questions and return a new one at each call

}

