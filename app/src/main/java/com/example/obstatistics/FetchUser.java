package com.example.obstatistics;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.obstatistics.Dto.InputJson;
import com.example.obstatistics.Dto.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.ref.WeakReference;

public class FetchUser extends AsyncTask<String, Void, String> {

    private static final String LOG_TAG =
            NetworkUtils.class.getSimpleName();

    private WeakReference<TextView> mFirstNameText;
    private WeakReference<TextView> mSecondNameText;

    FetchUser(TextView firstNameText, TextView secondNameText) {
        this.mFirstNameText = new WeakReference<>(firstNameText);
        this.mSecondNameText = new WeakReference<>(secondNameText);
    }

    @Override
    protected String doInBackground(String... strings) {
        return NetworkUtils.getUser(strings[0]);
    }

    @Override
    protected void onPostExecute(String string) {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            InputJson inputJson = mapper.readValue(string, InputJson.class);
            User user = inputJson.getUser();
            Log.d(LOG_TAG, user.toString());
            String firstName = user.getFirstName();
            String secondName = user.getSecondName();
            if (firstName != null && secondName != null) {
                mFirstNameText.get().setText(firstName);
                mSecondNameText.get().setText(secondName);
            } else {
                mFirstNameText.get().setText(R.string.no_result);
                mSecondNameText.get().setText("");
            }
        } catch (JsonProcessingException e) {
            mFirstNameText.get().setText(R.string.no_result);
            mSecondNameText.get().setText("");
            e.printStackTrace();
        }
    }
}
