package com.example.obstatistics.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class NonActiveUserJson {

    @JsonProperty("Data")
    private Map<String, User> users = new HashMap<>();

    public Map<String, User> getUsers() {
        return users;
    }

    public void setUsers(Map<String, User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "UserEntryJson{" +
                "users=" + users +
                '}';
    }
}
