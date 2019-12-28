package com.example.obstatistics.Task;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.obstatistics.Dto.NonActiveUserJson;
import com.example.obstatistics.Dto.User;
import com.example.obstatistics.NetworkUtils;
import com.example.obstatistics.R;
import com.example.obstatistics.StatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.ref.WeakReference;

public class FetchNonactiveUser extends AsyncTask<String, Void, User> {

    private static final String LOG_TAG =
            FetchNonactiveUser.class.getSimpleName();

    private WeakReference<TextView> mFirstNameText;
    private WeakReference<TextView> mSecondNameText;

    StatisticsService statisticsService;

    public FetchNonactiveUser(TextView firstNameText, TextView secondNameText, StatisticsService statisticsService) {
        this.mFirstNameText = new WeakReference<>(firstNameText);
        this.mSecondNameText = new WeakReference<>(secondNameText);
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
            if (firstName != null && secondName != null) {
                mFirstNameText.get().setText(firstName);
                mSecondNameText.get().setText(secondName);
            } else {
                mFirstNameText.get().setText(R.string.no_result);
                mSecondNameText.get().setText("");
            }
            statisticsService.readUserResult(user, statisticsService.userFound);
            Log.d(LOG_TAG, "I am in FetchUse: " + user.toString());
        }
    }
}
