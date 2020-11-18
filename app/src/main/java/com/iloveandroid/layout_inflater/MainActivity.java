package com.iloveandroid.layout_inflater;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
    }

    public void showInfo(View view) {

        LayoutInflater inflater = getLayoutInflater();
        View v = inflater.inflate(R.layout.showlayout, null);

        TextView textView = v.findViewById(R.id.welcome);
        textView.setTextColor( Color.BLUE);
        Toast toast = new Toast(getApplicationContext());
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER_VERTICAL, 0,0);
        toast.show();
    }
}