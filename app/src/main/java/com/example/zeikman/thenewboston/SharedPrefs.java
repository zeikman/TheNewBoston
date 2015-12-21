package com.example.zeikman.thenewboston;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by zeikman on 12/21/15.
 */
public class SharedPrefs extends Activity implements View.OnClickListener {
    EditText sharedData;
    TextView dataResults;
    public static String filename = "MySharedString";
    SharedPreferences someData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sharedpreferences);
        setupVariables();
        someData = getSharedPreferences(filename, 0);
    }

    private void setupVariables() {
        Button save = (Button) findViewById(R.id.bSaveData);
        Button load = (Button) findViewById(R.id.bLoadData);
        sharedData = (EditText) findViewById(R.id.etSharedPrefs);
        dataResults = (TextView) findViewById(R.id.tvLoadSharedPrefs);

        save.setOnClickListener(this);
        load.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSaveData:
                String stringData = sharedData.getText().toString();
                SharedPreferences.Editor editor = someData.edit();
                editor.putString("sharedString", stringData);
                editor.commit();
                break;
            case R.id.bLoadData:
                someData = getSharedPreferences(filename, 0);
                String dataReturned = someData.getString("sharedString", "Couldn't Load Data");
                dataResults.setText(dataReturned);
                break;
        }
    }
}
