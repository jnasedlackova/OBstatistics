package com.example.obstatistics.Task;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.obstatistics.Dto.UserJson;
import com.example.obstatistics.Dto.User;
import com.example.obstatistics.NetworkUtils;
import com.example.obstatistics.R;
import com.example.obstatistics.StatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.ref.WeakReference;

public class FetchUser extends AsyncTask<String, Void, User> {

    private static final String LOG_TAG =
            FetchUser.class.getSimpleName();

    private WeakReference<TextView> mNameText;

    StatisticsService statisticsService;

    public FetchUser(TextView firstNameText, StatisticsService statisticsService) {
        this.mNameText = new WeakReference<>(firstNameText);
        this.statisticsService = statisticsService;
    }

    @Override
    protected User doInBackground(String... strings) {
        String registration = strings[0];
        String string = NetworkUtils.getUser(registration);
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            UserJson userJson = mapper.readValue(string, UserJson.class);
            user = userJson.getUser();
            user.setRegistration(registration);
            Log.d(LOG_TAG, user.toString());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    protected void onPostExecute(User user) {
        if (user != null) {
            String firstName = user.getFirstName();
            String secondName = user.getSecondName();
            String yearOfBirth = statisticsService.calculateBirth(user.getRegistration());
            mNameText.get().setText(firstName + " " + secondName + ", rok narození: " + yearOfBirth);
            statisticsService.readUserResult(user, 1);
        } else {
            mNameText.get().setText(R.string.no_registration);
            statisticsService.getNonActiveUserInfo();
        }
    }
}
