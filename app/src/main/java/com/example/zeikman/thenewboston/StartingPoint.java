package com.example.zeikman.thenewboston;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class StartingPoint extends AppCompatActivity {
    int counter;
    Button add, sub;
    TextView display;
    String totalText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starting_point);

        counter = 0;
        add = (Button) findViewById(R.id.bAdd);
        sub = (Button) findViewById(R.id.bSub);
        display = (TextView) findViewById(R.id.tvNumber);

        totalText = getString(R.string.total_text) + " " + Integer.toString(counter);
        display.setText(totalText);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter++;
                totalText = getString(R.string.total_text) + " " + Integer.toString(counter);
                display.setText(totalText);
            }
        });

        sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                counter--;
                totalText = getString(R.string.total_text) + " " + Integer.toString(counter);
                display.setText(totalText);
            }
        });
    }
}
