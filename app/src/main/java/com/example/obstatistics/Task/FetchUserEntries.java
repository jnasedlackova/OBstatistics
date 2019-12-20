package com.example.obstatistics.Task;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.obstatistics.Dto.CompetitionAndId;
import com.example.obstatistics.Dto.UserEntryJson;
import com.example.obstatistics.Dto.UserEntry;
import com.example.obstatistics.Dto.UserEntryOutput;
import com.example.obstatistics.NetworkUtils;
import com.example.obstatistics.R;
import com.example.obstatistics.StatisticsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class FetchUserEntries extends AsyncTask<String, Void, UserEntryOutput> {

    private static final String LOG_TAG = FetchUserEntries.class.getSimpleName();

    private WeakReference<TextView> mThirdNameText;
    private WeakReference<TextView> maSIText;

    public StatisticsService statisticsService;

    public FetchUserEntries(TextView thirdNameText, StatisticsService statisticsService) {
        this.mThirdNameText = new WeakReference<>(thirdNameText);
        this.statisticsService = statisticsService;
    }

    @Override
    protected UserEntryOutput doInBackground(String... strings) {
        String string = NetworkUtils.getUserEventEntries(strings[0]);
        ObjectMapper mapper = new ObjectMapper();
        List<UserEntry> userEntryList = new ArrayList<>();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            UserEntryJson inputUserJson = mapper.readValue(string, UserEntryJson.class);
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
            userEntryOutput.getCompetitionAndIdList()
                    .add(new CompetitionAndId(userEntry.getCompetitionId(), userEntry.getClassId()));
            userEntryOutput.setClubId(userEntry.getClubId());
            userEntryOutput.setSi(userEntry.getSi());
            fee += userEntry.getFee();
        }
        userEntryOutput.setFee(fee);
        return userEntryOutput;
    }

    @Override
    protected void onPostExecute(UserEntryOutput userEntryOutput) {
        if (userEntryOutput != null) {
            String thirdName = userEntryOutput.getFee().toString();
            if (thirdName != null) {
                mThirdNameText.get().setText(thirdName);
            } else {
                mThirdNameText.get().setText(R.string.no_result);
            }
            statisticsService.readUserEntryResult(userEntryOutput);
        }
    }
}
