package com.example.obstatistics.Dto;

import java.util.ArrayList;
import java.util.List;

public class UserEntryOutput {

    private List<Long> competitionIdList;
    private List<CompetitionAndId> competitionAndIdList;
    private Long clubId;
    private Long fee;
    private String si;

    public UserEntryOutput() {
        competitionIdList = new ArrayList<>();
        competitionAndIdList = new ArrayList<>();
    }

    public List<Long> getCompetitionIdList() {
        return competitionIdList;
    }

    public void setCompetitionIdList(List<Long> competitionIdList) {
        this.competitionIdList = competitionIdList;
    }

    public List<CompetitionAndId> getCompetitionAndIdList() {
        return competitionAndIdList;
    }

    public void setCompetitionAndIdList(List<CompetitionAndId> competitionAndIdList) {
        this.competitionAndIdList = competitionAndIdList;
    }

    public Long getClubId() {
        return clubId;
    }

    public void setClubId(Long clubId) {
        this.clubId = clubId;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public String getSi() {
        return si;
    }

    public void setSi(String si) {
        this.si = si;
    }

    @Override
    public String toString() {
        return "UserEntryOutput{" +
                "competitionIdList=" + competitionIdList +
                ", competitionAndIdList=" + competitionAndIdList +
                ", clubId=" + clubId +
                ", fee=" + fee +
                ", si='" + si + '\'' +
                '}';
    }
}
