package com.example.obstatistics;

import android.net.NetworkInfo;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.obstatistics.Dto.Competition;
import com.example.obstatistics.Dto.CompetitionAndId;
import com.example.obstatistics.Dto.OutputDto;
import com.example.obstatistics.Dto.User;
import com.example.obstatistics.Dto.UserEntryOutput;
import com.example.obstatistics.Dto.UserResult;
import com.example.obstatistics.Dto.UserResultOutput;
import com.example.obstatistics.Task.FetchCompetition;
import com.example.obstatistics.Task.FetchUser;
import com.example.obstatistics.Task.FetchUserEntries;
import com.example.obstatistics.Task.FetchUserResult;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class StatisticsService {

    private ProgressBar spinner;
    private static OutputDto outputDto = new OutputDto();
    private UserResultOutput userResultOutput = new UserResultOutput();
    private String registration;
    private Long competitionId;
    private UserResult userResult;
    private List<CompetitionAndId> competitionAndClassIds = new ArrayList<>();
    private Set<String> listOfClasses = new TreeSet<>();
    private int countEvents = 0;
    private int medalPlaces = 0;
    private int minuteTime = 0;
    private int secondsTime = 0;
    private int minuteLoss = 0;
    private int secondsLoss = 0;
    private int disk = 0;
    public String totalTime = "";
    public String totalLoss = "";
    private Competition competition;
    private Double totalDistance = 0.0;
    private Long totalClimbing = 0l;
    private Long totalControls = 0l;
    private static final String LOG_TAG = StatisticsService.class.getSimpleName();
    private TextView mFirstNameText;
    private TextView mSecondNameText;
    private TextView mThirdNameText;
    private TextView mFourthNameText;
    private FetchUser fetchUser;
    private FetchUserEntries fetchUserEntries;
    private FetchUserResult fetchUserResult;
    private FetchCompetition fetchCompetition;
    private int numberOfTasks;
    private int counter;


    public OutputDto getOutputDto(NetworkInfo networkInfo,
                                  String registration,
                                  TextView mFirstNameText,
                                  TextView mSecondNameText,
                                  TextView mThirdNameText,
                                  TextView mFourthNameText,
                                  ProgressBar spinner) {
        this.registration = registration;
        this.spinner = spinner;
        this.mThirdNameText = mThirdNameText;
        this.mFourthNameText = mFourthNameText;
        spinner.setVisibility(View.VISIBLE);
        if (networkInfo != null && networkInfo.isConnected()
                && registration.length() != 0) {
        getUserInfo(registration, mFirstNameText, mSecondNameText);
        } else {
            if (registration.length() == 0) {
                mSecondNameText.setText("");
                mFirstNameText.setText(R.string.no_search_term);
            } else {
                mSecondNameText.setText("");
                mFirstNameText.setText(R.string.no_network);
            }
        }
        return outputDto;
    }

    private void getUserInfo(String registration, TextView mFirstNameText, TextView mSecondNameText) {
        try {
            fetchUser = new FetchUser(mFirstNameText, mSecondNameText, this);
            fetchUser.execute(registration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readUser(User user) {
        Log.d(LOG_TAG, "User: " + user.toString());
        outputDto.setUser(user);
        getUserEntry();
    }

    private void getUserEntry() {
        try {
            fetchUserEntries = new FetchUserEntries(mThirdNameText, this);
            fetchUserEntries.execute(outputDto.getUser().getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readUserEntryResult(UserEntryOutput userEntryOutput) {
        outputDto.setUserEntryOutput(userEntryOutput);
        getUserResult();
    }

    private void getUserResult() {
        numberOfTasks = outputDto.getUserEntryOutput().getCompetitionIdList().size();
        Log.d(LOG_TAG, "number of tasks: " + numberOfTasks);
        for (Long competitionId : outputDto.getUserEntryOutput().getCompetitionIdList()) {
            try {
                this.competitionId = competitionId;
                fetchUserResult = new FetchUserResult(this, spinner);
                fetchUserResult.execute(competitionId.toString(), registration, String.valueOf(numberOfTasks));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void getCompetition() {
        List<CompetitionAndId> competitionAndIdList = outputDto.getUserEntryOutput().getCompetitionAndIdList();
//        String numberOfTasks = String.valueOf(outputDto.getUserResultOutput().getCompetitionAndClassIds().size());
        if (!competitionAndIdList.isEmpty()) {
            Log.d(LOG_TAG, "CompetitionAndClassIds: " + competitionAndIdList.toString());
            for(CompetitionAndId entry : competitionAndIdList) {
                try {
                    fetchCompetition = new FetchCompetition(this, spinner);
                    fetchCompetition.execute(entry.getCompetitionId().toString(), entry.getClassId().toString());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void readUser(UserResult userResult) {
        if (userResult != null)
            Log.d(LOG_TAG, "UserResult: " + userResult.toString());
        this.userResult = userResult;
        addUserResult(userResult);
        counter++;
        Log.d(LOG_TAG, "counter: " + counter);
        if (counter >= numberOfTasks) {
            getCompetition();
        }
    }

    public void readCompetition(Competition competition) {
        if (competition != null)
            Log.d(LOG_TAG, "Competition: " + competition.toString());
        this.competition = competition;
        addCompetition(competition);
    }

    private CompetitionAndId addUserResult(UserResult userResult) {
        CompetitionAndId competitionAndId = new CompetitionAndId();
        if (userResult != null && userResult.getTime() != "") {
            competitionAndId.setClassId(userResult.getClassId());
            Log.d(LOG_TAG, "CompetitionAndId: " + competitionAndId.toString());
            listOfClasses.add(userResult.getClassComp());
            countEvents += 1;
            if (userResult.getPlace() >= 1 && userResult.getPlace() < 4) {
                medalPlaces += 1;
            }
            if (userResult.getTime().equals("DISK")) {
                disk += 1;
            } else {
                String[] rowtime = userResult.getTime().split(":");
                minuteTime += Integer.parseInt(rowtime[0]);
                secondsTime += Integer.parseInt(rowtime[1]);
                if (userResult.getLoss() != "") {
                    String[] rowLoss = userResult.getLoss().split(" |:");
                    minuteLoss += Integer.parseInt(rowLoss[1]);
                    secondsLoss += Integer.parseInt(rowLoss[2]);
                }
            }
            countTimes();
            Log.d(LOG_TAG, "User: " + outputDto.getUser().toString());
            Log.d(LOG_TAG, "listOfClasses: " + listOfClasses.toString());
            Log.d(LOG_TAG, "countEvents: " + countEvents);
            Log.d(LOG_TAG, "medalPlaces: " + medalPlaces);
            Log.d(LOG_TAG, "totalTime: " + totalTime);
            Log.d(LOG_TAG, "totalLoss: " + totalLoss);
            Log.d(LOG_TAG, "diskEvents: " + disk);
            mFourthNameText.setText(totalTime);
        }
        return competitionAndId;
    }

    private void addCompetition(Competition competition) {
        if (competition != null) {
            totalDistance += competition.getDistance();
            totalClimbing += competition.getClimbing();
            totalControls += competition.getControls();
            Log.d(LOG_TAG, "Distance: " + totalDistance);
            Log.d(LOG_TAG, "Climbing: " + totalClimbing);
            Log.d(LOG_TAG, "Controls: " + totalControls);
            mFourthNameText.setText(totalTime);

        }
    }

    public void countTimes() {
        int totalSeconds = secondsTime % 60;
        int totalMinutes = (secondsTime / 60 + minuteTime) % 60;
        int totalHours = (secondsTime / 60 + minuteTime) / 60;
        int lossSeconds = secondsLoss % 60;
        int lossMinutes = (secondsLoss / 60 + minuteLoss) % 60;
        int lossHours = (secondsLoss / 60 + minuteLoss) / 60;
        totalTime = totalHours + ":" + totalMinutes + ":" + totalSeconds;
        totalLoss = lossHours + ":" + lossMinutes + ":" + lossSeconds;
    }

    public UserResultOutput getUserResutlOutput() {
        userResultOutput.setCompetitionAndClassIds(competitionAndClassIds);
        userResultOutput.setCountEvents(countEvents);
        userResultOutput.setMedalPlaces(medalPlaces);
        userResultOutput.setDisk(disk);
        userResultOutput.setListOfClasses(listOfClasses);
        userResultOutput.setTime(totalTime);
        userResultOutput.setLoss(totalLoss);
        return userResultOutput;
    }
}
