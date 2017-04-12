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

public class ScorePageActivity extends AppCompatActivity {
    public static int score = 0;
    public static LinearLayout  scoreContainer;
    public static Button button;
    private static Context mContext;
    public static ArrayList<SpamHam> incorrectItems = new ArrayList<>();
    public static String currentType = "";


    public static void putScreen(boolean isEmpty){
        TextView text = (TextView) scoreContainer.findViewById(R.id.list_header_continue);
        if (isEmpty){
            button.setVisibility(View.INVISIBLE);
            String scoreString = String.valueOf("You got everything correct, Congrats!");
            text.setText(scoreString);

        }else {
            int missed = 10-score;
            String scoreString = String.valueOf(score);
            String numberWrong = "Score: " + scoreString;
            text.setText(numberWrong + "\n Looks like you missed " + missed + "! Let's try it again!");
        }
        scoreContainer.removeAllViews();
        scoreContainer.addView(text);
    }

    public static void exitClick(View c){
        Intent intent = new Intent(mContext, ChooseGameActivity.class);
        mContext.startActivity(intent);
    }

    public static void continueClick(View v){
        if (currentType.equals("spam")){
            Intent intent = new Intent(mContext, SpamOrHamGame.class);
            intent.putExtra("incorrectItemsSerializeable", (Serializable) incorrectItems);
            mContext.startActivity(intent);
        }else {
            Intent intent = new Intent(mContext, PhishingGameActivity.class);
            intent.putExtra("incorrectItemsSerializeable", (Serializable) incorrectItems);
            mContext.startActivity(intent);
        }



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_page);


        Bundle bundle = getIntent().getExtras();
        incorrectItems= (ArrayList<SpamHam>) bundle.getSerializable("incorrectItemsSerializeable");
        currentType = bundle.getString("type");

        if (currentType.equals("spam")){
            score = SpamOrHamGame.score;
        }else{
            score = PhishingGameActivity.score;
        }

        boolean isEmpty = incorrectItems.isEmpty();
        System.out.println("Score"+ score);
        scoreContainer = (LinearLayout) findViewById(R.id.linearLayout1);
        button = (Button) findViewById(R.id.continueButton);
        mContext = this;
        putScreen(isEmpty);

    }
}
