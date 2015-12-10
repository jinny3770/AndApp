package com.example.sora.myapplication;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    DatePicker picker;
    Button btn;
    EditText edit;

    String filename;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        //FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        /*fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        super.setTitle("Diary");

        picker = (DatePicker) findViewById(R.id.picker);
        btn = (Button) findViewById(R.id.but);
        edit = (EditText) findViewById(R.id.edit);

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        filename = makeFileName(year, month, day);
        readDiary(filename);


        picker.init(year, month, day, new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                filename = makeFileName(year, monthOfYear, dayOfMonth);
                readDiary(filename);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    FileOutputStream opf = openFileOutput(filename, Context.MODE_PRIVATE);
                    String str = edit.getText().toString();
                    opf.write(str.getBytes());
                    opf.close();
                    Toast.makeText(getApplicationContext(), filename+" is saved", Toast.LENGTH_SHORT).show();

                }catch (IOException e) {}

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public String makeFileName(int y, int m, int d){
        return Integer.toString(y) + "_" + Integer.toString(m) + "_" + Integer.toString(d) + ".txt";
    }

    public void readDiary(String filename){
        String diaryStr = null;
        FileInputStream inf;

        try{
            inf = openFileInput(filename);
            byte[] txt = new byte[500];
            inf.read(txt);
            inf.close();

            diaryStr = (new String(txt)).trim();
            edit.setText(diaryStr);
            btn.setText("Edit");

        }catch(IOException e) {
            edit.setText(null);
            edit.setHint("no diary");
            btn.setText("Save");
        }

    }

}
