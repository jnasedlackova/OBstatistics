package com.example.obstatistics.Dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class NonActiveUser {

    @JsonProperty("UserID")
    private Long id;
    @JsonProperty("FirstName")
    private String firstName;
    @JsonProperty("LastName")
    private String secondName;
    @JsonProperty("RegNo")
    private String registration;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public String toString() {
        return "NonActiveUser{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", secondName='" + secondName + '\'' +
                ", registration='" + registration + '\'' +
                '}';
    }
}
