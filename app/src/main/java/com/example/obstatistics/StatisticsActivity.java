package com.example.obstatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.obstatistics.Dto.UserResultOutput;

public class StatisticsActivity extends AppCompatActivity {

    private ProgressBar spinner;
    private static final String LOG_TAG = StatisticsActivity.class.getSimpleName();
    private TextView mFirstNameText;
    private TextView mSecondNameText;
    private TextView mThirdNameText;
    private TextView mFourthNameText;
    StatisticsService statisticsService = new StatisticsService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        mFirstNameText = (TextView)findViewById(R.id.firstNameText);
        mSecondNameText = (TextView)findViewById(R.id.secondNameText);
        mThirdNameText = (TextView)findViewById(R.id.thirdNameText);
        mFourthNameText = (TextView)findViewById(R.id.fourthNameText);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView textView = findViewById(R.id.reg_number);
        textView.setText(message);
        spinner=(ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        createStatistics(message);
    }

    public void createStatistics(String registration) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        statisticsService.getOutputDto(
                networkInfo,
                registration,
                mFirstNameText,
                mSecondNameText,
                mThirdNameText,
                mFourthNameText,
                spinner);
        statisticsService.getUserResutlOutput();
    }
}
