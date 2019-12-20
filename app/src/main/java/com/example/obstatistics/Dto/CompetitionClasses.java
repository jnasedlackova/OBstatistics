package com.example.obstatistics.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class CompetitionClasses {

    @JsonProperty("Classes")
    private Map<String, Competition> competitions = new HashMap<>();

    public Map<String, Competition> getCompetitions() {
        if (competitions == null) return null;
        return competitions;
    }

    public void setCompetitions(Map<String, Competition> competitions) {
        this.competitions = competitions;
    }

    @Override
    public String toString() {
        return "CompetitionClasses{" +
                "competitions=" + competitions +
                '}';
    }
}
