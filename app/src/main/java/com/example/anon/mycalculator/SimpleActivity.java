package com.example.anon.mycalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.regex.Pattern;

public class SimpleActivity extends AppCompatActivity
{
    private TextView screen;
    private String display = "";
    private String currentOperator = "";
    private String result = "";

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
        if(result != "")
        {
            clear();
            updateScreen();
        }
        Button button = (Button) v;
        display += button.getText();
        updateScreen();
    }
    private boolean isOperator(char op)
    {
        switch(op)
        {
            case '+': return true;
            case '-': return true;
            case 'x': return true;
            case '÷': return true;
            default: return false;
        }
    }
    public void onClickOperator(View v)
    {
        //TODO: changing operation causes display errors
        if(display.equals("")) return;

        Button button = (Button) v;

        if(!result.equals(""))
        {
            String _display = result;
            clear();
            display = _display;
        }

        if(!currentOperator.equals(""))
        {
            Log.d("CalcX", ""+display.charAt(display.length()-1));
            if(isOperator(display.charAt(display.length()-1)))
            {
                display = display.replace(display.charAt(display.length() - 1), button.getText().charAt(0));
                updateScreen();
                return;
            }else
            {
                getResult();
                display = result;
                result = "";
            }
            currentOperator = button.getText().toString();
        }

        display += button.getText();
        currentOperator = button.getText().toString();
        updateScreen();
    }
    public void onClickDot(View v)
    {
        //Button button = (Button) v;
        //TODO: 0.6.6.6.6.6 error
        if(display.equals(""))
            display = "0.";
        else if(  display.charAt(display.length()-1) == '+'
                || display.charAt(display.length()-1) == '-'
                || display.charAt(display.length()-1) == 'x'
                || display.charAt(display.length()-1) == '÷')
            display+="0.";
        else if(!display.equals("")
                && !isOperator(display.charAt(display.length()-1))
                && display.charAt(display.length()-1) != '.')
            display += ".";
        else if(display.charAt(display.length()-1) == '.')
            return;
        updateScreen();
    }

    public void onClickSignChange (View v)
    {
        //Button button = (Button) v;
    }
    private void clear()
    {
        display = "";
        currentOperator = "";
        result = "";
    }
    public void onClickClear(View v)
    {
        clear();
        updateScreen();
    }
    private double operate (String aa, String bb, String op)
    {
        //TODO: 10.1 * 6  = 60.59999 error EDIT: kinda fixed....
        double a = Double.valueOf(aa);
        double b = Double.valueOf(bb);
        BigDecimal BDa = new BigDecimal(aa);
        BigDecimal BDb = new BigDecimal(bb);
        System.out.println(a);
        System.out.println(b);
        switch (op)
        {
            case "+":
                return a+b;
            case "-":
                return a-b;
            case "x":
                System.out.println((float)a*b);
                return Double.valueOf(BDa.multiply(BDb).toString());
            case "÷":
                try
                {
                    return a/b; //Should i use BigDecimal here too?
                }catch(Exception e)
                {
                    Log.d("Calc", e.getMessage());
                }

            default:
                return -1;
        }
    }
    private boolean getResult()
    {
        if(currentOperator.equals("")) return false;
        String[] operation = display.split(Pattern.quote(currentOperator));
        if(operation.length<2) return false;


        result = ""+operate(operation[0], operation[1], currentOperator);
        return true;
    }
    @SuppressLint("SetTextI18n")
    public void onClickEqual(View v)
    {
        if(display.equals("")) return;
        if(!getResult()) return;
        screen.setText(display + "\n" + String.valueOf(result));
    }
}
