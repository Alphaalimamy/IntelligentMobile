package com.iloveandroid.whowrote;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public EditText mBookInput;
    public TextView mTitleText;
    public TextView mAuthorText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mBookInput = findViewById(R.id.bookInput);
        mTitleText = findViewById(R.id.titleText);
        mAuthorText = findViewById(R.id.authorText);
    }

    public void searchBooks(View view) {
        // Get the search string from the input field.
        String queryString = mBookInput.getText().toString();

        // Hide the keyboard when the button is pushed.
        InputMethodManager inputManager = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        if (inputManager != null) {
            inputManager.hideSoftInputFromWindow(view.getWindowToken(),
                    InputMethodManager.HIDE_NOT_ALWAYS);
        }

        // Check the status of the network connection.
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService( Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = null;
        if (connMgr != null) networkInfo = connMgr.getActiveNetworkInfo();

        // If the network is available, connected, and the search field
        // is not empty, start a FetchBook AsyncTask.
        // Otherwise update the TextView to tell the user there is no
        // connection, or no search term.
        if (networkInfo.isConnected()) {
            if (networkInfo != null && queryString.length() != 0) {
                new FetchBook( mTitleText, mAuthorText ).execute( queryString );
                mAuthorText.setText( "" );
                mTitleText.setText( R.string.loading );
            } else {
                if (queryString.length() == 0) {
                    mAuthorText.setText( "" );
                    mTitleText.setText( R.string.no_search_term );
                } else {
                    mAuthorText.setText( "" );
                    mTitleText.setText( R.string.no_network );
                }
            }
        } else {
            if (queryString.length() == 0) {
                mAuthorText.setText( "" );
                mTitleText.setText( R.string.no_search_term );
            } else {
                mAuthorText.setText( "" );
                mTitleText.setText( R.string.no_network );
            }
        }
    }
}