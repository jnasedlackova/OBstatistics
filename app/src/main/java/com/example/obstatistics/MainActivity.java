package com.example.obstatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText mMessageRegistrationNumber;
    public static final String EXTRA_MESSAGE = "com.example.obstatistics.extra.MESSAGE";
    public static final int TEXT_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMessageRegistrationNumber = findViewById(R.id.editRegistration);
    }

    public void launchStatisticsActivity(View view) {
        Intent intent = new Intent(this, StatisticsActivity.class);
        String regNumber = mMessageRegistrationNumber.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, regNumber);
        startActivity(intent);
    }

    public void launchCompetitionsActivity(View view) {
        Intent intent = new Intent(this, CompetitionActivity.class);
        String regNumber = mMessageRegistrationNumber.getText().toString();
        intent.putExtra(EXTRA_MESSAGE, regNumber);
        startActivity(intent);
    }
}
