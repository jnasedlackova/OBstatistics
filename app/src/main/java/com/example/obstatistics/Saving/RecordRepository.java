package com.example.obstatistics.Saving;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

public class RecordRepository {

    private RecordDao mRecordDao;
    private List<Record> mAllRecords;
    private List<Record> mRecordsByRegistration;

    RecordRepository(Application application) {
        RecordRoomDatabase db = RecordRoomDatabase.getDatabase(application);
        mRecordDao = db.recordDao();
        mAllRecords = mRecordDao.getAllRecords();
    }

    List<Record> getAllRecords() {
        return mAllRecords;
    }

    List<Record> getRecordsByRegistration(String registration) {
        return mRecordDao.getRecordsByRegistration(registration);
    }

    public void deleteAll() {
        mRecordDao.deleteAll();
    }

    public void deleteReg(String registration) {
        mRecordDao.deleteReg(registration);
    }

    public void insert (Record record) {
        new insertAsyncTask(mRecordDao).execute(record);
    }

    private static class insertAsyncTask extends AsyncTask<Record, Void, Void> {

        private RecordDao mAsyncTaskDao;

        insertAsyncTask(RecordDao dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Record... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
