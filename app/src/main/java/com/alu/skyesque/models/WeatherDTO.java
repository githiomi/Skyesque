package com.alu.skyesque.models;

import lombok.Data;

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
    private String temperatureCelsius;
    private String temperatureFahrenheit;
    private String windDirection;
    private String windSpeed;
    private String humidity;
    private String pressure;
    private String visibility;
    private String latitude;
    private String longitude;

    public WeatherDTO() {
    }

    public WeatherDTO(String location, String day, String weatherSummary, String temperatureCelsius,
                      String temperatureFahrenheit, String windDirection, String windSpeed,
                      String humidity, String pressure, String visibility, String latitude,
                      String longitude) {
        this.location = location;
        this.day = day;
        this.weatherSummary = weatherSummary;
        this.temperatureCelsius = temperatureCelsius;
        this.temperatureFahrenheit = temperatureFahrenheit;
        this.windDirection = windDirection;
        this.windSpeed = windSpeed;
        this.humidity = humidity;
        this.pressure = pressure;
        this.visibility = visibility;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getLocation() {
        return location;
    }

    public String getDay() {
        return day;
    }

    @Override
    public String toString() {
        return "WeatherDTO{" +
                "location='" + location + '\'' +
                ", day='" + day + '\'' +
                ", weatherSummary='" + weatherSummary + '\'' +
                ", temperatureCelsius='" + temperatureCelsius + '\'' +
                ", temperatureFahrenheit='" + temperatureFahrenheit + '\'' +
                ", windDirection='" + windDirection + '\'' +
                ", windSpeed='" + windSpeed + '\'' +
                ", humidity='" + humidity + '\'' +
                ", pressure='" + pressure + '\'' +
                ", visibility='" + visibility + '\'' +
                ", latitude='" + latitude + '\'' +
                ", longitude='" + longitude + '\'' +
                '}';
    }
}
