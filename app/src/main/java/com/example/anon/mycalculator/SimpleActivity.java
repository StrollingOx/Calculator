package com.example.anon.mycalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class SimpleActivity extends AppCompatActivity
{
    //TODO: When you switch orientation while you are in the middle of equation it resets. Why? No idea.
    private TextView screen;
    private String display = "";
    private String currentOperator = "";
    private String result = "";

    private String divideByZeroErr = "Dividing by 0 is prohibited!";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        initLayout();
        updateScreen();

    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);

        savedInstanceState.putString("display", display);
        savedInstanceState.putString("currentOperator", currentOperator);
        savedInstanceState.putString("result", result);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        display = savedInstanceState.getString("display");
        currentOperator = savedInstanceState.getString("currentOperator");
        result = savedInstanceState.getString("result");
    }

    protected void initLayout()
    {
        screen = (TextView)findViewById(R.id.textView);
    }
    private void updateScreen()
    {
        screen.setText(display);
    }


    private boolean isNumbersLengthCorrect()
    {
        if(currentOperator.equals("") && display.length()>13)
            return false;

        if(!currentOperator.equals(""))
        {
            String _display = display;
            if(currentOperator.equals("-") && display.charAt(0) == '-')
            {
                _display = display.substring(1);
            }
            String [] numbers = _display.split(Pattern.quote(currentOperator));

            if(numbers.length>1)
            {
                if (numbers[1].length() > 13) return false;
                else return true;
            }
            else
                return true;

        }

        return true;
    }
    public void onClickNumber(View v)
    {
        if(!result.equals(""))
        {
            clear();
            updateScreen();
        }
        if(!isNumbersLengthCorrect())
        {
           return;
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
        if(display.equals("")) return;

        Button button = (Button) v;

        if(!result.equals(""))
        {
            //First number will be previous equation's result
            String _display;
            if(display.equals(divideByZeroErr))
                _display = "0";
            else
                _display = result;
            clear();
            display = _display;
        }

        if(!currentOperator.equals(""))
        {
            if(isOperator(display.charAt(display.length()-2)))
            {
                display = display.replace(display.charAt(display.length() - 2), button.getText().charAt(0));
                currentOperator = button.getText().toString();
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

        display += "\n" + button.getText() + "\n"; //TODO: If app fucks up, go to getResult() and delete replaceAll(...)
        currentOperator = button.getText().toString();
        updateScreen();
    }

    public void onClickDot(View v)
    {
        if(!result.equals(""))
        {
            //First number will be previous equation's result
            String _display = result;
            clear();
            display = _display;
        }

        if(display.equals("")
                || (!currentOperator.equals("")
                    && display.charAt(display.length()-2) == currentOperator.charAt(0))) //-2 instead of -1 beacuse of the '\n'
            display+="0.";

        if(canInsertDot())
        {
            display+=".";
        }

        updateScreen();
    }
    private boolean canInsertDot()
    {
        if(!isNumbersLengthCorrect())
            return false;

        if(currentOperator.equals("") && display.contains("."))
            return false;


        if(!currentOperator.equals(""))
        {
            String _display = display;
            if(currentOperator.equals("-") && display.charAt(0) == '-')
            {
                _display = display.substring(1);
            }
            String [] numbers = _display.split(Pattern.quote(currentOperator));

            if(numbers.length>1)
                if (numbers[1].contains("."))
                    return false;

        }

        return true;
    }

    public void onClickSignChange (View v)
    {
        if(!result.equals(""))
        {
            return;
        }

        if(display.equals(""))
        {
            return;
        }
        else
        {
            if(display.charAt(0) == '-')
            {
                display = display.substring(1);
            }
            else
                display = "-" + display;

            updateScreen();
        }

    }

    public void onClickDelete(View v)
    {
        if(!display.equals("") && display.charAt(display.length()-1) == '\n')
        {
            //TODO: Kinda gay.
            display = deleteLastChar(display);
            display = deleteLastChar(display);
            display = deleteLastChar(display);
            currentOperator = "";

        }
        else if(!display.equals("") && screen.getText().toString().contains("="))
        {
            return;
        }
        else
        {
            if(!display.equals(""))
                display = deleteLastChar(display);
        }
        updateScreen();

    }

    public void onClickPercent(View v)
    {
        double number;

        if(!result.equals(""))
        {
            number = calculatePercent(result);
            clear();
            display+=number;
            updateScreen();
            return;
        }
        if(display.equals(""))
        {
            return;
        }
        else if (currentOperator.equals("") && !display.equals(""))
        {
            number = calculatePercent(display);
            display = "" + number;
            updateScreen();
        }
        else if(!currentOperator.equals(""))
        {
            String _display = display;
            boolean signChanged = false;
            if(currentOperator.equals("-") && display.charAt(0) == '-')
            {
                _display = display.substring(1);
                signChanged = true;
            }
            _display = _display.replaceAll("\n","");
            String [] temp = _display.split(Pattern.quote(currentOperator));
            switch(temp.length)
            {
                case 0:
                    display = "onClickPercent error, case 0";
                    updateScreen();
                    break;
                case 1:
                    return;
                case 2:
                    number = calculatePercent(temp[1]);
                    if(signChanged)
                        display = "-"+temp[0] + "\n" + currentOperator + "\n" + number;
                    else
                        display = ""+temp[0] + "\n" + currentOperator + "\n" + number;
                    updateScreen();
                    break;
                default:
                    System.out.println("Cheeki Breeki!!!!!!!!!!!!!!");
            }
        }


    }

    public double calculatePercent(String s)
    {
        return Double.valueOf(new BigDecimal(s).multiply(new BigDecimal(0.01), new MathContext(10, RoundingMode.HALF_UP)).toString());
    }

    public String deleteLastChar(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
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
    private double operate (String aa, String bb,@NonNull String op)
    {
        if (aa.equals("")
                || bb.equals("")
                || display.equals("Infinity")
                || aa.equals("-")
                || aa.endsWith("E")
                || bb.equals("E"))
            return 0;

        double a = Double.valueOf(aa);
        BigDecimal BDa = new BigDecimal(aa);

        double b = Double.valueOf(bb);
        BigDecimal BDb = new BigDecimal(bb);

        switch (op)
        {
            case "+":
                return Double.valueOf(BDa.add(BDb).toString());
            case "-":
                return Double.valueOf(BDa.subtract(BDb).toString());
            case "x":
                return Double.valueOf(BDa.multiply(BDb).toString());
            case "÷":
                try
                {
                    if(BDb.compareTo(BigDecimal.ZERO) != 0)
                        return Double.valueOf((BDa.divide(BDb, new MathContext(8, RoundingMode.HALF_UP))).toString());
                    else
                        display = divideByZeroErr;
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
        boolean changeSign = false;

        //CASE: Equation not empty
        if(currentOperator.equals("")) return false;

        //CASE: first number is negative
        if(currentOperator.equals("-") && display.charAt(0) == '-')
        {
            display = display.substring(1);
            changeSign = true;
        }

        //CASE: There are two numbers
        String[] operation = display.replaceAll("\\s+", "").split(Pattern.quote(currentOperator));

        if(operation.length<2) return false;

        if(changeSign)
        {
            display = "-" + display;
            result = "" + operate("-" + operation[0], operation[1], currentOperator);
        }
        else
            result = ""+operate(operation[0], operation[1], currentOperator);
        return true;
    }
    @SuppressLint("SetTextI18n")
    public void onClickEqual(View v)
    {
        //TODO: Split display to two TextViews and change color for result operation
        if(display.equals("")) return;
        if(!getResult()) return;
        if(display.equals(divideByZeroErr))
            screen.setText(display);
        else
            screen.setText(display + "\n= " + String.valueOf(result));
    }
}
