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

public class DeleteAllActivity extends AppCompatActivity {

    private static final String LOG_TAG = DeleteAllActivity.class.getSimpleName();

    private RecordViewModel mRecordViewModel;
    private TextView mCountRecords;
    private View buttonDeleteAll;
    private View realyDeleteAll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_all);
        Intent intent = getIntent();
        String registration = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        mRecordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        List<Record> records = mRecordViewModel.getAllRecords();
        mCountRecords = (TextView) findViewById(R.id.countRecords);
        mCountRecords.setText("Počet nalezených záznamů: " + records.size());
        buttonDeleteAll = findViewById(R.id.buttonDelete);
        realyDeleteAll = findViewById(R.id.realyDelete);
        if (records.size() == 0) {
            buttonDeleteAll.setVisibility(View.GONE);
            realyDeleteAll.setVisibility(View.GONE);
        }
    }

    public void deleteItem(View view) {
        mRecordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        mRecordViewModel.deleteAll();
        Toast toast = Toast.makeText(this, R.string.toast_message_delete_all, Toast.LENGTH_SHORT);
        toast.show();
    }
}
