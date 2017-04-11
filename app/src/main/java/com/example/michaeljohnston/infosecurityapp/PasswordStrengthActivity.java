package com.example.michaeljohnston.infosecurityapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.math.BigInteger;

public class PasswordStrengthActivity extends AppCompatActivity {
    public static Context mContext;
    public static EditText mEdit;
    public static TextView mText;
    public static LinearLayout parent;

    /**
     * Calculates approximate time to bruteforce a password according to its length/complexity
     * @param pass Password to be processed
     * @return BigInteger time - Time it would take (seconds) to bruteforce pass
     */
    public static BigInteger calculateStrength(String pass){
        BigInteger time;
        BigInteger speed = new BigInteger("10000000000");
        int space = 24;
        BigInteger size = new BigInteger(String.valueOf(pass.length()));

        long length = (long) pass.length();

        //if pass has numbers
        if (pass.matches(".*\\d+.*")){
            space +=10;
        }

        //if pass isnt just numbers and digits (i.e. special characters)
        if (pass.matches("^[A-Z0-9]+$")){
            space +=10;
        }

        //take size^space
        size = size.pow(space);

        //divide that by the speed of the potential algorithm (iterations per second)
        time = size.divide(speed);
        return time;
    }

    public static void testPassword(View v){
        String text = mEdit.getText().toString();

        if (text.equals("")){
            TextView textView = (TextView) parent.findViewById(R.id.textViewPass);
            textView.setText( "Please enter a valid password" );
            parent.removeAllViews();
            parent.addView(textView);
        }else {
            String length = "";
            BigInteger l = calculateStrength(text);

            BigInteger hourSeconds = new BigInteger("3600");
            BigInteger daySeconds = new BigInteger("86400");
            BigInteger yearSeconds = new BigInteger("31557600");

            //append the appropriate unit of time
            if(l.compareTo(hourSeconds)>=0&&l.compareTo(daySeconds)<=0){
                l = l.divide(hourSeconds);
                length = String.valueOf(l) + " hours";
            }else if (l.compareTo(daySeconds)>=0&&l.compareTo(yearSeconds)<=0){
                l = l.divide(daySeconds);
                length = String.valueOf(l) + " days";
            }else if (l.compareTo(yearSeconds)>0){
                l = l.divide(yearSeconds);
                length = String.valueOf(l) + " years";
            }else {
                length = String.valueOf(l) + " seconds";
            }


            //pass the updated text view to the parent linear layout
            TextView textView = (TextView) parent.findViewById(R.id.textViewPass);
            textView.setText(length);
            parent.removeAllViews();
            parent.addView(textView);

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_strength);

        //get all of the layout elements
        mEdit   = (EditText)findViewById(R.id.editText);
        mText   = (TextView)findViewById(R.id.textViewPass);
        parent = (LinearLayout) findViewById(R.id.textViewParent);

        //for static Intent calls (if needed)
        mContext=this;
    }
}
