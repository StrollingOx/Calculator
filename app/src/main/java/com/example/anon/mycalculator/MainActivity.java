package com.example.anon.mycalculator;

import android.content.Context;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button bSimple;
    Button bAdvanced;
    Button bAboutMe;

    Vibrator vb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initButtons();
        vb = (Vibrator) getApplicationContext().getSystemService(Context.VIBRATOR_SERVICE);

        bSimple.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                vb.vibrate(5);
                showSimple();
            }
        });
    }

    void initButtons()
    {
        bSimple = findViewById(R.id.button0);
    }

    void showSimple()
    {
        getSupportFragmentManager().beginTransaction().replace(R.id.layout_simple, new FragmentSimple()).commit();
    }
}
