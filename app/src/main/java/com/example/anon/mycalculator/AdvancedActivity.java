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

public class AdvancedActivity extends AppCompatActivity
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
    Toast toastCantSqrtThat;
    Toast toastLogOneOrNegative;
    Toast toastLnOneOrNegative;
    Toast toastIncorrectInput;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advanced);
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


    //advanced calculator-----------------------
    public void onClickLn(View v)
    {
        if((!display.equals("") && currentOperator.equals(""))
                || (screen.getText()).toString().endsWith("= " + String.valueOf(result)))
        {
            double a;
            if((screen.getText()).toString().endsWith("= " + String.valueOf(result)))
                a = Double.valueOf(result);
            else
            {
                try {a = Double.valueOf(display);} catch(NumberFormatException e) { Log.d("onClickLn", "NumberFormatException"); toastIncorrectInput.show(); return; }
            }
            if(a == 1.0 || a<0.0)
            {
                toastLnOneOrNegative.show();
                return;
            }
            double result = Math.log(a);
            if(result >= Double.MAX_VALUE)
            {
                clear();
                display = "∞";
                updateScreen();
                display = "";
            }
            else if (result <= Integer.MIN_VALUE)
            {
                clear();
                display = "-∞";
                updateScreen();
                display = "";
            }
            else
            {
                result = Double.valueOf(new BigDecimal(result, new MathContext(11, RoundingMode.HALF_UP)).toString());
                clear();
                display+= ""+result;
                updateScreen();
            }
        }
        else
            return;
    }
    public void onClickLog(View v)
    {
        if((!display.equals("") && currentOperator.equals(""))
                || (screen.getText()).toString().endsWith("= " + String.valueOf(result)))
        {
            double a;
            if((screen.getText()).toString().endsWith("= " + String.valueOf(result)))
                a = Double.valueOf(result);
            else
            {
                try {a = Double.valueOf(display);} catch(NumberFormatException e) { Log.d("onClickLog", "NumberFormatException"); toastIncorrectInput.show(); return; }
            }
            if(a == 1.0 || a<0.0)
            {
                toastLogOneOrNegative.show();
                return;
            }
            double result = Math.log10(Math.toRadians(a));
            if(result >= Double.MAX_VALUE)
            {
                clear();
                display = "∞";
                updateScreen();
                display = "";
            }
            else if (result <= Integer.MIN_VALUE)
            {
                clear();
                display = "-∞";
                updateScreen();
                display = "";
            }
            else
            {
                result = Double.valueOf(new BigDecimal(result, new MathContext(11, RoundingMode.HALF_UP)).toString());
                clear();
                display+= ""+result;
                updateScreen();
            }
        }
        else
            return;
    }
    public void onClickSin(View v)
    {
        if((!display.equals("") && currentOperator.equals(""))
                || (screen.getText()).toString().endsWith("= " + String.valueOf(result)))
        {
            double a;
            if((screen.getText()).toString().endsWith("= " + String.valueOf(result)))
            {
                a = Double.valueOf(result);
                clear();
            }
            else
            {
                try {a = Double.valueOf(display);} catch(NumberFormatException e) { Log.d("onClickSin", "NumberFormatException"); toastIncorrectInput.show(); return; }
            }
            double result = Math.sin(Math.toRadians(a));
            if(result >= Double.MAX_VALUE)
            {
                clear();
                display = "∞";
                updateScreen();
                display = "";
            }
            else if (result <= Integer.MIN_VALUE)
            {
                clear();
                display = "-∞";
                updateScreen();
                display = "";
            }
            else
            {
                result = Double.valueOf(new BigDecimal(result, new MathContext(11, RoundingMode.HALF_UP)).toString());
                clear();
                display+= ""+result;
                updateScreen();
            }
        }
        else
            return;
    }
    public void onClickCos(View v)
    {
        if((!display.equals("") && currentOperator.equals(""))
                || (screen.getText()).toString().endsWith("= " + String.valueOf(result)))
        {
            double a;
            if((screen.getText()).toString().endsWith("= " + String.valueOf(result)))
            {
                a = Double.valueOf(result);
                clear();
            }
            else
            {
                try {a = Double.valueOf(display);} catch(NumberFormatException e) { Log.d("onClickCos", "NumberFormatException"); toastIncorrectInput.show(); return; }
            }
            double result = Math.cos(Math.toRadians(a));
            if(result >= Double.MAX_VALUE)
            {
                clear();
                display = "∞";
                updateScreen();
                display = "";
            }
            else if (result <= Integer.MIN_VALUE)
            {
                clear();
                display = "-∞";
                updateScreen();
                display = "";
            }
            else
            {
                result = Double.valueOf(new BigDecimal(result, new MathContext(11, RoundingMode.HALF_UP)).toString());
                clear();
                display+= ""+result;
                updateScreen();
            }
        }
        else
            return;
    }
    public void onClickTan(View v)
    {
        if((!display.equals("") && currentOperator.equals(""))
                || (screen.getText()).toString().endsWith("= " + String.valueOf(result)))
        {
            double a;
            if((screen.getText()).toString().endsWith("= " + String.valueOf(result)))
            {
                a = Double.valueOf(result);
                clear();
            }
            else
            {
                try {a = Double.valueOf(display);} catch(NumberFormatException e) { Log.d("onClickTan", "NumberFormatException"); toastIncorrectInput.show(); return; }
            }
            double result = Math.tan(Math.toRadians(a)); //Math.tan(Math.toRadians(Double.parseDouble(calculation())));
            if(result >= Double.MAX_VALUE)
            {
                clear();
                display = "∞";
                updateScreen();
                display = "";
            }
            else if (result <= Integer.MIN_VALUE)
            {
                clear();
                display = "-∞";
                updateScreen();
                display = "";
            }
            else
            {
                result = Double.valueOf(new BigDecimal(result, new MathContext(11, RoundingMode.HALF_UP)).toString());
                clear();
                display+= ""+result;
                updateScreen();
            }
        }
        else
            return;
    }
    //public void onClickXn(View v){return;}
    public void onClickSqrt(View v)
    {
        if((!display.equals("") && currentOperator.equals(""))
                || (screen.getText()).toString().endsWith("= " + String.valueOf(result)))
        {
            double a;
            if((screen.getText()).toString().endsWith("= " + String.valueOf(result)))
            {
                a = Double.valueOf(result);
                clear();
            }
            else
            {
                try {a = Double.valueOf(display);} catch(NumberFormatException e) { Log.d("onClickSqrt", "NumberFormatException"); toastIncorrectInput.show(); return; }
            }
            if(a<0)
            {
                toastCantSqrtThat.show();
                return;
            }

            double result = Math.sqrt(a);

            if (result <= Integer.MIN_VALUE)
            {
                clear();
                display = "-∞";
                updateScreen();
                display = "";
                return;
            }
            result = Double.valueOf(new BigDecimal(result, new MathContext(11, RoundingMode.HALF_UP)).toString());
            clear();
            display+= ""+result;
            updateScreen();

        }
        else
            return;
    }
    public void onClickX2(View v)
    {
        if((!display.equals("") && currentOperator.equals(""))
                || (screen.getText()).toString().endsWith("= " + String.valueOf(result)))
        {
            double a;
            if((screen.getText()).toString().endsWith("= " + String.valueOf(result)))
            {
                a = Double.valueOf(result);
                clear();
            }
            else
            {
                try {a = Double.valueOf(display);} catch(NumberFormatException e) { Log.d("onClickX2", "NumberFormatException"); toastIncorrectInput.show(); return; }
            }
            double result = Math.pow(a,2);
            if(result >= Double.MAX_VALUE)
            {
                clear();
                display = "∞";
                updateScreen();
                display = "";
            }
            else if (result <= Integer.MIN_VALUE)
            {
                clear();
                display = "-∞";
                updateScreen();
                display = "";
            }
            else
            {
                result = Double.valueOf(new BigDecimal(result, new MathContext(11, RoundingMode.HALF_UP)).toString());
                clear();
                display+= ""+result;
                updateScreen();
            }
        }
        else
            return;
    }

    //TODO: pack all above methods into this one \/
    public void onClickAdvancedOperation(View v)
    {

    }

    protected void initLayout()
    {
        screen = (TextView)findViewById(R.id.textView_adv);
    }
    private void updateScreen()
    {
        screen.setText(display);
    }
    //------------------------------------------

    @SuppressLint("ShowToast")
    private void initToasts()
    {
        toastError = Toast.makeText(this, "Equation incorrect, result changed to 0.", Toast.LENGTH_SHORT);
        toastTooBigNumber = Toast.makeText(this, "The number is too big :(!", Toast.LENGTH_SHORT);
        toastDivideByZero = Toast.makeText(this, "Dividing by 0 is prohibited!", Toast.LENGTH_SHORT);
        toastNoNumber = Toast.makeText(this, "Enter the number first!", Toast.LENGTH_SHORT);
        toastCantInsertDot = Toast.makeText(this, "You dont need this dot :)", Toast.LENGTH_SHORT);
        toastCantSqrtThat = Toast.makeText(this, "Can't square that!", Toast.LENGTH_SHORT);
        toastLogOneOrNegative = Toast.makeText(this,"Can't Log10 from 1 or negative!", Toast.LENGTH_SHORT);
        toastLnOneOrNegative = Toast.makeText(this,"Can't Ln from 1 or negative!", Toast.LENGTH_SHORT);
        toastIncorrectInput = Toast.makeText(this, "Incorrect input!", Toast.LENGTH_SHORT);
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
            case '^': return true;
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
        //Starts with pattern: non-digit character return 0.0
        //Pattern p = Pattern.compile("\\D+"); //ENDED HERE!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!

        double result;
        try
        {
            result = Double.valueOf(new BigDecimal(s).multiply(new BigDecimal(0.01), new MathContext(10, RoundingMode.HALF_UP)).toString());
        }catch(Error e)
        {
            Log.d("calculatePercent()", "Pin pon, exception!");
            return 0.0;
        }

        return result;
    }
    public String deleteLastChar(String str) {
        if (str != null && str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return str;
    }
    private void handleEAndEMinusError()
    {

        //CASE: First number ends with "E" or "E-"
        if(display.contains("E\n" + currentOperator))
            display = display.replaceFirst("E\n", "");
        if(display.contains("E-\n" + currentOperator))
            display = display.replaceFirst("E-\n", "");

        //CASE: Ends with "E" or "E-"
        if(display.endsWith("E"))
            display = display.substring(0, display.length()-1);
        if(display.endsWith("E-"))
            display = display.substring(0, display.length()-2);
    }
    private boolean temporaryHandleForWierdErrors(String aa, String bb, String op)
    {
        Log.d("temporaryHandle(...)", "Error occured");

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
        if(temporaryHandleForWierdErrors(aa, bb, op))
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
            case "^":
                double result = Math.pow(a,b);
                return Double.valueOf(new BigDecimal(result, new MathContext(11, RoundingMode.HALF_UP)).toString());
            case "+":
                return Double.valueOf((BDa.add(BDb, new MathContext(11, RoundingMode.HALF_UP))).toString());
            case "-":
                return Double.valueOf((BDa.subtract(BDb, new MathContext(11, RoundingMode.HALF_UP))).toString());
            case "x":
                return Double.valueOf((BDa.multiply(BDb, new MathContext(11, RoundingMode.HALF_UP))).toString());
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

        //Special case: ZERO
        if(display.equals("0"))
        {
            if(button.getText().equals("0"))
                return;
            else
            {
                display = "" + button.getText();
                updateScreen();
                return;
            }
        }

        if(display.endsWith(currentOperator+"\n0"))
        {
            if(button.getText().equals("0"))
                return;
            else
            {
                display = display.substring(0,display.length()-1) + button.getText();
                updateScreen();
                return;
            }
        }


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
        String buttonTxt;

        if(button.getText().equals("xⁿ"))
            buttonTxt = "^";
        else
            buttonTxt = button.getText().toString();

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
            if(isOperator(display.charAt(display.length()-2))
                    && !(display.charAt(display.length()-3) == 'E'))
            {
                System.out.println(display);
                display = display.substring(0,display.length()-2) + buttonTxt.charAt(0) + "\n";
                System.out.println(display);
                currentOperator = buttonTxt;
                updateScreen();
                return;
            }else
            {
                if(!getResult()) return;
                display = result;
                result = "";
            }
            currentOperator = buttonTxt;
        }

        display += "\n" + buttonTxt + "\n"; //TODO: If app fucks up, go to getResult() and delete replaceAll(...)
        currentOperator = buttonTxt;
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
            return;


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
        handleEAndEMinusError();

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

            //signChange is on
            if(currentOperator.equals("-") && display.charAt(0) == '-')
            {
                _display = display.substring(1);
                signChanged = true;
            }
            _display = _display.replaceAll("\n","");

            ///If there are E-
            if(_display.contains("E-"))
                _display = _display.replaceAll("E-","EMINUS");

            String [] temp = _display.split(Pattern.quote(currentOperator));
            switch(temp.length)
            {
                case 0:
                    clear();
                    updateScreen();
                    Log.d("onClickPercent error", "case 0");
                    break;
                case 1:
                    return;
                case 2:
                    temp[0] = temp[0].replaceAll("EMINUS", "E-");
                    temp[1] = temp[1].replaceAll("EMINUS", "E-");
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

        handleEAndEMinusError();



        //CASE: Equation not empty
        if(currentOperator.equals("")) return false;

        //CASE: first number is negative
        if(currentOperator.equals("-") && display.charAt(0) == '-')
        {
            display = display.substring(1);
            changeSign = true;
        }

        //in case of numbers like '5.0E-4'
        if(currentOperator.equals("-"))
        {
            display = display.replaceAll("E-", "EMINUS");
            Log.d("getResult() method", "changing E- to EMINUS");
        }

        //CASE: There are two numbers
        String[] operation = display.replaceAll("\\s+", "").split(Pattern.quote(currentOperator));
        if(operation.length<2) {
            display = display.replaceAll("EMINUS", "E-");
            return false;
        }

        operation[0] = operation[0].replaceAll("EMINUS", "E-");
        operation[1] = operation[1].replaceAll("EMINUS", "E-");

        //CASE: Result too big
        if(isOverMaxInt(operation[0], operation[1], currentOperator))
        {
            return false;
        }

        //CASE: Dividing by 0
        double secondNumber = Double.valueOf(operation[1]);
        if(currentOperator.equals("÷") && secondNumber == 0.0)
        {
            toastDivideByZero.show();
            display = display.replaceAll("EMINUS", "E-");
            return false;
        }

        //TODO: Check if needed
        display = display.replaceAll("EMINUS", "E-");

        if(changeSign)
        {
            display = "-" + display;
            result = "" + operate("-" + operation[0], operation[1], currentOperator);
        }
        else
            result = ""+operate(operation[0], operation[1], currentOperator);
        return true;
    }

    private boolean isOverMaxInt(String aa, String bb, String op)
    {
        double result = 0.0;

        if(aa.equals("") || bb.equals(""))
            return true;

        double a = Double.valueOf(aa);
        BigDecimal BDa = new BigDecimal(aa);

        double b = Double.valueOf(bb);
        BigDecimal BDb = new BigDecimal(bb);

        if(op.equals("^"))
            result = Math.pow(a,b);

        if(result >= Double.MAX_VALUE)
        {
            clear();
            display = "∞";
            updateScreen();
            display = "";
            return true;
        }
        else if (result <= Integer.MIN_VALUE)
        {
            clear();
            display = "-∞";
            updateScreen();
            display = "";
            return true;
        }
        else
            return false;


    }

    @SuppressLint("SetTextI18n")
    public void onClickEqual(View v)
    {
        //TODO: Split display to two TextViews and change color for result operation?
        if(display.equals("")) return;
        if(!getResult()) return;
        screen.setText(display + "\n= " + String.valueOf(result));
    }

}
