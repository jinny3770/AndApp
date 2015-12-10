package com.example.com.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    String code;
    Button but;
    RadioGroup rg;
    EditText num1, num2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("First Activity");

        num1 = (EditText)findViewById(R.id.num1);
        num2 = (EditText)findViewById(R.id.num2);

        rg = (RadioGroup)findViewById(R.id.radioGroup);

        but = (Button)findViewById(R.id.but);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n1, n2;
                try {
                    n1 = Integer.parseInt(num1.getText().toString());
                    n2 = Integer.parseInt(num2.getText().toString());
                }catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "not number", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(getApplicationContext(), second.class);
                intent.putExtra("num1", n1);
                intent.putExtra("num2", n2);

                switch(rg.getCheckedRadioButtonId()){
                    case R.id.add : code = "add"; break;
                    case R.id.sub : code = "sub"; break;
                    case R.id.mul : code = "mul"; break;
                    case R.id.div :
                        if(n2 == 0) {
                            Toast.makeText(getApplicationContext(), "Divided by 0!", Toast.LENGTH_SHORT).show();
                            return;
                        }
                            code = "div"; break;
                    default: return;
                }

                intent.putExtra("cal", code);
                startActivityForResult(intent, 0);
            }
        });


    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if(resultCode == RESULT_OK){
            int hap = data.getIntExtra("hap", 0);
            Toast.makeText(getApplicationContext(), "result : " + hap, Toast.LENGTH_SHORT).show();
        }

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
}
