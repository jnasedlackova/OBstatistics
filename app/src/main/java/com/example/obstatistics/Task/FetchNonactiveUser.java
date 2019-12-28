package com.example.obstatistics.Task;

import android.os.AsyncTask;
import android.view.View;
import android.widget.ProgressBar;
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

    private WeakReference<TextView> mNameText;
    private WeakReference<TextView> mChipText;

    StatisticsService statisticsService;
    ProgressBar spinner;

    public FetchNonactiveUser(TextView nameText, TextView chipText, StatisticsService statisticsService, ProgressBar spinner) {
        this.mNameText = new WeakReference<>(nameText);
        this.mChipText = new WeakReference<>(chipText);
        this.statisticsService = statisticsService;
        this.spinner = spinner;
    }

    @Override
    protected User doInBackground(String... strings) {
        String registration = strings[0];
        if (strings[1].equals("2013")) {
           statisticsService.nonActiveUserFinished = true;
        }
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
        if (statisticsService.nonActiveUserFinished) {
            spinner.setVisibility(View.GONE);
            mChipText.get().setText(R.string.older_registration);
        }
    }
}
