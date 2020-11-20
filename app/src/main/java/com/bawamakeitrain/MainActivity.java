package com.bawamakeitrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button showTag;
    private Button showMoney;
    private TextView money;
    private int mCount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        showMoney = findViewById(R.id.btnMake);
        showTag = findViewById(R.id.btnShow);
        money = findViewById( R.id.moneyText );


        showTag.setOnClickListener(this);
        showMoney.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.btnMake:
                NumberFormat numberFormat = NumberFormat.getCurrencyInstance();

                mCount +=1000;
                String mMoney = String.valueOf(numberFormat.format(mCount));
                money.setText(mMoney);

                switch (mCount){
                    case 20000 :
                        money.setTextColor(Color.RED);
                        break;
                    case 40000 :
                        money.setTextColor(Color.GREEN);
                        break;
                    case 50000 :
                        money.setTextColor(Color.MAGENTA);
                        break;
                    default:
                        break;
                }

                Log.d("Make", "Make It Rain: "+ numberFormat.format(mCount));

                break;

            case R.id.btnShow:

                Toast.makeText(getApplicationContext(), R.string.toast, Toast.LENGTH_SHORT).show();
                Log.d("Show", "Show Tag");
        }
    }
}