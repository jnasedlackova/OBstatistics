package com.example.obstatistics.Task;

import android.os.AsyncTask;
import android.widget.ProgressBar;

import com.example.obstatistics.Dto.UserResult;
import com.example.obstatistics.Dto.UserResultJson;
import com.example.obstatistics.NetworkUtils;
import com.example.obstatistics.StatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

public class FetchUserResult extends AsyncTask<String, Void, UserResult> {

    private static final String LOG_TAG =
            FetchUserResult.class.getSimpleName();
    static int countTasks = 0;
    int numberOfTasks;

    StatisticsService statisticsService;
    ProgressBar spinner;

    public FetchUserResult(StatisticsService statisticsService, ProgressBar spinner) {
        this.statisticsService = statisticsService;
        this.spinner = spinner;
    }

    @Override
    protected UserResult doInBackground(String... strings) {
        countTasks++;
        String string = NetworkUtils.getEventResults(strings[0]);
        String registration = strings[1];
        numberOfTasks = Integer.valueOf(strings[2]);
        ObjectMapper mapper = new ObjectMapper();
        List<UserResult> userResultList = new ArrayList<>();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            UserResultJson userResultJson = mapper.readValue(string, UserResultJson.class);
            for (UserResult value : userResultJson.getUserResults().values()) {
                userResultList.add(value);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        for (UserResult result : userResultList) {
             if (result.getRegistration().equals(registration.toUpperCase())) {
                 return result;
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(UserResult userResult) {
        statisticsService.readUserResult(userResult);
    }
}
