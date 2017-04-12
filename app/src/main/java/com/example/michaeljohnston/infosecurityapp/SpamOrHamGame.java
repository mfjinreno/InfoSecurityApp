package com.example.michaeljohnston.infosecurityapp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

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
        System.out.println("size "+ ScorePageActivity.incorrectItems.size());

        //handles cases where the game is in its repeat state
        if (incorrectItems.size()>0){
            for (SpamHam s: incorrectItems){
                System.out.println("Incorrect "+ s.name);
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
            //this is where you initialize the new game's items, everything else handles the repeat case
            Integer legit[] = {R.mipmap.legit1, R.mipmap.legit2, R.mipmap.legit3, R.mipmap.legit4, R.mipmap.legit5, R.mipmap.legit6, R.mipmap.legit7, R.mipmap.legit8, R.mipmap.legit9, R.mipmap.legit10, R.mipmap.legit11, R.mipmap.legit12, R.mipmap.legit13, R.mipmap.legit14, R.mipmap.legit15, R.mipmap.legit16, R.mipmap.legit17, R.mipmap.legit18, R.mipmap.legit19, R.mipmap.legit20, R.mipmap.legit21};
            Integer phish[] = {R.mipmap.screenshot21, R.mipmap.screenshot22, R.mipmap.screenshot23, R.mipmap.screenshot24, R.mipmap.screenshot25, R.mipmap.screenshot26, R.mipmap.screenshot27, R.mipmap.screenshot28, R.mipmap.screenshot29, R.mipmap.screenshot30, R.mipmap.screenshot31, R.mipmap.screenshot32, R.mipmap.screenshot33, R.mipmap.screenshot34, R.mipmap.screenshot35, R.mipmap.screenshot36, R.mipmap.screenshot37, R.mipmap.screenshot38, R.mipmap.screenshot39, R.mipmap.screenshot40, R.mipmap.screenshot41, R.mipmap.screenshot42, R.mipmap.screenshot1, R.mipmap.screenshot2, R.mipmap.screenshot3, R.mipmap.screenshot4, R.mipmap.screenshot5, R.mipmap.screenshot6, R.mipmap.screenshot7, R.mipmap.screenshot8, R.mipmap.screenshot9, R.mipmap.screenshot10, R.mipmap.screenshot12, R.mipmap.screenshot13, R.mipmap.screenshot14, R.mipmap.screenshot15, R.mipmap.screenshot16, R.mipmap.screenshot17, R.mipmap.screenshot18, R.mipmap.screenshot19, R.mipmap.screenshot20, R.mipmap.spam1, R.mipmap.spam2, R.mipmap.spam3, R.mipmap.spam4, R.mipmap.spam5, R.mipmap.spam6, R.mipmap.spam7};

            Random index = new Random();
            // arrays to check if image already used
            ArrayList<Integer> legitUsed = new ArrayList<>(5);
            ArrayList<Integer> spamUsed = new ArrayList<>(5);
            //this is where you initialize the new game's items, everything else handles the repeat case
            for (int i = 0; i < 10; i++) {
                SpamHam s = new SpamHam();
                if (i % 2 == 0) {
                    s.name = "SPAM" + i;
                    s.text = "I am spam number " + i;
                    s.imgID = index.nextInt(phish.length);
                    s.isSpam = true;
                } else {
                    s.name = "HAM" + i;
                    s.text = "I am ham number " + i;
                    s.imgID = index.nextInt(legit.length);
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
            intent.putExtra("type", "spam");
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

                // (add that here)


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

        /**
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!   ADD HERE  !!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
         * Update both the view and this method to send an image item instead
         */


        Integer legit[] = {R.mipmap.legit1, R.mipmap.legit2, R.mipmap.legit3, R.mipmap.legit4, R.mipmap.legit5, R.mipmap.legit6, R.mipmap.legit7, R.mipmap.legit8, R.mipmap.legit9, R.mipmap.legit10, R.mipmap.legit11, R.mipmap.legit12, R.mipmap.legit13, R.mipmap.legit14, R.mipmap.legit15, R.mipmap.legit16, R.mipmap.legit17, R.mipmap.legit18, R.mipmap.legit19, R.mipmap.legit20, R.mipmap.legit21};
        Integer phish[] = {R.mipmap.screenshot21, R.mipmap.screenshot22, R.mipmap.screenshot23, R.mipmap.screenshot24, R.mipmap.screenshot25, R.mipmap.screenshot26, R.mipmap.screenshot27, R.mipmap.screenshot28, R.mipmap.screenshot29, R.mipmap.screenshot30, R.mipmap.screenshot31, R.mipmap.screenshot32, R.mipmap.screenshot33, R.mipmap.screenshot34, R.mipmap.screenshot35, R.mipmap.screenshot36, R.mipmap.screenshot37, R.mipmap.screenshot38, R.mipmap.screenshot39, R.mipmap.screenshot40, R.mipmap.screenshot41, R.mipmap.screenshot42, R.mipmap.screenshot1, R.mipmap.screenshot2, R.mipmap.screenshot3, R.mipmap.screenshot4, R.mipmap.screenshot5, R.mipmap.screenshot6, R.mipmap.screenshot7, R.mipmap.screenshot8, R.mipmap.screenshot9, R.mipmap.screenshot10, R.mipmap.screenshot12, R.mipmap.screenshot13, R.mipmap.screenshot14, R.mipmap.screenshot15, R.mipmap.screenshot16, R.mipmap.screenshot17, R.mipmap.screenshot18, R.mipmap.screenshot19, R.mipmap.screenshot20, R.mipmap.spam1, R.mipmap.spam2, R.mipmap.spam3, R.mipmap.spam4, R.mipmap.spam5, R.mipmap.spam6, R.mipmap.spam7};

        ImageView img = (ImageView) imgContainer.findViewById(R.id.imageView);
        //puts the text
        if (currentItem.isSpam){
            img.setImageResource( phish[currentItem.imgID] );
        } else {
            img.setImageResource( legit[currentItem.imgID] );
        }

        imgContainer.removeAllViews();
        imgContainer.addView(img);
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

        //gets the incorrectItems list from score screen if needed
        Bundle bundle = getIntent().getExtras();
        if (bundle!=null) {
            incorrectItems = (ArrayList<SpamHam>) bundle.getSerializable("incorrectItemsSerializeable");
        }

        //sets class Context for static method calls to startActivity and Intent init
        mContext = this;
        initGame();
    }
}
