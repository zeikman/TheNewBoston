package com.example.zeikman.thenewboston;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by zeikman on 12/21/15.
 */
public class SQLiteExample extends Activity implements View.OnClickListener {
    Button sqlUpdate, sqlView, sqlModify, sqlGetInfo, sqlDelete;
    EditText sqlName, sqlHotness, sqlRow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sqliteexample);

        sqlUpdate = (Button) findViewById(R.id.bSQLUpdate);
        sqlView= (Button) findViewById(R.id.bSQLView);
        sqlName = (EditText) findViewById(R.id.etSQLName);
        sqlHotness= (EditText) findViewById(R.id.etSQLHotness);

        sqlUpdate.setOnClickListener(this);
        sqlView.setOnClickListener(this);

        sqlRow = (EditText) findViewById(R.id.etSQLrowInfo);
        sqlModify = (Button) findViewById(R.id.bSQLmodify);
        sqlGetInfo = (Button) findViewById(R.id.bgetInfo);
        sqlDelete = (Button) findViewById(R.id.bSQLdelete);

        sqlModify.setOnClickListener(this);
        sqlGetInfo.setOnClickListener(this);
        sqlDelete.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bSQLUpdate:
                boolean didItWork = true;
                try {
                    String name = sqlName.getText().toString();
                    String hotness = sqlHotness.getText().toString();

                    HotOrNot entry = new HotOrNot(SQLiteExample.this);
                    entry.open();
                    entry.createEntry(name, hotness);
                    entry.close();
                } catch (Exception e) {
                    didItWork = false;
                    // e.printStackTrace();
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                } finally {
                    if (didItWork) {
                        Dialog d = new Dialog(this);
                        d.setTitle("Heck Yea!");
                        TextView tv = new TextView(this);
                        tv.setText("Success");
                        d.setContentView(tv);
                        d.show();
                    }
                }

                break;
            case R.id.bSQLView:
                Intent i = new Intent("com.example.zeikman.thenewboston.SQLVIEW");
                startActivity(i);
                break;
            case R.id.bgetInfo:
                try {
                    String s = sqlRow.getText().toString();
                    long l = Long.parseLong(s);
                    HotOrNot hon = new HotOrNot(this);
                    hon.open();
                    String returnedName = hon.getName(l);
                    String returnedHotness = hon.getHotness(l);
                    hon.close();

                    sqlName.setText(returnedName);
                    sqlHotness.setText(returnedHotness);
                } catch (Exception e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
            case R.id.bSQLmodify:
                try {
                    String mName = sqlName.getText().toString();
                    String mHotness = sqlHotness.getText().toString();
                    String sRow = sqlRow.getText().toString();
                    long lRow = Long.parseLong(sRow);

                    HotOrNot ex = new HotOrNot(this);
                    ex.open();
                    ex.updateEntry(lRow, mName, mHotness);
                    ex.close();
                } catch (Exception e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
            case R.id.bSQLdelete:
                try {
                    String sRow1 = sqlRow.getText().toString();
                    long lRow1 = Long.parseLong(sRow1);
                    HotOrNot ex1 = new HotOrNot(this);
                    ex1.open();
                    ex1.deleteEntry(lRow1);
                    ex1.close();
                } catch (Exception e) {
                    String error = e.toString();
                    Dialog d = new Dialog(this);
                    d.setTitle("Dang it!");
                    TextView tv = new TextView(this);
                    tv.setText(error);
                    d.setContentView(tv);
                    d.show();
                }
                break;
        }
    }
}
