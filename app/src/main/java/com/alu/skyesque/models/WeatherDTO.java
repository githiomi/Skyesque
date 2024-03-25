package com.alu.skyesque.models;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Monday 25, 03 2024
 */
public class WeatherDTO {
    private String location;
    private String day;
    private String weatherSummary;
    private String temperature;
    private String windDirection;
    private String windSpeed;
    private String humidity;
    private String pressure;
    private String visibility;
    private String latitude;
    private String longitude;

    public WeatherDTO() {
    }

    public WeatherDTO(String location, String day, String weatherSummary, String temperature, String windDirection,
                      String windSpeed, String humidity, String pressure, String visibility, String latitude,
                      String longitude) {
        this.location = location;
        this.day = day;
        this.weatherSummary = weatherSummary;
        this.temperature = temperature;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
