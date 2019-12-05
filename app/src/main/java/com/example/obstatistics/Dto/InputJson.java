package com.example.obstatistics.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class InputJson {

    @JsonProperty("Data")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "InputJson{" +
                "user=" + user +
                '}';
    }
}
