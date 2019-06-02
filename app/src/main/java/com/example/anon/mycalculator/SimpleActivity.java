package com.example.anon.mycalculator;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.regex.Pattern;

public class SimpleActivity extends AppCompatActivity
{
    private TextView screen;
    private String display = "";
    private String currentOperator = "";
    private String result = "";

    Toast toastError;
    Toast toastTooBigNumber;
    Toast toastDivideByZero;
    Toast toastNoNumber;
    Toast toastCantInsertDot;
    //private String divideByZeroErr = "Dividing by 0 is prohibited!";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple);
        initLayout();
        initToasts();
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
    private void initToasts()
    {
        toastError = Toast.makeText(this, "Slow down!", Toast.LENGTH_SHORT);
        toastTooBigNumber = Toast.makeText(this, "The number is too big!", Toast.LENGTH_SHORT);
        toastDivideByZero = Toast.makeText(this, "Dividing by 0 is prohibited!", Toast.LENGTH_SHORT);
        toastNoNumber = Toast.makeText(this, "Enter the number first!", Toast.LENGTH_SHORT);
        toastCantInsertDot = Toast.makeText(this, "You dont need this dot :)", Toast.LENGTH_SHORT);
    }


    private boolean isNumbersLengthCorrect()
    {
        if(currentOperator.equals("") && display.length()>13)
            return true;

        if(!currentOperator.equals(""))
        {
            String _display = display;
            if(currentOperator.equals("-") && display.charAt(0) == '-')
            {
                _display = display.substring(1);
            }
            String [] numbers = _display.split(Pattern.quote(currentOperator));

            if(numbers.length>1)
                return numbers[1].length() > 13;
            else
                return false;

        }

        return false;
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
    private boolean canInsertDot()
    {
        if(isNumbersLengthCorrect())
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
    private boolean operateHandleErrors(String aa, String bb, String op)
    {
        return aa.equals("")
                || bb.equals("")
                || display.equals("Infinity")
                || aa.equals("-")
                || aa.endsWith("E")
                || bb.equals("E");
    }
    private void clear()
    {
        display = "";
        currentOperator = "";
        result = "";
    }
    private double operate (String aa, String bb,@NonNull String op)
    {
        if(operateHandleErrors(aa, bb, op))
        {
            toastError.show();
            return 0;
        }

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
                }catch(Exception e)
                {
                    Log.d("operate()", e.getMessage());
                }

            default:
                return -1;
        }
    }


    public void onClickNumber(View v)
    {
        if(!result.equals(""))
        {
            clear();
            updateScreen();
        }
        if(isNumbersLengthCorrect())
        {
            toastTooBigNumber.show();
            return;
        }

        Button button = (Button) v;
        display += button.getText();
        updateScreen();



    }
    public void onClickOperator(View v)
    {
        if(display.equals(""))
        {
            toastNoNumber.show();
            return;
        }

        Button button = (Button) v;

        if(!result.equals(""))
        {
            //First number will be previous equation's result
            String _display;
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
        boolean insertToast = true;
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
        {
            insertToast = false;
            display+="0.";
        }

        if(canInsertDot())
            display+=".";
        else
            if(insertToast) toastCantInsertDot.show();



        updateScreen();
    }
    public void onClickSignChange (View v)
    {
        if(!result.equals(""))
        {
            return;
        }

        if(display.equals(""))
        {
            toastNoNumber.show();
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
    public void onClickClear(View v)
    {
        clear();
        updateScreen();
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

        //CASE: Dividing by 0
        double secondNumber = Double.valueOf(operation[1]);
        if(currentOperator.equals("÷") && secondNumber == 0.0)
        {
            toastDivideByZero.show();
            return false;
        }


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
        screen.setText(display + "\n= " + String.valueOf(result));
    }
}
