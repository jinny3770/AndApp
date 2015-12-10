package com.example.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by com on 2015-11-19.
 */
public class second extends Activity {

    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        setTitle("Second Activity");


        but = (Button)findViewById(R.id.but2);

        Intent inIntent = getIntent();

        final int n1 = inIntent.getIntExtra("num1", 0);
        final int n2 = inIntent.getIntExtra("num2", 0);
        final int hap;

        switch(inIntent.getStringExtra("cal").toString()){
            case "add" : hap = n1 + n2; break;
            case "sub" : hap = n1 - n2; break;
            case "mul" : hap = n1 * n2; break;
            case "div" : hap = n1 / n2; break;
            default: return;
        }

        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent outIntent = new Intent(getApplicationContext(), MainActivity.class);
                outIntent.putExtra("hap", hap);
                setResult(RESULT_OK, outIntent);
                finish();
            }
        });


    }
}
