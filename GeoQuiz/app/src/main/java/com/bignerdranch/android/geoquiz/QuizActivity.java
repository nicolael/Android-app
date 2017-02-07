package com.bignerdranch.android.geoquiz;

import android.content.Intent;
import android.os.Bundle;
//import android.provider.Settings;
import android.util.Log;
//import android.support.design.widget.FloatingActionButton;
//import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
//import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//Controller layer
public class QuizActivity extends AppCompatActivity {
    private static final String TAG = "QuizActivity";
    private static final String KEY_INDEX = "index";

    private Button mTrueButton;
    private Button mFalseButton;
    //private Button mNextButton;
    private Button mCheatButton;

    private int poengs = 0;
    private TextView mQuestionTextView;
    private TextView mPoengView;
    private ImageView mImageView;

    private boolean visible = true;
    //should be stored somewhere else
    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_turkey,false,"istanbul"),
            new Question(R.string.question_oceans, true,"oceans"),
            new Question(R.string.question_mideast,false,"suez"),
            new Question(R.string.question_africa,false,"nile"),
            new Question(R.string.question_americas, true,"amazonas"),
            new Question(R.string.question_asia,true,"baikal"),
    };
    private int mCurrentIndex = 0;


    private void updateQuestion() {

        if(mCurrentIndex == mQuestionBank.length ){
            mCurrentIndex = 0;
            Intent intent = new Intent(getApplicationContext(), resume.class);
            intent.putExtra("score", poengs);
            intent.putExtra("MaxScore",mQuestionBank.length);
            finish();
            startActivity(intent);
        }else {
            int question = mQuestionBank[mCurrentIndex].getTextResId();
            String img = mQuestionBank[mCurrentIndex].getImgResId();
            int resID = getResources().getIdentifier(img, "drawable", getPackageName());
            mQuestionTextView.setText(question);//setter bildet
            mImageView.setImageResource(resID);
        }

    }
    private void checkAnswer(boolean userPressedTrue) {

            if(mCurrentIndex == mQuestionBank.length ){
                mCurrentIndex = 0;
            }

            boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

            int messageResId = 0;

            if (userPressedTrue == answerIsTrue) {
                messageResId = R.string.correct_toast;
                poengs++;
                String poengSum = Integer.toString(poengs);
                mPoengView.setText(poengSum); //viser poengsummen
            } else {
                messageResId = R.string.incorrect_toast;
            }
            Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                    .show();

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        mCheatButton = (Button)findViewById(R.id.cheat_button);
        mCheatButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Start CheatActivity
                boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();
                Intent i = CheatActivity.newIntent(QuizActivity.this, answerIsTrue);
                startActivity(i);

            }
        });

        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mPoengView = (TextView) findViewById(R.id.poeng_view);
        mImageView = (ImageView) findViewById(R.id.image);

        //makes image Clickable
        /*
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });
        */
        //updateQuestion();

        mTrueButton = (Button) findViewById(R.id.true_button);
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
                if(mCurrentIndex < mQuestionBank.length){
                    mCurrentIndex++;
                    updateQuestion();
                }
            }

        });

        mFalseButton = (Button) findViewById(R.id.false_button);
        mFalseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkAnswer(false);
                if(mCurrentIndex < mQuestionBank.length){
                    mCurrentIndex++;
                    updateQuestion();
                }
            }
        });

        /*
        mNextButton = (Button) findViewById(R.id.next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                mCurrentIndex++;
                updateQuestion();
            }
        });
        */
        if (savedInstanceState != null) {
            mCurrentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }
        updateQuestion();

        /*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, mCurrentIndex);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d(TAG, "onPause() called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
