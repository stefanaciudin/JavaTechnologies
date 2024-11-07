package com.example.lab55.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "clients", schema = "public")
@NamedQuery(name = "Client.findByName", query = "SELECT c FROM Client c WHERE c.name = :name")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "x_coordinate", nullable = false)
    private Integer xCoordinate;

    @Column(name = "y_coordinate", nullable = false)
    private Integer yCoordinate;

    @Column(name = "available_days", length = 50)
    private String availableDays;

    @Column(name = "available_time_intervals", length = 50)
    private String availableTimeIntervals;

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getxCoordinate() {
        return xCoordinate;
    }

    public void setxCoordinate(Integer xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public Integer getyCoordinate() {
        return yCoordinate;
    }

    public void setyCoordinate(Integer yCoordinate) {
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

