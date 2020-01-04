package com.example.obstatistics;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.obstatistics.Saving.Record;
import com.example.obstatistics.Saving.RecordAdapter;
import com.example.obstatistics.Saving.RecordViewModel;

import java.util.List;

public class CompetitionActivity extends AppCompatActivity {

    private static final String LOG_TAG = StatisticsActivity.class.getSimpleName();

    private RecordViewModel mRecordViewModel;
    private TextView mCountRecords;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_competition);
        Intent intent = getIntent();
        String registration = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        mRecordViewModel = ViewModelProviders.of(this).get(RecordViewModel.class);
        List<Record> records = mRecordViewModel.getRecordsByRegistration(registration);
        if (records.isEmpty()) {
            records.add(new Record("Nebyly nalezeny žádné záznamy pro toto registrační číslo"));
        }

        mCountRecords = (TextView) findViewById(R.id.numberOfRecords);
        mCountRecords.setText("Počet nalezených záznamů: " + records.size());
        RecordAdapter adapter = new RecordAdapter(this, records);
        listView = (ListView) findViewById(R.id.recordlist);
        listView.setAdapter(adapter);
    }
}
