package com.example.michaeljohnston.infosecurityapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PasswordStrengthActivity extends AppCompatActivity {
    public static Context mContext;
    public static EditText mEdit;
    public static TextView mText;
    public static LinearLayout parent;


    public static long calculateStrength(String pass){
        long time = 0;

        return time;
    }

    public static void testPassword(View v){
        String text = mEdit.getText().toString();

        if (text.equals("")){
            TextView textView = (TextView) parent.findViewById(R.id.textViewPass);
            textView.setText( "Please enter a valid password" );
            parent.removeAllViews();
            parent.addView(textView);
        }else{
            String length = "";
            long l = calculateStrength(text);
            length = String.valueOf(l);

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
        mEdit   = (EditText)findViewById(R.id.editText);
        mText   = (TextView)findViewById(R.id.textViewPass);
        parent = (LinearLayout) findViewById(R.id.textViewParent);

        mContext=this;
    }
}
