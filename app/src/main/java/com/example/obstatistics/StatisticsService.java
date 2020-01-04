package com.example.obstatistics;

import android.net.NetworkInfo;
import android.os.AsyncTask;
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
import com.example.obstatistics.Saving.Record;
import com.example.obstatistics.Task.FetchCompetition;
import com.example.obstatistics.Task.FetchNonactiveUser;
import com.example.obstatistics.Task.FetchUser;
import com.example.obstatistics.Task.FetchUserEntries;
import com.example.obstatistics.Task.FetchUserResult;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class StatisticsService {

    private static final String LOG_TAG = StatisticsService.class.getSimpleName();

    public int userFound = 0;
    public boolean nonActiveUserFinished = false;

    private ProgressBar spinner;
    private static OutputDto outputDto = new OutputDto();
    public Record record = new Record();
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
    private TextView mNameText;
    private TextView mChipText;
    private TextView mCountCompetitionText;
    private TextView mMoneyPaidText;
    private TextView mTotalTimeText;
    private TextView mTotalLossText;
    private TextView mMedalPlacesText;
    private TextView mDiskPlacesText;
    private TextView mCathegoriesText;
    private TextView mTotalDistanceText;
    private TextView mTotalElevationText;
    private TextView mTotalControlNumberText;
    private View buttonSave;

    private FetchUser fetchUser;
    private FetchNonactiveUser fetchNonActiveUser;
    private FetchUserEntries fetchUserEntries;
    private FetchUserResult fetchUserResult;
    private FetchCompetition fetchCompetition;
    private int numberOfTasks;
    private int counter;


    public void startStatsCounting(NetworkInfo networkInfo,
                                   String registration,
                                   TextView mNameText,
                                   TextView mChipText,
                                   TextView mCountCompetitionText,
                                   TextView mMoneyPaidText,
                                   TextView mTotalTimeText,
                                   TextView mTotalLossText,
                                   TextView mMedalPlacesText,
                                   TextView mDiskPlacesText,
                                   TextView mCathegoriesText,
                                   TextView mTotalDistanceText,
                                   TextView mTotalElevationText,
                                   TextView mTotalControlNumberText,
                                   View buttonSave,
                                   ProgressBar spinner) {
        this.registration = registration;
        this.spinner = spinner;
        this.mNameText = mNameText;
        this.mChipText = mChipText;
        this.mCountCompetitionText = mCountCompetitionText;
        this.mMoneyPaidText = mMoneyPaidText;
        this.mTotalTimeText = mTotalTimeText;
        this.mTotalLossText = mTotalLossText;
        this.mMedalPlacesText = mMedalPlacesText;
        this.mDiskPlacesText = mDiskPlacesText;
        this.mCathegoriesText = mCathegoriesText;
        this.mTotalDistanceText = mTotalDistanceText;
        this.mTotalElevationText = mTotalElevationText;
        this.mTotalControlNumberText = mTotalControlNumberText;
        this.buttonSave = buttonSave;

        if (networkInfo != null && networkInfo.isConnected()
                && registration.length() != 0) {
            spinner.setVisibility(View.VISIBLE);
            getUserInfo(registration, mNameText);
        } else {
            if (registration.length() == 0) {
                mChipText.setText("");
                mNameText.setText(R.string.no_search_term);
            } else {
                mChipText.setText("");
                mNameText.setText(R.string.no_network);
            }
        }
    }

    private void getUserInfo(String registration, TextView mNameText) {
        try {
            fetchUser = new FetchUser(mNameText, this);
            fetchUser.execute(registration);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getNonActiveUserInfo() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Integer thisYear = Integer.valueOf(sdf.format(new Date()));
        try {
            for (int year = thisYear; year >= 2013; year--) {
                if (userFound == 0) {
                    fetchNonActiveUser = new FetchNonactiveUser(mNameText, mChipText,this, spinner);
                    fetchNonActiveUser.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, registration, String.valueOf(year), String.valueOf(numberOfTasks));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readUserResult(User user, int value) {
        if (value == 1) {
            outputDto.setUser(user);
            getUserEntry();
        }
    }

    private void getUserEntry() {
        try {
            fetchUserEntries = new FetchUserEntries(mChipText, mMoneyPaidText, this);
            fetchUserEntries.execute(outputDto.getUser().getId().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readUserEntryResult(UserEntryOutput userEntryOutput) {
        outputDto.setUserEntryOutput(userEntryOutput);
        fillRecordFirstPart();
        getUserResult();
    }

    private void getUserResult() {
        numberOfTasks = outputDto.getUserEntryOutput().getCompetitionIdList().size();
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

    public void readUserResult(UserResult userResult) {
        this.userResult = userResult;
        addUserResult(userResult);
        counter++;
        if (counter >= numberOfTasks) {
            getCompetition();
        }
    }

    private CompetitionAndId addUserResult(UserResult userResult) {
        CompetitionAndId competitionAndId = new CompetitionAndId();
        if (userResult != null && userResult.getTime() != "") {
            competitionAndId.setClassId(userResult.getClassId());
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
            record.setCountCompetition("Počet závodů: " + countEvents);
            mCountCompetitionText.setText(record.getCountCompetition());
            record.setTotalTime("Celkový čas v lese (hh:mm:ss): " + totalTime);
            mTotalTimeText.setText(record.getTotalTime());
            record.setTotalLoss("Celková ztráta na vítěze (hh:mm:ss): " + totalLoss);
            mTotalLossText.setText(record.getTotalLoss());
            record.setMedalPlaces("Počet medailových umístění: " + medalPlaces);
            mMedalPlacesText.setText(record.getMedalPlaces());
            record.setDiskEvents("Počet diskvalifikovaných závodů: " + disk);
            mDiskPlacesText.setText(record.getDiskEvents());
            record.setCathegories("Závodní kategorie: " + listOfClasses.toString());
            mCathegoriesText.setText(record.getCathegories());
        }
        return competitionAndId;
    }

    private void getCompetition() {
        List<CompetitionAndId> competitionAndIdList = outputDto.getUserEntryOutput().getCompetitionAndIdList();
        numberOfTasks = competitionAndIdList.size();
        if (!competitionAndIdList.isEmpty()) {
            for(CompetitionAndId entry : competitionAndIdList) {
                try {
                    fetchCompetition = new FetchCompetition(this, spinner, buttonSave);
                    fetchCompetition.execute(entry.getCompetitionId().toString(), entry.getClassId().toString(), String.valueOf(numberOfTasks));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void readCompetition(Competition competition) {
        this.competition = competition;
        addCompetition(competition);
    }

    private void addCompetition(Competition competition) {
        if (competition != null) {
            totalDistance += competition.getDistance();
            totalClimbing += competition.getClimbing();
            totalControls += competition.getControls();
            NumberFormat formatter = new DecimalFormat("#0.00");
            record.setTotalDistance("Celková uběhnutá vzdálenost (km): " + formatter.format(totalDistance));
            mTotalDistanceText.setText(record.getTotalDistance());
            record.setTotalClimbing("Celkové převýšení (m): " + totalClimbing);
            mTotalElevationText.setText(record.getTotalClimbing());
            record.setTotalControls("Celkový počet kontrol: " + totalControls);
            mTotalControlNumberText.setText(record.getTotalControls());
        }
    }

    public void countTimes() {
        int totalSeconds = secondsTime % 60;
        int totalMinutes = (secondsTime / 60 + minuteTime) % 60;
        int totalHours = (secondsTime / 60 + minuteTime) / 60;
        int lossSeconds = secondsLoss % 60;
        int lossMinutes = (secondsLoss / 60 + minuteLoss) % 60;
        int lossHours = (secondsLoss / 60 + minuteLoss) / 60;
        totalTime = totalHours + ":" + parseTwoDigits(totalMinutes) + ":" + parseTwoDigits(totalSeconds);
        totalLoss = lossHours + ":" + parseTwoDigits(lossMinutes) + ":" + parseTwoDigits(lossSeconds);
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

    public String calculateBirth(String registration) {
        String x = registration.substring(3,5);
        if (isInteger(x)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yy");
            int thisYear = Integer.parseInt(sdf.format(new Date()));
            if (Integer.parseInt(x) > thisYear) {
                return "19" + x;
            } else {
                return "20" + x;
            }
        }
        return "????";

    }

    private boolean isInteger( String input ) {
        try {
            Integer.parseInt( input );
            return true;
        }
        catch( Exception e ) {
            return false;
        }
    }

    private String parseTwoDigits (int number) {
        if (number < 10) {
            return "0" + number;
        }
        return Integer.toString(number);
    }

    private void fillRecordFirstPart() {
        record.setRegistration(registration);
        record.setIdentity(mNameText.getText().toString());
        record.setChip(mChipText.getText().toString());
        record.setMoneyPaid(mMoneyPaidText.getText().toString());
    }
}
