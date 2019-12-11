package com.example.obstatistics;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.obstatistics.Dto.userEntryJson;
import com.example.obstatistics.Dto.UserEntry;
import com.example.obstatistics.Dto.UserEntryOutput;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class FetchUserEntries extends AsyncTask<String, Void, UserEntryOutput> {

    private static final String LOG_TAG =
            NetworkUtils.class.getSimpleName();

    private WeakReference<TextView> mClubText;
    private WeakReference<TextView> maSIText;
//    private WeakReference<TextView> mFeeText;

//    FetchUserEntries(TextView firstNameText, TextView secondNameText, TextView feeText) {
        FetchUserEntries(TextView firstNameText, TextView secondNameText) {
        this.mClubText = new WeakReference<>(firstNameText);
        this.maSIText = new WeakReference<>(secondNameText);
//        this.mFeeText = new WeakReference<>(feeText);
    }

    @Override
    protected UserEntryOutput doInBackground(String... strings) {
        String string = NetworkUtils.getUserEventEntries(strings[0]);
        ObjectMapper mapper = new ObjectMapper();
        List<UserEntry> userEntryList = new ArrayList<>();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            userEntryJson inputUserJson = mapper.readValue(string, userEntryJson.class);
            for (UserEntry value : inputUserJson.getInputEntries().values()) {
                userEntryList.add(value);
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        UserEntryOutput userEntryOutput = new UserEntryOutput();
        Long fee = 0l;
        for (UserEntry userEntry : userEntryList) {
            userEntryOutput.getCompetitionIdList().add(userEntry.getCompetitionId());
            userEntryOutput.setClubId(userEntry.getClubId());
            userEntryOutput.setSi(userEntry.getSi());
            fee += userEntry.getFee();
        }
        userEntryOutput.setFee(fee);
        Log.d(LOG_TAG, "UserEntryOutput: " + userEntryOutput.toString());
        return userEntryOutput;
    }

    @Override
    protected void onPostExecute(UserEntryOutput userEntryOutput) {
        String firstName = userEntryOutput.getFee().toString();
        String secondName = userEntryOutput.getSi();
        if (firstName != null && secondName != null) {
            mClubText.get().setText(firstName);
            maSIText.get().setText(secondName);
        } else {
            mClubText.get().setText(R.string.no_result);
            maSIText.get().setText("");
        }
    }
}
