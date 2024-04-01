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

    public static final String DTO_TRANSFER = "DTO Transfer";
    public static final String WEATHER_DETAILS = "Weather Details";

    public static final String MY_SHARED_PREFERENCES = "authentication";
    public static final String LOGGED_IN_SHARED_PREFERENCES = "isLoggedIn";
    public static final List<Location> LOCATIONS = new ArrayList<>(
            Arrays.asList(Location.GLASGOW, Location.LONDON, Location.NEW_YORK, Location.OMAN, Location.MAURITIUS, Location.BANGLADESH)
    );

}
