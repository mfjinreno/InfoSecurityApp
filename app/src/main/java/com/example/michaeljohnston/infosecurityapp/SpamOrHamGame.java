package com.example.michaeljohnston.infosecurityapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class SpamOrHamGame extends AppCompatActivity {
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

        if (incorrectItems.size()>0){
            incorrectItems.clear();
        }

        for(int i = 0; i<10; i++){
            SpamHam s = new SpamHam();
            if (i%2 ==0){
                s.name = "SPAM" + i;
                s.text = "I am spam number " +i;
                s.isSpam = true;
            }else {
                s.name = "HAM" + i;
                s.text = "I am ham number " +i;
                s.isSpam = false;
            }
            items.add(s);
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
            if (incorrectItems.size()>0){
                for (SpamHam s : incorrectItems){
                    items.add(s);
                }

                incorrectItems.clear();
                //TELL THEM THEIR SCORE/ANSWERS INCORRECT

                // (add that here)


                //CONTINUE THE GAME
                nextTurn();

            }else{
                //TELL THEM THEIR SCORE/ANSWERS INCORRECT (in this case there will be no answers incorrect

                // (add that here)

                //END THE GAME IF ALL LISTS ARE 0
                endGame();
            }
        }else {
            System.out.println("Next one!!");
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
        setContentView(R.layout.activity_spam_or_ham_game);
        imgContainer = (LinearLayout) findViewById(R.id.linearLayout1);
        mContext = this;
        initGame();
    }
}
