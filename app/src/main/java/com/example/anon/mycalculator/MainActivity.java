package com.example.anon.mycalculator;

import android.content.Context;
import android.content.Intent;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity
{

    Button bSimple;
    Button bAdvanced;
    Button bAboutMe;
    Button bExit;

    Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        vb = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        bSimple.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                vb.vibrate(5);
                goToSimple();
            }
        });

        bAdvanced.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                vb.vibrate(5);
                goToAdvanced();
            }
        });

        //TODO: instead of new activity just show a toast
        bAboutMe.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                vb.vibrate(5);
                goToAboutMe();
            }
        });

        bExit.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                vb.vibrate(5);
                System.exit(0);
            }
        });
    }

    void initButtons()
    {
        bSimple = (Button) findViewById(R.id.button0);
        bAdvanced = (Button) findViewById(R.id.button1);
        bAboutMe = (Button) findViewById(R.id.button2);
        bExit = (Button) findViewById(R.id.button_EXIT);
    }

    void goToSimple()
    {
        Intent intent = new Intent(this, SimpleActivity.class);
        startActivity(intent);
    }

    void goToAdvanced()
    {
        Intent intent = new Intent(this, AdvancedActivity.class);
        startActivity(intent);
    }

    void goToAboutMe()
    {
        Intent intent = new Intent(this, AboutMeActivity.class);
        startActivity(intent);
    }
}
