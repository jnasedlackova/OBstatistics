package com.example.obstatistics.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class CompetitionJson {

    @JsonProperty("Data")
    private CompetitionClasses competitionClasses;

    public CompetitionClasses getCompetitionClasses() {
        return competitionClasses;
    }

    public void setCompetitionClasses(CompetitionClasses competitionClasses) {
        this.competitionClasses = competitionClasses;
    }

    @Override
    public String toString() {
        return "CompetitionJson{" +
                "competitionClasses=" + competitionClasses +
                '}';
    }
}
