package com.example.obstatistics.Dto;

import java.util.ArrayList;
import java.util.List;

public class UserEntryOutput {

    private List<Long> competitionIdList;
    private Long clubId;
    private Long fee;
    private String si;

    public UserEntryOutput() {
        competitionIdList = new ArrayList<>();
    }

    public List<Long> getCompetitionIdList() {
        return competitionIdList;
    }

    public void setCompetitionIdList(List<Long> competitionIdList) {
        this.competitionIdList = competitionIdList;
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
                ", clubId=" + clubId +
                ", fee=" + fee +
                ", si='" + si + '\'' +
                '}';
    }
}
