package com.example.obstatistics.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserEntry {

    @JsonProperty("EventID")
    private Long competitionId;
    @JsonProperty("ClubID")
    private Long clubId;
    @JsonProperty("Fee")
    private Long fee;
    @JsonProperty("SI")
    private String si;

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
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
        return "UserEntry{" +
                "competitionId=" + competitionId +
                ", clubId=" + clubId +
                ", fee='" + fee + '\'' +
                ", si='" + si + '\'' +
                '}';
    }
}
