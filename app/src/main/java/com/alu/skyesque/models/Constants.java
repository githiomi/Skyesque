package com.alu.skyesque.models;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Monday 25, 03 2024
 */

public class Constants {

    private final String DTO_TRANSFER = "DTO Transfer";

    private final String WEATHER_DETAILS = "Weather Details";

    private final String MY_SHARED_PREFERENCES = "authentication";
    private final String LOGGED_IN_SHARED_PREFERENCES = "isLoggedIn";

    public String getDTO_TRANSFER() {
        return DTO_TRANSFER;
    }

    public String getMY_SHARED_PREFERENCES() {
        return MY_SHARED_PREFERENCES;
    }

    public String getWEATHER_DETAILS() {
        return WEATHER_DETAILS;
    }

    public String getLOGGED_IN_SHARED_PREFERENCES() {
        return LOGGED_IN_SHARED_PREFERENCES;
    }
}
