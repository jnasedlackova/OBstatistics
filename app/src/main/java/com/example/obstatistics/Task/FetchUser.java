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

    private WeakReference<TextView> mFirstNameText;
    private WeakReference<TextView> mSecondNameText;

    StatisticsService statisticsService;

    public FetchUser(TextView firstNameText, TextView secondNameText, StatisticsService statisticsService) {
        this.mFirstNameText = new WeakReference<>(firstNameText);
        this.mSecondNameText = new WeakReference<>(secondNameText);
        this.statisticsService = statisticsService;
    }

    @Override
    protected User doInBackground(String... strings) {
        String string = NetworkUtils.getUser(strings[0]);
        ObjectMapper mapper = new ObjectMapper();
        User user = null;
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            UserJson userJson = mapper.readValue(string, UserJson.class);
            user = userJson.getUser();
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
            if (firstName != null && secondName != null) {
                mFirstNameText.get().setText(firstName);
                mSecondNameText.get().setText(secondName);
            } else {
                mFirstNameText.get().setText(R.string.no_result);
                mSecondNameText.get().setText("");
            }
            statisticsService.readUserResult(user, 1);
        } else {
            mFirstNameText.get().setText(R.string.no_registration);
            mSecondNameText.get().setText("R.string.older_registration");
            statisticsService.getNonActiveUserInfo();
        }
    }
}
