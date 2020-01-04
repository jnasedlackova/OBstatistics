package com.example.obstatistics.Saving;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.text.SimpleDateFormat;
import java.util.Date;

@Entity(tableName = "record_table")
public class Record {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private Long id;

    private String dateOfRecord;
    private String registration;
    private String identity;
    private String chip;
    private String moneyPaid;
    private String countCompetition;
    private String totalTime;
    private String totalLoss;
    private String medalPlaces;
    private String diskEvents;
    private String cathegories;
    private String totalDistance;
    private String totalClimbing;
    private String totalControls;

    public Record() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
        dateOfRecord = sdf.format(new Date());
    }

    public Record(String text) {
        this.dateOfRecord = text;
    }

    @NonNull
    public Long getId() {
        return id;
    }

    public void setId(@NonNull Long id) {
        this.id = id;
    }

    public String getDateOfRecord() {
        return dateOfRecord;
    }

    public void setDateOfRecord(String dateOfRecord) {
        this.dateOfRecord = dateOfRecord;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getChip() {
        return chip;
    }

    public void setChip(String chip) {
        this.chip = chip;
    }

    public String getMoneyPaid() {
        return moneyPaid;
    }

    public void setMoneyPaid(String moneyPaid) {
        this.moneyPaid = moneyPaid;
    }

    public String getCountCompetition() {
        return countCompetition;
    }

    public void setCountCompetition(String countCompetition) {
        this.countCompetition = countCompetition;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getTotalLoss() {
        return totalLoss;
    }

    public void setTotalLoss(String totalLoss) {
        this.totalLoss = totalLoss;
    }

    public String getMedalPlaces() {
        return medalPlaces;
    }

    public void setMedalPlaces(String medalPlaces) {
        this.medalPlaces = medalPlaces;
    }

    public String getDiskEvents() {
        return diskEvents;
    }

    public void setDiskEvents(String diskEvents) {
        this.diskEvents = diskEvents;
    }

    public String getCathegories() {
        return cathegories;
    }

    public void setCathegories(String cathegories) {
        this.cathegories = cathegories;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getTotalClimbing() {
        return totalClimbing;
    }

    public void setTotalClimbing(String totalClimbing) {
        this.totalClimbing = totalClimbing;
    }

    public String getTotalControls() {
        return totalControls;
    }

    public void setTotalControls(String totalControls) {
        this.totalControls = totalControls;
    }
}
