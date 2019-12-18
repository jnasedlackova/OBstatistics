package com.example.obstatistics.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class UserResultJson {

    @JsonProperty("Data")
    private Map<String, UserResult> userResults = new HashMap<>();

    public Map<String, UserResult> getUserResults() {
        if (userResults == null) return null;
        return userResults;
    }

    public void setUserResults(Map<String, UserResult> userResults) {
        this.userResults = userResults;
    }

    @Override
    public String toString() {
        return "UserResultJson{" +
                "userResults=" + userResults +
                '}';
    }
}
