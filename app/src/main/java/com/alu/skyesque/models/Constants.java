package com.alu.skyesque.models;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Monday 25, 03 2024
 */
public class Constants {

    public static final String OBSERVATION_BASE_URL = "https://weather-broker-cdn.api.bbci.co.uk/en/obsevation/rss/";
    public static final String THREE_DAY_BASE_URL = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/";
    public static final String WEATHER_DTO_TRANSFER = "Weather DTO Transfer";
    public static final String WEATHER_LOCATION_TRANSFER = "Weather Location Transfer";
    public static final String WEATHER_DETAILS_TRANSFER = "Weather Details Transfer";
    public static final String LOCATION_ID = "Location ID";
    public static final String COORDINATES = "Coordinates";
    public static final String PIN_LOCATION = "Pin Location";
    public static final String MY_SHARED_PREFERENCES = "authentication";
    public static final String LOGGED_IN_USER = "Logged In User";
    public static final List<Location> LOCATIONS = new ArrayList<>(
            Arrays.asList(Location.GLASGOW, Location.LONDON, Location.NEW_YORK, Location.OMAN, Location.MAURITIUS, Location.BANGLADESH)
    );

}
