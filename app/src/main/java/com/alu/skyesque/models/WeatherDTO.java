package com.alu.skyesque.models;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Monday 25, 03 2024
 */
public class WeatherDTO implements Parcelable {
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

    protected WeatherDTO(Parcel in) {
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
    }

    public static final Creator<WeatherDTO> CREATOR = new Creator<WeatherDTO>() {
        @Override
        public WeatherDTO createFromParcel(Parcel in) {
            return new WeatherDTO(in);
        }

        @Override
        public WeatherDTO[] newArray(int size) {
            return new WeatherDTO[size];
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
    }
}
