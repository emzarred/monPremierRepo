package com.example.pc.topquiz.controller;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pc.topquiz.R;
import com.example.pc.topquiz.modele.Question;
import com.example.pc.topquiz.modele.QuestionBank;
import java.util.Arrays;
import java.util.prefs.Preferences;


public class GameActivity extends AppCompatActivity implements View.OnClickListener {
    private MediaPlayer mPlayer = null;
private TextView ques;
    private Button A1,A2,A3,A4;
    private QuestionBank dev;
    private Question mPosQues;
    public int mScore,mNumQues;
    public static final String BUNDLE_EXTRA_SCORE="BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";
    private boolean mEnableTouchEvents;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        System.out.println("GameActivity::onCreate()");


        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumQues = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {
            mScore = 0;
           mNumQues = 4;
        }

        mEnableTouchEvents = true;
        dev = this.generateQuestions();
        ques = (TextView) findViewById(R.id.ques);
        A1 = (Button) findViewById(R.id.A1);
        A2 = (Button) findViewById(R.id.A2);
        A3 = (Button) findViewById(R.id.A3);
        A4 = (Button) findViewById(R.id.A4);
        A1.setTag(0);
        A2.setTag(1);
        A3.setTag(2);
        A4.setTag(3);

        A1.setOnClickListener(this);
        A2.setOnClickListener(this);
        A3.setOnClickListener(this);
        A4.setOnClickListener(this);

        mPosQues = dev.getQuestion();
        this.displayQuestion(mPosQues);



    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumQues);

        super.onSaveInstanceState(outState);
    }
    private void playSound(int resId) {
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
        mPlayer = MediaPlayer.create(this, resId);
        mPlayer.start();
    }
    @Override
    public void onClick(View v) {
        playSound(R.raw.up);
        int responseIndex = (int) v.getTag();


        if (responseIndex == mPosQues.getAnswerIndex()) {

            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {

            Toast.makeText(this, "Wrong answer!", Toast.LENGTH_SHORT).show();
        }

        mEnableTouchEvents = false;

        boolean b = new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;


                if (--mNumQues == 0) {

                    endGame();
                } else {
                    mPosQues = dev.getQuestion();
                    displayQuestion(mPosQues);
                }
            }
        }, 2000);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }

    private void endGame() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Great Work!")
                .setMessage("Your score is " + mScore)
                .setNegativeButton("Quit Game", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog,int which){
                        onDestroy();
                        finish();
                    }
                } )
                .setPositiveButton("Try Again", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // End the activity
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .setCancelable(false)
                .create()

                .show();
    };


    private void displayQuestion(final Question question) {
        ques.setText(question.getQuestion());
        A1.setText(question.getChoiceList().get(0));
        A2.setText(question.getChoiceList().get(1));
        A3.setText(question.getChoiceList().get(2));
        A4.setText(question.getChoiceList().get(3));
    }

    private QuestionBank generateQuestions() {


        Question question1 = new Question("How many legs spider has?",
                Arrays.asList("6",
                        "4",
                        "8",
                        "10"),
                2);

        Question question2 = new Question("Combien de coeurs a l'octopus?",
                Arrays.asList("1",
                        "2",
                        "3",
                        "4"),
                2);

        Question question3 = new Question("In Pokemon what'is the evolution of Meouth ?",
                Arrays.asList("Charizard",
                        "Pikachu",
                        "Vinosor",
                        "Persian"),
                3);
        Question question4= new Question("How many partes pirate of Caribian has?",
                Arrays.asList("5",
                        "8",
                        "3",
                        "2"),
                0);

        return new QuestionBank(Arrays.asList(question1,
                question2,
                question3,
                question4));

    }


    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("GameActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("GameActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("GameActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("GameActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("GameActivity::onDestroy()");
    }
}


