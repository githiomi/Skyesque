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
    BANGLADESH("Bangladesh",1185241L),
    CAPE_TOWN("Cape Town", 3369157L),
    NAIROBI("Nairobi", 184745L),
    ANTANANARIVO("Antananarivo", 1070940L),
    DAR_ES_SALAAM("Dar es Salaam", 160263L),
    HARARE("Harare", 890299L),
    CAIRO("Cairo", 360630L);

    private final String locationName;
    private final Long locationId;

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
