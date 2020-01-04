package com.example.obstatistics;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.obstatistics.Saving.RecordViewModel;

public class MainActivity extends AppCompatActivity {

    private EditText mMessageRegistrationNumber;
    public static final String EXTRA_MESSAGE = "com.example.obstatistics.extra.MESSAGE";
    private RecordViewModel mRecordViewModel;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch(item.getItemId()) {
            case R.id.item1:
                Intent launchDeleteAllIntent = new Intent(MainActivity.this, DeleteAllActivity.class);
                startActivityForResult(launchDeleteAllIntent, 0);
                break;
            case R.id.item2:
                Intent launchRegDeleteIntent = new Intent(MainActivity.this, DeleteRegActivity.class);
                String regNumber = mMessageRegistrationNumber.getText().toString();
                launchRegDeleteIntent.putExtra(EXTRA_MESSAGE, regNumber);
                startActivityForResult(launchRegDeleteIntent, 0);
                break;
            default:
                return super.onOptionsItemSelected(item);
        }

        return true;
    }
}
