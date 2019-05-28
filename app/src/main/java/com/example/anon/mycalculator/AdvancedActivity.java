package com.example.anon.mycalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class AdvancedActivity extends AppCompatActivity
{
    private TextView screen;
    private String display;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
        initLayout();
        updateScreen();

    }

    protected void initLayout()
    {
        screen = (TextView)findViewById(R.id.textView_adv);
    }
    private void updateScreen()
    {
        screen.setText(display);
    }

}
