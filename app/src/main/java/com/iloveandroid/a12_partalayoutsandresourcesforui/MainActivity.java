package com.iloveandroid.a12_partalayoutsandresourcesforui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button btnToast, btnCount;

    private int mCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        textView = findViewById( R.id.txt1 );
        btnToast = findViewById( R.id.toast );
        btnCount = findViewById( R.id.btnCount );
    }

    public void Toast(View view) {
        Toast toast = Toast.makeText(this,"Hello toast", Toast.LENGTH_LONG);
        toast.show();
    }

    @SuppressLint("SetTextI18n")
    public void Count(View view) {
        mCount++;
        if (textView !=null){
            textView.setText(Integer.toString(mCount));
        }
    }
}