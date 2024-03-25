package com.alu.skyesque.models;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Monday 25, 03 2024
 */

public class DetailedWeatherDTO extends WeatherDTO {
    private String minimumTemperature;
    private String maximumTemperature;
    private String uvRisk;
    private String pollution;
    private String sunrise;
    private String sunset;

    public DetailedWeatherDTO() {
    }

    public DetailedWeatherDTO(String location, String day, String weatherSummary, String temperatureCelsius,
                              String temperatureFahrenheit, String windDirection, String windSpeed,
                              String humidity, String pressure, String visibility, String latitude,
                              String longitude, String minimumTemperature, String maximumTemperature,
                              String uvRisk, String pollution, String sunrise, String sunset) {

        super(location, day, weatherSummary, temperatureCelsius, temperatureFahrenheit, windDirection,
                windSpeed, humidity, pressure, visibility, latitude, longitude);

        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.uvRisk = uvRisk;
        this.pollution = pollution;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getMinimumTemperature() {
        return minimumTemperature;
    }

    public void setMinimumTemperature(String minimumTemperature) {
        this.minimumTemperature = minimumTemperature;
    }

    public String getMaximumTemperature() {
        return maximumTemperature;
    }

    public void setMaximumTemperature(String maximumTemperature) {
        this.maximumTemperature = maximumTemperature;
    }

    public String getUvRisk() {
        return uvRisk;
    }

    public void setUvRisk(String uvRisk) {
        this.uvRisk = uvRisk;
    }

    public String getPollution() {
        return pollution;
    }

    public void setPollution(String pollution) {
        this.pollution = pollution;
    }

    public String getSunrise() {
        return sunrise;
    }

    public void setSunrise(String sunrise) {
        this.sunrise = sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    public void setSunset(String sunset) {
        this.sunset = sunset;
    }

    @Override
    public String toString() {
        return "DetailedWeatherDTO{" +
                "location='" + super.getLocation() + '\'' +
                ", day='" + super.getDay() + '\'' +
                ", weatherSummary='" + super.getWeatherSummary() + '\'' +
                ", temperatureCelsius='" + super.getTemperatureCelsius() + '\'' +
                ", temperatureFahrenheit='" + super.getTemperatureFahrenheit() + '\'' +
                ", windDirection='" + super.getWindDirection() + '\'' +
                ", windSpeed='" + super.getWindSpeed() + '\'' +
                ", humidity='" + super.getHumidity() + '\'' +
                ", pressure='" + super.getPressure() + '\'' +
                ", visibility='" + super.getVisibility() + '\'' +
                ", latitude='" + super.getLatitude() + '\'' +
                ", longitude='" + super.getLongitude() + '\'' +
                "minimumTemperature='" + minimumTemperature + '\'' +
                ", maximumTemperature='" + maximumTemperature + '\'' +
                ", uvRisk='" + uvRisk + '\'' +
                ", pollution='" + pollution + '\'' +
                ", sunrise='" + sunrise + '\'' +
                ", sunset='" + sunset + '\'' +
                '}';
    }
}
