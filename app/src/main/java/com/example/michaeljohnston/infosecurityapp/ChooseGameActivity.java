package com.example.michaeljohnston.infosecurityapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_game);
    }

    public void enterSpamHamScreen(View view){
        Intent intent = new Intent(this, SpamOrHamGame.class);
        startActivity(intent);
    }
    public void enterPhishScreen(View view){
        Intent intent = new Intent(this, PhishingGameActivity.class);
        startActivity(intent);
    }
}
