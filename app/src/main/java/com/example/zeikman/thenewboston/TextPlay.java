package com.example.zeikman.thenewboston;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

import java.util.Random;

/**
 * Created by zeikman on 12/19/15.
 */
public class TextPlay extends Activity implements View.OnClickListener {
    Button checkCmd;
    ToggleButton passTog;
    EditText input;
    TextView display;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.text);

        setVariables();

        passTog.setOnClickListener(this);
        checkCmd.setOnClickListener(this);
    }

    private void setVariables() {
        checkCmd = (Button) findViewById(R.id.bResults);
        passTog = (ToggleButton) findViewById(R.id.tbPassword);
        input = (EditText) findViewById(R.id.etCommands);
        display = (TextView) findViewById(R.id.tvResults);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bResults:
                String check = input.getText().toString();

                if (check.equals("left")) {
                    display.setGravity(Gravity.START);
                }
                else if (check.equals("center")) {
                    display.setGravity(Gravity.CENTER);
                }
                else if (check.equals("right")) {
                    display.setGravity(Gravity.END);
                }
                else if (check.equals("blue")) {
                    display.setTextColor(Color.BLUE);
                }
                else if (check.equals("WTF")) {
                    Random crazy = new Random();
                    display.setText("WTF !!!");
                    display.setTextSize(crazy.nextInt(75));
                    display.setTextColor(Color.rgb(crazy.nextInt(255), crazy.nextInt(255), crazy.nextInt(255)));

                    switch (crazy.nextInt(3)) {
                        case 0:
                            display.setGravity(Gravity.START);
                            break;
                        case 1:
                            display.setGravity(Gravity.CENTER);
                            break;
                        case 2:
                            display.setGravity(Gravity.END);
                            break;
                    }
                }
                else {
                    display.setText("invalid");
                    display.setGravity(Gravity.CENTER);
                }
                break;
            case R.id.tbPassword:
                if (passTog.isChecked()) {
                    input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }
                else {
                    input.setInputType(InputType.TYPE_CLASS_TEXT);
                }
                break;
        }
    }
}
