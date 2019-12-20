package com.example.obstatistics.Task;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.example.obstatistics.Dto.Competition;
import com.example.obstatistics.Dto.CompetitionJson;
import com.example.obstatistics.NetworkUtils;
import com.example.obstatistics.StatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class FetchCompetition extends AsyncTask<String, Void, Competition> {

    private static final String LOG_TAG = FetchCompetition.class.getSimpleName();
    static int countTasks = 0;
//    int numberOfTasks;

    StatisticsService statisticsService;
    ProgressBar spinner;

    public FetchCompetition(StatisticsService statisticsService, ProgressBar spinner) {
        this.statisticsService = statisticsService;
        this.spinner = spinner;
    }

    @Override
    protected Competition doInBackground(String... strings) {
        countTasks++;
        String string = NetworkUtils.getEvent(strings[0]);
        String classId = strings[1];
//        numberOfTasks = Integer.valueOf(strings[2]);
        ObjectMapper mapper = new ObjectMapper();
        List<Competition> competitionList = new ArrayList<>();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            CompetitionJson competitionJson = mapper.readValue(string, CompetitionJson.class);
            for (Competition value : competitionJson.getCompetitionClasses().getCompetitions().values()) {
                competitionList.add(value);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (Competition competition : competitionList) {
            if (competition.getClassId().toString().equals(classId)) {
                 return competition;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(Competition competition) {
//        if (numberOfTasks <= countTasks)
//            spinner.setVisibility(View.GONE);
        statisticsService.readCompetition(competition);
    }
}
