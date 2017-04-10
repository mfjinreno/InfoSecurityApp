package com.example.michaeljohnston.infosecurityapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class ScoreScreenPhishActivity extends AppCompatActivity {
    public static int score = 0;
    public static LinearLayout scoreContainer;
    public static Button button;
    private static Context mContext;
    public static ArrayList<SpamHam> incorrectItems = new ArrayList<SpamHam>();


    public static void putScreen(boolean isEmpty){
        TextView text = (TextView) scoreContainer.findViewById(R.id.list_header_continue);
        if (isEmpty){
            button.setVisibility(View.INVISIBLE);
            String scoreString = String.valueOf("You got everything correct, Congrats!");
            text.setText(scoreString);

        }else {
            String scoreString = String.valueOf(score);
            text.setText(scoreString);
        }
        scoreContainer.removeAllViews();
        scoreContainer.addView(text);
    }

    public static void exitClick(View c){
        Intent intent = new Intent(mContext, ChooseGameActivity.class);
        mContext.startActivity(intent);
    }

    public static void continueClick(View v){
        Intent intent = new Intent(mContext, SpamOrHamGame.class);
        intent.putExtra("incorrectItemsSerializeable", (Serializable) incorrectItems);

        mContext.startActivity(intent);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_screen_phish);
        score = SpamOrHamGame.score;

        Bundle bundle = getIntent().getExtras();
        incorrectItems= (ArrayList<SpamHam>) bundle.getSerializable("incorrectItemsSerializeable");
        boolean isEmpty = incorrectItems.isEmpty();
        System.out.println("Score"+ score);
        scoreContainer = (LinearLayout) findViewById(R.id.linearLayout1);
        button = (Button) findViewById(R.id.continueButton);
        mContext = this;
        putScreen(isEmpty);
    }
}
