package com.example.lab3.homework;

import java.io.Serializable;

public class Client implements Serializable {
    private int clientId;
    private String name;
    private int xCoordinate;
    private int yCoordinate;
    private String availableDays;         // Assuming this is a comma-separated list, like "Monday, Wednesday"
    private String availableTimeIntervals; // Assuming this is a time interval string, e.g., "09:00-12:00, 14:00-18:00"

    // Getters and Setters
    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public String getAvailableDays() {
        return availableDays;
    }

    public void setAvailableDays(String availableDays) {
        this.availableDays = availableDays;
    }

    public String getAvailableTimeIntervals() {
        return availableTimeIntervals;
    }

    public void setAvailableTimeIntervals(String availableTimeIntervals) {
        this.availableTimeIntervals = availableTimeIntervals;
    }
}

