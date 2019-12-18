package com.example.obstatistics.Dto;

public class OutputDto {

    private User user;
    private UserEntryOutput userEntryOutput;
    private UserResultOutput userResultOutput;

    public OutputDto() {
        this.user = new User();
        this.userEntryOutput = new UserEntryOutput();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public UserEntryOutput getUserEntryOutput() {
        return userEntryOutput;
    }

    public void setUserEntryOutput(UserEntryOutput userEntryOutput) {
        this.userEntryOutput = userEntryOutput;
    }

    public UserResultOutput getUserResultOutput() {
        return userResultOutput;
    }

    public void setUserResultOutput(UserResultOutput userResultOutput) {
        this.userResultOutput = userResultOutput;
    }

    @Override
    public String toString() {
        return "OutputDto{" +
                "user=" + user +
                ", userEntryOutput=" + userEntryOutput +
                ", userResultOutput=" + userResultOutput +
                '}';
    }
}
