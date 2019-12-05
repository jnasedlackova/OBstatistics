package com.example.obstatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;

public class StatisticsActivity extends AppCompatActivity {

    private TextView mFirstNameText;
    private TextView mSecondNameText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);

        mFirstNameText = (TextView)findViewById(R.id.firstNameText);
        mSecondNameText = (TextView)findViewById(R.id.secondNameText);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.reg_number);
        textView.setText(message);
        createStatistics(message);
    }

    public void createStatistics(String registration) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        if (networkInfo != null && networkInfo.isConnected()
                && registration.length() != 0) {
            new FetchUser(mFirstNameText, mSecondNameText).execute(registration);
            mSecondNameText.setText("");
            mFirstNameText.setText(R.string.loading);
        } else {
            if (registration.length() == 0) {
                mSecondNameText.setText("");
                mFirstNameText.setText(R.string.no_search_term);
            } else {
                mSecondNameText.setText("");
                mFirstNameText.setText(R.string.no_network);
            }
        }
    }
}
