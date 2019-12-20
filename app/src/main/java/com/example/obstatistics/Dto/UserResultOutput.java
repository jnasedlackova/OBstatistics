package com.example.obstatistics.Dto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class UserResultOutput {

    private List<CompetitionAndId> competitionAndClassIds;
    private Set<String> listOfClasses;
    private int countEvents;
    private int medalPlaces;
    private String time;
    private String loss;
    private int disk;


    public UserResultOutput() {
        this.competitionAndClassIds = new ArrayList<>();
        this.listOfClasses = new TreeSet<>();
        this.countEvents = 0;
        this.medalPlaces = 0;
        this.disk = 0;
    }

    public List<CompetitionAndId> getCompetitionAndClassIds() {
        return competitionAndClassIds;
    }

    public void setCompetitionAndClassIds(List<CompetitionAndId> competitionAndClassIds) {
        this.competitionAndClassIds = competitionAndClassIds;
    }

    public Set<String> getListOfClasses() {
        return listOfClasses;
    }

    public void setListOfClasses(Set<String> listOfClasses) {
        this.listOfClasses = listOfClasses;
    }

    public int getCountEvents() {
        return countEvents;
    }

    public void setCountEvents(int countEvents) {
        this.countEvents = countEvents;
    }

    public int getMedalPlaces() {
        return medalPlaces;
    }

    public void setMedalPlaces(int medalPlaces) {
        this.medalPlaces = medalPlaces;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    public int getDisk() {
        return disk;
    }

    public void setDisk(int disk) {
        this.disk = disk;
    }

    @Override
    public String toString() {
        return "UserResultOutput{" +
                "competitionAndClassIds=" + competitionAndClassIds +
                ", listOfClasses=" + listOfClasses +
                ", countEvents=" + countEvents +
                ", medalPlaces=" + medalPlaces +
                '}';
    }
}
