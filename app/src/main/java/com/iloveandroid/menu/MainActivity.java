package com.iloveandroid.menu;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button alertButton;
    private AlertDialog.Builder dialog;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        alertButton = findViewById( R.id.btnAlert );

        alertButton.setOnClickListener( v -> {
            dialog = new AlertDialog.Builder(MainActivity.this);
            dialog.setTitle(getResources().getString(R.string.title_alert));
            dialog.setMessage(getResources().getString(R.string.message));
            dialog.setCancelable(false);

            dialog.setPositiveButton( getResources().getString( R.string.yes ), (dialog, which) -> MainActivity.this.finish() );

            dialog.setNegativeButton( getResources().getString( R.string.no ), (dialog, which) -> dialog.cancel() );
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();

        } );
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.new_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.settings:
                Toast.makeText(this, "Home is Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.home:
                Toast.makeText(this, "Settings is Selected", Toast.LENGTH_LONG).show();
                return true;
            case R.id.add:
                Toast.makeText(this, "Plus is Selected", Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected( item );
        }

    }
}