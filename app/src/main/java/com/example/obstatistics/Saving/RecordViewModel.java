package com.example.obstatistics.Saving;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;

import java.util.List;

public class RecordViewModel extends AndroidViewModel {

    private RecordRepository mRepository;

    private List<Record> mAllRecords;

    public RecordViewModel (Application application) {
        super(application);
        mRepository = new RecordRepository(application);
        mAllRecords = mRepository.getAllRecords();
    }

    public List<Record> getRecordsByRegistration(String registration) {
        return mRepository.getRecordsByRegistration(registration);
    }

    public List<Record> getAllRecords() { return mAllRecords; }

    public void insert(Record record) { mRepository.insert(record); }

    public void deleteAll() {
        mRepository.deleteAll();
    }

    public void deleteReg(String registration) {
        mRepository.deleteReg(registration);
    }


}
