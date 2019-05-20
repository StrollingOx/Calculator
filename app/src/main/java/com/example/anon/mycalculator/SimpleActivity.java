package com.example.anon.mycalculator;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.regex.Pattern;

public class SimpleActivity extends AppCompatActivity
{
    private TextView screen;
    private String display = "";
    private String currentOperator = "";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        initLayout();
        updateScreen();

    }

    protected void initLayout()
    {
        screen = (TextView)findViewById(R.id.textView);
    }

    private void updateScreen()
    {
        screen.setText(display);
    }

    public void onClickNumber(View v)
    {
        Button button = (Button) v;
        display += button.getText();
        updateScreen();
    }

    public void onClickOperator(View v)
    {
        Button button = (Button) v;
        display += button.getText();
        currentOperator = button.getText().toString();
        updateScreen();
    }

    private void clear()
    {
        display = "";
        currentOperator = "";
    }

    public void onClickClear(View v)
    {
        clear();
        updateScreen();
    }

    private double operate (String a, String b, String op)
    {
        switch (op)
        {
            case "+":
                return Double.valueOf(a) + Double.valueOf(b);
            case "-":
                return Double.valueOf(a) - Double.valueOf(b);
            case "x":
                return Double.valueOf(a) * Double.valueOf(b);
            case "÷":
                try
                {
                    return Double.valueOf(a) / Double.valueOf(b);
                }catch(Exception e)
                {
                    Log.d("Calc", e.getMessage());
                }

            default:
                return -1;
        }
    }
    public void onClickEqual(View v)
    {
        String[] operation = display.split(Pattern.quote(currentOperator));
        if(operation.length<2) return;

        Double result = operate(operation[0], operation[1], currentOperator);
        if(currentOperator.equals("÷") && operation[1].equals("0"))
            screen.setText(display + "\nCan't divie by 0!");
        else
            screen.setText(display + "\n" + String.valueOf(result));
    }
}
