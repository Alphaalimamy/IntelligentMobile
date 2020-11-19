package com.iloveandroid.tip_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private EditText enterAmount;
    private SeekBar seekBar;
    private Button calculateButton;
    private TextView totalResult;
    private TextView seekBarTxt;
    private int seekBarPercentage;
    private float enteredBillFloat;
    private TextView totalBillTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        enterAmount = findViewById( R.id.billAmount );
        seekBar = findViewById( R.id.seekBar );
        calculateButton = (Button)findViewById(R.id.btnCalculate );
        totalResult = findViewById( R.id.resultID );
        seekBarTxt = findViewById(R.id.seekBarTextView);
        totalBillTv = findViewById( R.id.totalBillTextView );

        calculateButton.setOnClickListener(this);

        seekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekBarTxt.setText( seekBar.getProgress() + "% " );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                seekBarPercentage = seekBar.getProgress();

            }
        } );
    }

    public void onClick(View view){

        calculate();
    }

    public void calculate(){
        float result;

        if (!enterAmount.getText().toString().equals("")){
            enteredBillFloat =  Float.parseFloat(enterAmount.getText().toString());
            result = enteredBillFloat * seekBarPercentage /100;
            totalResult.setText("Tip Le: " + result );
            totalBillTv.setText( "Total bill Le : " + (enteredBillFloat + result) );
        } else {
            Toast.makeText(this, "Enter a bill amount", Toast.LENGTH_LONG).show();
        }


    }
}