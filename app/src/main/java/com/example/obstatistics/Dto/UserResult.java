package com.example.obstatistics.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class UserResult {

    @JsonProperty("ClassID")
    private Long classId;
    @JsonProperty("ClassDesc")
    private String classComp;
    @JsonProperty("Sort")
    private Long place;
    @JsonProperty("RegNo")
    private String registration;
    @JsonProperty("Time")
    private String time;
    @JsonProperty("Loss")
    private String loss;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public String getClassComp() {
        return classComp;
    }

    public void setClassComp(String classComp) {
        this.classComp = classComp;
    }

    public Long getPlace() {
        return place;
    }

    public void setPlace(Long place) {
        this.place = place;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getLoss() {
        return loss;
    }

    public void setLoss(String loss) {
        this.loss = loss;
    }

    @Override
    public String toString() {
        return "UserResult{" +
                "classId=" + classId +
                ", classComp='" + classComp + '\'' +
                ", place=" + place +
                ", registration='" + registration + '\'' +
                ", time='" + time + '\'' +
                ", loss='" + loss + '\'' +
                '}';
    }
}
