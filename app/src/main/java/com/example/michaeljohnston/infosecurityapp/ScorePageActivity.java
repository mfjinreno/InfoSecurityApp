package com.example.michaeljohnston.infosecurityapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class ScorePageActivity extends AppCompatActivity {
    public static int score = 0;
    public static LinearLayout  scoreContainer;
    private static Context mContext;
    public static ArrayList<SpamHam> incorrectItems = new ArrayList<SpamHam>();


    public static void putScreen(){
        TextView text = (TextView) scoreContainer.findViewById(R.id.list_header_continue);
        String scoreString = String.valueOf(score);
        text.setText(scoreString);
        scoreContainer.removeAllViews();
        scoreContainer.addView(text);
    }

    public static void exitClick(View c){
        Intent intent = new Intent(mContext, ChooseGameActivity.class);
        mContext.startActivity(intent);
    }

    public static void continueClick(View v){
        Intent intent = new Intent(mContext, SpamOrHamGame.class);
        mContext.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);
        score = SpamOrHamGame.score;
        incorrectItems=SpamOrHamGame.incorrectItems;
        System.out.println("Score"+ score);
        scoreContainer = (LinearLayout) findViewById(R.id.linearLayout1);
        mContext = this;
        putScreen();

    }
}
