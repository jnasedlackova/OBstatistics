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

public class StatisticsActivity extends AppCompatActivity {

    private static final String LOG_TAG = StatisticsActivity.class.getSimpleName();

    private TextView mNameText;
    private TextView mChipText;
    private TextView mCountCompetitionText;
    private TextView mMoneyPaidText;
    private TextView mTotalTimeText;
    private TextView mTotalLossText;
    private TextView mMedalPlacesText;
    private TextView mDiskPlacesText;
    private TextView mCathegoriesText;
    private TextView mTotalDistanceText;
    private TextView mTotalElevationText;
    private TextView mTotalControlNumberText;
    private ProgressBar spinner;
    StatisticsService statisticsService = new StatisticsService();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistics);
        mNameText = (TextView)findViewById(R.id.nameText);
        mChipText = (TextView)findViewById(R.id.chipText);
        mCountCompetitionText = (TextView)findViewById(R.id.countCompetitionText);
        mMoneyPaidText = (TextView)findViewById(R.id.moneyPaidText);
        mTotalTimeText = (TextView) findViewById(R.id.totalTimeText);
        mTotalLossText = (TextView) findViewById(R.id.totalLossText);
        mMedalPlacesText = (TextView) findViewById(R.id.medalPlacesText);
        mDiskPlacesText = (TextView) findViewById(R.id.diskPlacesText);
        mCathegoriesText = (TextView) findViewById(R.id.cathegoriesText);
        mTotalDistanceText = (TextView) findViewById(R.id.totalDistanceText);
        mTotalElevationText = (TextView) findViewById(R.id.totalElevationText);
        mTotalControlNumberText = (TextView) findViewById(R.id.totalControlNumberText);

        Intent intent = getIntent();
        String registration = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        TextView registrationTextView = findViewById(R.id.reg_number);
        registrationTextView.setText(registration.toUpperCase());
        spinner=(ProgressBar)findViewById(R.id.progressBar1);
        spinner.setVisibility(View.VISIBLE);
        createStatistics(registration);
    }

    public void createStatistics(String registration) {
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = null;
        if (connMgr != null) {
            networkInfo = connMgr.getActiveNetworkInfo();
        }
        statisticsService.startStatsCounting(
                networkInfo,
                registration,
                mNameText,
                mChipText,
                mCountCompetitionText,
                mMoneyPaidText,
                mTotalTimeText,
                mTotalLossText,
                mMedalPlacesText,
                mDiskPlacesText,
                mCathegoriesText,
                mTotalDistanceText,
                mTotalElevationText,
                mTotalControlNumberText,
                spinner);
        statisticsService.getUserResutlOutput();
    }
}
