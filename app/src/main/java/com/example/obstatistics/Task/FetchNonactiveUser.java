package com.example.obstatistics.Task;

import android.os.AsyncTask;
import android.widget.TextView;

import com.example.obstatistics.Dto.NonActiveUserJson;
import com.example.obstatistics.Dto.User;
import com.example.obstatistics.NetworkUtils;
import com.example.obstatistics.StatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.ref.WeakReference;

public class FetchNonactiveUser extends AsyncTask<String, Void, User> {

    private static final String LOG_TAG =
            FetchNonactiveUser.class.getSimpleName();

    private WeakReference<TextView> mNameText;

    StatisticsService statisticsService;

    public FetchNonactiveUser(TextView firstNameText, StatisticsService statisticsService) {
        this.mNameText = new WeakReference<>(firstNameText);
        this.statisticsService = statisticsService;
    }

    @Override
    protected User doInBackground(String... strings) {
        String registration = strings[0];
        String string = NetworkUtils.getNonActiveUser(strings[0], strings[1]);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            NonActiveUserJson userJson = mapper.readValue(string, NonActiveUserJson.class);
            for (User user : userJson.getUsers().values()) {
                if (user.getRegistration().equals(registration.toUpperCase())) {
                    return user;
                }
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(User user) {
        if (user != null) {
            statisticsService.userFound+=1;
            String firstName = user.getFirstName();
            String secondName = user.getSecondName();
            String yearOfBirth = statisticsService.calculateBirth(user.getRegistration());
            mNameText.get().setText(firstName + " " + secondName + ", rok narození: " + yearOfBirth);
            statisticsService.readUserResult(user, statisticsService.userFound);
        }
    }
}
