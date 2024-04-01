package com.alu.skyesque.models;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Monday 01, 04 2024
 */
public enum Location {

    GLASGOW("Glasgow", 2648579L ),
    LONDON("London",2643743L),
    NEW_YORK("New York",5128581L),
    OMAN("Oman",287286L),
    MAURITIUS("Mauritius",934154L),
    BANGLADESH("Bangladesh",1185241L);

    private String locationName;
    private Long locationId;

    Location(String locationName, Long locationId) {
        this.locationName = locationName;
        this.locationId = locationId;
    }

    public String getLocationName() {
        return locationName;
    }

    public Long getLocationId() {
        return locationId;
    }
}
