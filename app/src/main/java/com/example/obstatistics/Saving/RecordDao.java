package com.example.obstatistics.Saving;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RecordDao {

    @Insert
    void insert(Record record);

    @Delete
    void delete(Record record);

    @Query("DELETE FROM record_table")
    void deleteAll();

    @Query("SELECT * from record_table ORDER BY registration ASC")
    List<Record> getAllRecords();

    @Query("SELECT * from record_table WHERE registration LIKE :reg")
    List<Record> getRecordsByRegistration(String reg);
}
