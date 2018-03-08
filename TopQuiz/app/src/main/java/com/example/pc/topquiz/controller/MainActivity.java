package com.example.pc.topquiz.controller;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.example.pc.topquiz.R;
import com.example.pc.topquiz.modele.User;

public class MainActivity extends AppCompatActivity {
    private MediaPlayer mPlayer = null;
    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private User mUser;
    private int score;
    public static final int REQUEST_CODE=44;
    private SharedPreferences mPreferences;


    public static final String PREF_KEY_SCORE = "PREF_KEY_SCORE";
    public static final String PREF_KEY_FIRSTNAME = "PREF_KEY_FIRSTNAME";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        System.out.println("MainActivity::onCreate()");
        mUser=new User();
        mPreferences = getPreferences(MODE_PRIVATE);
        mGreetingText=(TextView) findViewById(R.id.greeting);
        mNameInput=(EditText)findViewById(R.id.name);
    mPlayButton=(Button)findViewById(R.id.play);
    mPlayButton.setEnabled(false);
    mNameInput.addTextChangedListener(new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            mPlayButton.setEnabled(s.toString().length() != 0);

        }

        @Override
        public void afterTextChanged(Editable s) {

        }

    });
       mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String firstname= mNameInput.getText().toString();
                mUser.setFirstname(firstname);
               mPreferences.edit().putString(PREF_KEY_FIRSTNAME, mUser.getFirstname()).apply();

                Intent gameActivity = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivity,REQUEST_CODE);
            }
        });



    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (REQUEST_CODE == requestCode && RESULT_OK == resultCode) {

            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);
            mPreferences.edit().putInt(PREF_KEY_SCORE,score).commit();

            greetUser();
    }

}
    private void greetUser() {
        String firstname = mPreferences.getString(PREF_KEY_FIRSTNAME, null);


        if (null != firstname) {
            int score = mPreferences.getInt(PREF_KEY_SCORE, 0);

            String fulltext = "Hey Again, " + firstname
                    + "!\nYour last score was " + score
                    + ", Score better this time?";
            mGreetingText.setText(fulltext);
            mNameInput.setText(firstname);
            mNameInput.setSelection(firstname.length());
            mPlayButton.setEnabled(true);
        }
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
    protected void onStart() {
        super.onStart();
        playSound(R.raw.harry);

       System.out.println("MainActivity::onStart()");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("MainActivity::onResume()");
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlayer.stop();

        System.out.println("MainActivity::onPause()");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("MainActivity::onStop()");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("MainActivity::onDestroy()");
    }
}
