package com.bignerdranch.android.geoquiz;

import android.widget.ImageView;

/**
 * Created by Nicolas on 08.04.2016.
 * Model layer
 */
public class Question {

    private int mTextResId;
    private boolean mAnswerTrue;
    private String mImgResId;
    public Question(int textResId, boolean answerTrue, String imgResId){
        mTextResId = textResId;
        mAnswerTrue = answerTrue;
        mImgResId = imgResId;
    }

    public int getTextResId() {
        return mTextResId;
    }
    public String getImgResId(){
        return mImgResId;
    }

    public void setTextResId(int textResId) {
        mTextResId = textResId;
    }

    public boolean isAnswerTrue() {
        return mAnswerTrue;
    }

    public void setAnswerTrue(boolean answerTrue) {
        mAnswerTrue = answerTrue;
    }

}
