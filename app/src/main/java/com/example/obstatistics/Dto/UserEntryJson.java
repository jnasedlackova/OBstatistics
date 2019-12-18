package com.example.obstatistics.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class UserEntryJson {

    @JsonProperty("Data")
    private Map<String, UserEntry> inputEntries = new HashMap<>();

    public Map<String, UserEntry> getInputEntries() {
        return inputEntries;
    }

    public void setInputEntries(Map<String, UserEntry> inputEntries) {
        this.inputEntries = inputEntries;
    }

    @Override
    public String toString() {
        return "UserEntryJson{" +
                "inputEntries=" + inputEntries +
                '}';
    }
}
