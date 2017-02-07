package com.bignerdranch.android.geoquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class resume extends AppCompatActivity {

    private TextView mFinalScore;
    private TextView mMaxPosPoeng;
    private int score;
    private Button tryAgain;

    //Tar imot poeng fra QuizActivity
    public static Intent newIntent(Context packageContext, int score) {
        Intent i = new Intent(packageContext, resume.class);
        return i;
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resume);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        int sum = getIntent().getExtras().getInt("score");
        int MaxScore = getIntent().getExtras().getInt("MaxScore");

        mMaxPosPoeng = (TextView) findViewById(R.id.view_MaxScore);
        mFinalScore = (TextView) findViewById(R.id.view_score);

        String poengSum = Integer.toString(sum);
        String MaxPosPoeng = Integer.toString(MaxScore);

        mMaxPosPoeng.setText(MaxPosPoeng);
        mFinalScore.setText(poengSum);

        tryAgain = (Button)findViewById(R.id.Try_again);
        tryAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start quizActivity again
                Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                finish();
                startActivity(intent);

            }
        });
    }

}
