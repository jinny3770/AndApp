package com.example.certa.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    Integer[] posterID = { R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4,
            R.drawable.movie5, R.drawable.movie6, R.drawable.movie7, R.drawable.movie8, R.drawable.movie9, R.drawable.movie10,
            R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4,
            R.drawable.movie5, R.drawable.movie6, R.drawable.movie7, R.drawable.movie8, R.drawable.movie9, R.drawable.movie10,
            R.drawable.movie1, R.drawable.movie2, R.drawable.movie3, R.drawable.movie4,
            R.drawable.movie5, R.drawable.movie6, R.drawable.movie7, R.drawable.movie8, R.drawable.movie9, R.drawable.movie10};
    String[] posterName = { "내부자들", "도리화가", "열정같은소리하고있네", "극적인하룻밤", "검은사제들",
            "헝거게임", "사우스포", "크림슨피크", "007스펙터", "하트오브더씨",
            "내부자들", "도리화가", "열정같은소리하고있네", "극적인하룻밤", "검은사제들",
            "헝거게임", "사우스포", "크림슨피크", "007스펙터", "하트오브더씨",
            "내부자들", "도리화가", "열정같은소리하고있네", "극적인하룻밤", "검은사제들",
            "헝거게임", "사우스포", "크림슨피크", "007스펙터", "하트오브더씨"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("GridView Poster");

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        final GridView grid = (GridView) findViewById(R.id.gridView);
        GridAdapter gridAdapter = new GridAdapter(this);
        grid.setAdapter(gridAdapter);

    }

    public class GridAdapter extends BaseAdapter {
        Context context;
        LayoutInflater inflater = null;
        View view;

        public GridAdapter(Context c) {
            context = c;
            inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        }

        public int getCount() {
            return posterID.length;
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int arg0) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {

            /*
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(100, 150));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);

            imageview.setImageResource(posterID[position]);
            */

            view = inflater.inflate(R.layout.grid, null);
            TextView tv = (TextView) view.findViewById(R.id.textView);
            ImageView imageview = (ImageView) view.findViewById(R.id.imgView);

            tv.setText(posterName[position]);
            imageview.setImageResource(posterID[position]);

            final int pos = position;
            imageview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(MainActivity.this, R.layout.dialog, null);

                    AlertDialog.Builder dlg = new AlertDialog.Builder(MainActivity.this);
                    ImageView ivposter = (ImageView) dialogView.findViewById(R.id.poster);
                    ivposter.setImageResource(posterID[pos]);
                    dlg.setTitle(posterName[pos]);
                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });

            return view;
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
