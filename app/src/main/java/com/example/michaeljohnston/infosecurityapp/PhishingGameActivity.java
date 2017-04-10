package com.example.michaeljohnston.infosecurityapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class PhishingGameActivity extends AppCompatActivity {
    public static LinearLayout imgContainer;
    public static ArrayList<SpamHam> items = new ArrayList<SpamHam>();
    public static ArrayList<SpamHam> incorrectItems = new ArrayList<SpamHam>();
    public static SpamHam currentItem;
    public static int score = 0;
    private static Context mContext;

    /**
     * Initializes the game-
     *
     *   !IMPORTANT------------- NEEDS UPDATE TO ACTUAL INFO, CURRENTLY IS FILLED WITH PLACEHOLDER DATA
     */
    public static void initGame(){
        if (items.size()>0){
            items.clear();
        }
        System.out.println("size "+ ScorePageActivity.incorrectItems.size());
        if (incorrectItems.size()>0){
            for (SpamHam s: incorrectItems){
                System.out.println("Incorrect "+ s.name);
                items.add(s);
            }
            incorrectItems.clear();
        }else {
            score = 0;
            //this is where you initialize the new game's items, everything else handles the repeat case
            for (int i = 0; i < 10; i++) {
                SpamHam s = new SpamHam();
                if (i % 2 == 0) {
                    s.name = "PHISH" + i;
                    s.text = "I am phish number " + i;
                    s.isSpam = true;
                } else {
                    s.name = "NONPHISH" + i;
                    s.text = "I am non-phish number " + i;
                    s.isSpam = false;
                }
                items.add(s);
            }
        }

        for (SpamHam s : items){
            System.out.println(s.name);
        }

        nextTurn();
    }

    /**
     * Next turn will inspect lists to end/restart game.
     *
     *  !IMPORTANT-------------NEEDS ACTIVITY SWITCH AFTER GAME TO SHOW
     *   WHAT Q's WERE INCORRECT/SCORE.
     */
    public static void nextTurn(){
        if (items.size()==0){
            Intent intent = new Intent(mContext, ScorePageActivity.class);
            intent.putExtra("type", "phish");
            if (incorrectItems.size()>0){



                //TELL THEM THEIR SCORE/ANSWERS INCORRECT

                // (add that here)
                System.out.println("end of round: next game starting");

                //cast the arraylist of incorrect items to a serializeable type
                intent.putExtra("incorrectItemsSerializeable", (Serializable) incorrectItems);


                mContext.startActivity(intent);
                //CONTINUE THE GAME

                incorrectItems.clear();
            }else{
                //TELL THEM THEIR SCORE/ANSWERS INCORRECT (in this case there will be no answers incorrect



                //cast the arraylist of incorrect items to a serializeable type
                intent.putExtra("incorrectItemsSerializeable", (Serializable) incorrectItems);

                score=0;
                mContext.startActivity(intent);
                System.out.println("End game");
                //END THE GAME IF ALL LISTS ARE 0

            }
        }else {

            //CONTINUE THE GAME
            currentItem = items.remove(0);
            System.out.println("Current Item: " + currentItem.name);
            System.out.println(items.size());
            putScreen();
        }
    }

    /**
     * Puts the current item text to the screen
     *
     *
     *  !IMPORTANT-------------NEEDS UPDATE TO PUT IMAGE TO SCREEN
     */
    public static void putScreen(){
        TextView text = (TextView) imgContainer.findViewById(R.id.list_header);
        text.setText( currentItem.text );
        imgContainer.removeAllViews();
        imgContainer.addView(text);
    }

    /**
     * Returns the player to the game screen
     */
    public static void endGame(){
        Intent intent = new Intent(mContext, ChooseGameActivity.class);
        mContext.startActivity(intent);
    }

    /**
     * When the "Spam" button is clicked, check if it is indeed not spam. Increment score accordingly.
     * @param v View
     */
    public static void spamClick(View v){
        if (currentItem.isSpam){
            score++;
        }else {
            incorrectItems.add(currentItem);
        }
        nextTurn();
    }

    /**
     * When the "Not Spam" button is clicked, check if it is indeed not spam. Increment score accordingly.
     * @param v View
     */
    public static void hamClick(View v){
        if (!currentItem.isSpam){
            score++;
        }else {
            incorrectItems.add(currentItem);
        }
        nextTurn();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phishing_game);
        imgContainer = (LinearLayout) findViewById(R.id.linearLayout2);
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            incorrectItems = (ArrayList<SpamHam>) bundle.getSerializable("incorrectItemsSerializeable");
        }
        mContext = this;
        initGame();
    }
}
