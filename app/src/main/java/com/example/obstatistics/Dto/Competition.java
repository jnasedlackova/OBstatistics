package com.example.obstatistics.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Competition {

    @JsonProperty("ID")
    private Long classId;
    @JsonProperty("Distance")
    private Double distance;
    @JsonProperty("Climbing")
    private Long climbing;
    @JsonProperty("Controls")
    private Long controls;

    public Long getClassId() {
        return classId;
    }

    public void setClassId(Long classId) {
        this.classId = classId;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Long getClimbing() {
        return climbing;
    }

    public void setClimbing(Long climbing) {
        this.climbing = climbing;
    }

    public Long getControls() {
        return controls;
    }

    public void setControls(Long controls) {
        this.controls = controls;
    }

    @Override
    public String toString() {
        return "Competition{" +
                "classId='" + classId + '\'' +
                ", distance=" + distance +
                ", climbing=" + climbing +
                ", controls=" + controls +
                '}';
    }
}
