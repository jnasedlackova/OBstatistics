package com.example.obstatistics;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.obstatistics.Saving.Record;
import com.example.obstatistics.Saving.RecordViewModel;

import java.util.List;

public class DeleteRegActivity extends AppCompatActivity {

    private static final String LOG_TAG = DeleteRegActivity.class.getSimpleName();

    private RecordViewModel mRecordViewModel;
    private TextView mCountRecords;
    private String registration;
    private View buttonDeleteReg;
    private View realyDeleteReg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_reg);
        Intent intent = getIntent();
        registration = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mRecordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        List<Record> records = mRecordViewModel.getRecordsByRegistration(registration);
        mCountRecords = (TextView) findViewById(R.id.countRecordsReg);
        mCountRecords.setText("Celkem " + records.size() + " nalezených záznamů pro r.č. " + registration.toUpperCase() );
        buttonDeleteReg = findViewById(R.id.buttonDeleteReg);
        realyDeleteReg = findViewById(R.id.realyDeleteReg);
        if (records.size() == 0) {
            buttonDeleteReg.setVisibility(View.GONE);
            realyDeleteReg.setVisibility(View.GONE);
        }
    }

    public void deleteItem(View view) {
        mRecordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        mRecordViewModel.deleteReg(registration);
        Toast toast = Toast.makeText(this, R.string.toast_message_delete_reg, Toast.LENGTH_SHORT);
        toast.show();
    }
}
