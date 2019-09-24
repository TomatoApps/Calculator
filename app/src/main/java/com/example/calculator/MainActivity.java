package com.example.calculator;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public String TAG = "#######################";
    public EditText editText;

    public void cout(int str){
        Log.i(TAG, "" + str);
    }

    public void cout(char str){
        Log.i(TAG, "" + str);
    }

    public void cout(String str){
        Log.i(TAG, "" + str);
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar)findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        editText = (EditText) findViewById(R.id.expression);

        if (Build.VERSION.SDK_INT >= 11) {
            editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
            editText.setTextIsSelectable(true);
        } else {
            editText.setRawInputType(InputType.TYPE_NULL);
            editText.setFocusable(true);
        }

        ((Button)findViewById(R.id.btn7)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("7");
            }
        });
        ((Button)findViewById(R.id.btn8)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("8");
            }
        });
        ((Button)findViewById(R.id.btn9)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("9");
            }
        });
        ((Button)findViewById(R.id.btnDiv)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("/");
            }
        });

        ((Button)findViewById(R.id.btn4)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("4");
            }
        });
        ((Button)findViewById(R.id.btn5)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("5");
            }
        });
        ((Button)findViewById(R.id.btn6)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("6");
            }
        });
        ((Button)findViewById(R.id.btnMul)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("*");
            }
        });

        ((Button)findViewById(R.id.btn1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("1");
            }
        });
        ((Button)findViewById(R.id.btn2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("2");
            }
        });
        ((Button)findViewById(R.id.btn3)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("3");
            }
        });
        ((Button)findViewById(R.id.btnAdd)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("+");
            }
        });

        ((Button)findViewById(R.id.btnLeft)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("(");
            }
        });
        ((Button)findViewById(R.id.btn0)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("0");
            }
        });
        ((Button)findViewById(R.id.btnRight)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(")");
            }
        });
        ((Button)findViewById(R.id.btnSub)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("-");
            }
        });
        ((Button)findViewById(R.id.btnPercent)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("%");
            }
        });

        ((Button)findViewById(R.id.btnPeriod)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert(".");
            }
        });
        ((Button)findViewById(R.id.btnMore)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout)findViewById(R.id.mainPanel)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.secPanel)).setVisibility(View.VISIBLE);
            }
        });

        ((Button)findViewById(R.id.btnClear)).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.answer)).setTextColor(getResources().getColor(R.color.black, getTheme()));
                ((EditText)findViewById(R.id.expression)).setText("");
                ((TextView)findViewById(R.id.answer)).setText("0");
            }
        });
        ((Button)findViewById(R.id.btnDel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int start = Math.max(editText.getSelectionStart(), 0);
                int end = Math.max(editText.getSelectionEnd(), 0);

                if(start != 0)
                {
                    if(start == end)
                        editText.getText().replace(Math.min(start, end)-1, Math.max(start, end), "", 0, 0);
                    else
                        editText.getText().replace(Math.min(start, end), Math.max(start, end), "", 0, 0);
                }
            }
        });
        ((Button)findViewById(R.id.btnEquals)).setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {

                String sending = "", input = editText.getText().toString().replace("√", "sqrt");
                cout(input);

                if(input.indexOf('%') != -1) {
                    for (int i = 1; i < input.length(); i++) {
                        if (input.charAt(i) == '%') {
                            for(int j = i - 1; j >= 0; j--) {
                                if(input.charAt(j) != '.'){
                                    if(j == 0){
                                        cout("1");
                                        sending = "(" + input.substring(j, i) + "*0.01)" + input.substring(i+1, input.length());
                                        break;
                                    } else if(!Character.isDigit(input.charAt(j))){
                                        cout("2");
                                        if(i == input.length()){
                                            sending = input.substring(0, j) + "(" + input.substring(j+1, i) + "*0.01)";
                                        }
                                        else{
                                            cout("3");
                                            String a = input.substring(0, j+1);
                                            String b = input.substring(j+1, i);
                                            String c = input.substring(i+1, input.length());
                                            sending = a + "(" + b + "*0.01)" + c;
                                        }
                                        break;
                                    }
                                }
                            }
                            input = sending;
                        }
                    }
                }

                cout(sending);

                double answer = eval(input);
                String answerStr = answer + "";

                if(invalid)
                {
                    invalid = false;
                    lastInput = "";
                    ((TextView)findViewById(R.id.answer)).setTextColor(getResources().getColor(R.color.red, getTheme()));
                    ((TextView)findViewById(R.id.answer)).setText("Invalid Input");
                }
                else if(!answerStr.contains("E"))
                {
                    ((TextView)findViewById(R.id.answer)).setTextColor(getResources().getColor(R.color.black, getTheme()));

                    if(answer == Math.round(answer))
                        ((TextView)findViewById(R.id.answer)).setText(answerStr.substring(0, answerStr.length() - 2));
                    else
                        ((TextView)findViewById(R.id.answer)).setText(answerStr);
                }
                else
                {
                    ((TextView)findViewById(R.id.answer)).setTextColor(getResources().getColor(R.color.black, getTheme()));
                    ((TextView)findViewById(R.id.answer)).setText(String.format("%.3E",answer));
                }
            }
        });

        ((Button)findViewById(R.id.btnSqrt)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("√");
            }
        });
        ((Button)findViewById(R.id.btnExp)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("^");
            }
        });
        ((Button)findViewById(R.id.btnSIN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("sin ");
            }
        });
        ((Button)findViewById(R.id.btnCOS)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("cos ");
            }
        });
        ((Button)findViewById(R.id.btnTAN)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insert("tan ");
            }
        });
        ((Button)findViewById(R.id.btnBack)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LinearLayout)findViewById(R.id.mainPanel)).setVisibility(View.VISIBLE);
                ((LinearLayout)findViewById(R.id.secPanel)).setVisibility(View.GONE);
            }
        });
    }

    String lastInput = "";
    public void insert(String input){
        if(input == "/" || input == "*" || input == "+" || input == "-" || input == "√" || input == "^" || input == ".")
            if(input == lastInput)
                return;
        int start = Math.max(editText.getSelectionStart(), 0);
        int end = Math.max(editText.getSelectionEnd(), 0);
        editText.getText().replace(Math.min(start, end), Math.max(start, end), input, 0, input.length());
        lastInput = input;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.about:
                startActivity(new Intent(this, About.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    boolean invalid = false;
    public double eval(final String str) {
        return new Object() {
            int pos = -1, ch;

            void nextChar() {
                ch = (++pos < str.length()) ? str.charAt(pos) : -1;
            }

            boolean eat(int charToEat) {
                while (ch == ' ') nextChar();
                if (ch == charToEat) {
                    nextChar();
                    return true;
                }
                return false;
            }

            double parse() {
                nextChar();
                double x = parseExpression();
                if (pos < str.length())
                {
//                    Log.i("#################", "invalid input");
                    invalid = true;
//                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                return x;
            }

            // Grammar:
            // expression = term | expression `+` term | expression `-` term
            // term = factor | term `*` factor | term `/` factor
            // factor = `+` factor | `-` factor | `(` expression `)`
            //        | number | functionName factor | factor `^` factor

            double parseExpression() {
                double x = parseTerm();
                for (;;) {
                    if      (eat('+')) x += parseTerm(); // addition
                    else if (eat('-')) x -= parseTerm(); // subtraction
                    else return x;
                }
            }

            double parseTerm() {
                double x = parseFactor();
                for (;;) {
                    if      (eat('*')) x *= parseFactor(); // multiplication
                    else if (eat('/')) x /= parseFactor(); // division
                    else return x;
                }
            }

            double parseFactor() {
                if (eat('+')) return parseFactor(); // unary plus
                if (eat('-')) return -parseFactor(); // unary minus

                double x = 0;
                int startPos = this.pos;
                if (eat('(')) { // parentheses
                    x = parseExpression();
                    eat(')');
                } else if ((ch >= '0' && ch <= '9') || ch == '.') { // numbers
                    while ((ch >= '0' && ch <= '9') || ch == '.') nextChar();
                    try{
                        x = Double.parseDouble(str.substring(startPos, this.pos));
                    }catch (Exception e){e.printStackTrace();}
                } else if (ch >= 'a' && ch <= 'z') { // functions
                    while (ch >= 'a' && ch <= 'z') nextChar();
                    String func = str.substring(startPos, this.pos);
                    x = parseFactor();
                    if (func.equals("sqrt")) x = Math.sqrt(x);
                    else if (func.equals("sin")) x = Math.sin(Math.toRadians(x));
                    else if (func.equals("cos")) x = Math.cos(Math.toRadians(x));
                    else if (func.equals("tan")) x = Math.tan(Math.toRadians(x));
                    else{
//                        Log.i("#################", "invalid input");
                        invalid = true;
//                        throw new RuntimeException("Unknown function: " + func);
                    }
                } else {
//                    Log.i("#################", "invalid input");
                    invalid = true;
//                    throw new RuntimeException("Unexpected: " + (char)ch);
                }

                if (eat('^')) x = Math.pow(x, parseFactor()); // exponentiation

                return x;
            }
        }.parse();
    }
}
