package com.example.obstatistics.Dto;

public class CompetitionAndId {

    private Long competitionId;
    private Long classId;

    public CompetitionAndId(Long competitionId, Long classId) {
        this.competitionId = competitionId;
        this.classId = classId;
    }

    public CompetitionAndId() {
    }

    public Long getCompetitionId() {
        return competitionId;
    }

    public void setCompetitionId(Long competitionId) {
        this.competitionId = competitionId;
    }

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    @Override
    public String toString() {
        return "CompetitionAndId{" +
                "competitionId='" + competitionId + '\'' +
                ", classId='" + classId + '\'' +
                '}';
    }
}
