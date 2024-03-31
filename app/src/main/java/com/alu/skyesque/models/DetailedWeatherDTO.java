package com.alu.skyesque.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Monday 25, 03 2024
 */

public class DetailedWeatherDTO implements Parcelable {
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
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
        this.uvRisk = uvRisk;
        this.pollution = pollution;
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    protected DetailedWeatherDTO(Parcel in) {
        location = in.readString();
        day = in.readString();
        weatherSummary = in.readString();
        temperatureCelsius = in.readString();
        temperatureFahrenheit = in.readString();
        windDirection = in.readString();
        windSpeed = in.readString();
        humidity = in.readString();
        pressure = in.readString();
        visibility = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        minimumTemperature = in.readString();
        maximumTemperature = in.readString();
        uvRisk = in.readString();
        pollution = in.readString();
        sunrise = in.readString();
        sunset = in.readString();
    }

    public static final Creator<DetailedWeatherDTO> CREATOR = new Creator<DetailedWeatherDTO>() {
        @Override
        public DetailedWeatherDTO createFromParcel(Parcel in) {
            return new DetailedWeatherDTO(in);
        }

        @Override
        public DetailedWeatherDTO[] newArray(int size) {
            return new DetailedWeatherDTO[size];
        }
    };

    public String getLocation() {
        return location;
    }

    public String getDay() {
        return day;
    }

    public String getWeatherSummary() {
        return weatherSummary;
    }

    public String getTemperatureCelsius() {
        return temperatureCelsius;
    }

    public String getTemperatureFahrenheit() {
        return temperatureFahrenheit;
    }

    public String getWindDirection() {
        return windDirection;
    }

    public String getWindSpeed() {
        return windSpeed;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getVisibility() {
        return visibility;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public String getMinimumTemperature() {
        return minimumTemperature;
    }

    public String getMaximumTemperature() {
        return maximumTemperature;
    }

    public String getUvRisk() {
        return uvRisk;
    }

    public String getPollution() {
        return pollution;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }

    @NonNull
    @Override
    public String toString() {
        return "DetailedWeatherDTO{" +
                "  location='" + getLocation() + '\'' +
                ", day='" + getDay() + '\'' +
                ", weatherSummary='" + getWeatherSummary() + '\'' +
                ", temperatureCelsius='" + getTemperatureCelsius() + '\'' +
                ", temperatureFahrenheit='" + getTemperatureFahrenheit() + '\'' +
                ", windDirection='" + getWindDirection() + '\'' +
                ", windSpeed='" + getWindSpeed() + '\'' +
                ", humidity='" + getHumidity() + '\'' +
                ", pressure='" + getPressure() + '\'' +
                ", visibility='" + getVisibility() + '\'' +
                ", latitude='" + getLatitude() + '\'' +
                ", longitude='" + getLongitude() + '\'' +
                ", minimumTemperature='" + getMinimumTemperature() + '\'' +
                ", maximumTemperature='" + getMaximumTemperature() + '\'' +
                ", uvRisk='" + getUvRisk() + '\'' +
                ", pollution='" + getPollution() + '\'' +
                ", sunrise='" + getSunrise() + '\'' +
                ", sunset='" + getSunset() + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(location);
        dest.writeString(day);
        dest.writeString(weatherSummary);
        dest.writeString(temperatureCelsius);
        dest.writeString(temperatureFahrenheit);
        dest.writeString(windDirection);
        dest.writeString(windSpeed);
        dest.writeString(humidity);
        dest.writeString(pressure);
        dest.writeString(visibility);
        dest.writeString(latitude);
        dest.writeString(longitude);
        dest.writeString(minimumTemperature);
        dest.writeString(maximumTemperature);
        dest.writeString(uvRisk);
        dest.writeString(pollution);
        dest.writeString(sunrise);
        dest.writeString(sunset);
    }
}
