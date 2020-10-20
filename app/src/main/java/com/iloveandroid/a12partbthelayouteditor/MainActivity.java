package com.iloveandroid.a12partbthelayouteditor;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private int mcount = 0, reset = 0;
    private TextView mShowCount;
    private Button button_zero, button_count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        mShowCount = (TextView) findViewById(R.id.show_count);
        button_count = (Button)findViewById(R.id.button_count);
        button_zero = (Button)findViewById(R.id.button_zero);

    }

    @SuppressLint("SetTextI18n")
    public void countUp(View view) {
        ++mcount;
        if (mShowCount != null)
            mShowCount.setText(Integer.toString(mcount));
        button_zero.setBackgroundColor(getResources().getColor(R.color.pink));
    }

    public void showToast(View view) {
        Toast.makeText(this, "", Toast.LENGTH_SHORT).show();

    }

    @SuppressLint("SetTextI18n")
    public void setZero(View view) {
        mShowCount.setText(Integer.toString(mcount=0));
        button_zero.setBackgroundColor( Color.GRAY);
    }
}