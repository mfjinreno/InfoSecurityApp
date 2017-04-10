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
                items.add(s);
            }
            incorrectItems.clear();
        }else {
            score = 0;

            /**
             * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
             * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
             * !!!!!!!!!!!!!!!!   ADD HERE  !!!!!!!!!!!!!!!!!!!!!
             * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
             * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
             * EVERY ITEM WILL BE A SPAMHAM, I'M TOO LAZY TO MAKE A SEPARATE OBJECT CLASS FOR PHISH
             * INITIALIZE OBJECTS CONTAINED IN THE QUIZ HERE AND ADD THEM TO items ARRAYLIST
             * CURRENT DATA IS PLACEHOLDER ~ONLY~
             */
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
     */
    public static void nextTurn(){
        if (items.size()==0){

            //create intent and set the type
            Intent intent = new Intent(mContext, ScorePageActivity.class);
            intent.putExtra("type", "phish");
            if (incorrectItems.size()>0){



                //TELL THEM THEIR SCORE/ANSWERS INCORRECT
                System.out.println("end of round: next game starting");

                //cast the arraylist of incorrect items to a serializeable type
                intent.putExtra("incorrectItemsSerializeable", (Serializable) incorrectItems);

                mContext.startActivity(intent);
                incorrectItems.clear();
                //CONTINUE THE GAME

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
            //update current item
            currentItem = items.remove(0);

            //put its info to the screen
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

        /**
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!   ADD HERE  !!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * Update both the view and this method to send an image item instead
         */


        //updates text
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

        //gets the incorrect item list back from the score activity if needed
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            incorrectItems = (ArrayList<SpamHam>) bundle.getSerializable("incorrectItemsSerializeable");
        }

        //sets class Context for static method calls to startActivity and Intent init
        mContext = this;

        //initialize the game
        initGame();
    }
}
