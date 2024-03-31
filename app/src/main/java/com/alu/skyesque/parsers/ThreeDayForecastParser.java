package com.alu.skyesque.parsers;

import android.util.Log;

import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;

import com.alu.skyesque.R;
import com.alu.skyesque.models.DetailedWeatherDTO;
import com.alu.skyesque.models.WeatherDTO;
import com.alu.skyesque.models.WeatherUnit;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Monday 25, 03 2024
 */
public class ThreeDayForecastParser {

    // Array to hold the 3 day forecast
    List<WeatherUnit> weatherUnits = new ArrayList<>();
    List<DetailedWeatherDTO> detailedWeatherUnits = new ArrayList<>();
    WeatherUnit weatherUnit;
    private String text;

    public List<DetailedWeatherDTO> getThreeDayForecast(InputStream inputStream) {

        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(inputStream, null);
            int eventType = parser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = parser.getName();

                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("item")) {
                            weatherUnit = new WeatherUnit();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("title") & weatherUnit != null) {
                            weatherUnit.setTitle(text);
                        } else if (tagname.equalsIgnoreCase("link") & weatherUnit != null) {
                            weatherUnit.setLink(text);
                        } else if (tagname.equalsIgnoreCase("description") & weatherUnit != null) {
                            weatherUnit.setDescription(text);
                        } else if (tagname.equalsIgnoreCase("pubDate") & weatherUnit != null) {
                            weatherUnit.setPublishedDate(text);
                        } else if (tagname.equalsIgnoreCase("guid") & weatherUnit != null) {
                            weatherUnit.setGuid(text);
                        } else if (tagname.equalsIgnoreCase("dc:date") & weatherUnit != null) {
                            weatherUnit.setDate(text);
                        } else if (tagname.equalsIgnoreCase("georss:point") & weatherUnit != null) {
                            weatherUnit.setGeorss(text);
                        } else if (tagname.equalsIgnoreCase("item") & weatherUnit != null) {
                            weatherUnits.add(weatherUnit);
                        }
                        break;

                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException | IOException e) {
            e.printStackTrace();
        }

//        Log.e("Weather Units -> ", weatherUnits.toString());
//        Log.e("----- -> ","------------------------------");
//        Log.e("Detailed Weather Units -> ", detailedWeatherUnits.toString());

        return this.weatherUnits.stream().map(this::DTOMapper).collect(Collectors.toList());
    }

    private DetailedWeatherDTO DTOMapper(WeatherUnit weatherUnit) {

        String location = "Glasgow";
        String title = weatherUnit.getTitle();
        String description = weatherUnit.getDescription();

        String day = title.split(": ")[0];
        String weatherSummary = title.substring(title.indexOf(": ") + 2, title.indexOf(","));
//        String maximumTemperature = description.substring(description.indexOf("Maximum Temperature: ") + 21, description.indexOf(", Minimum Temperature") - 1).split(" ")[0];
        String maximumTemperature = "10°C";
        String minimumTemperature = description.substring(description.indexOf("Minimum Temperature: ") + 21, description.indexOf(", Wind Direction") - 1).split(" ")[0];
        int temp = (Integer.parseInt(maximumTemperature.split("°")[0]) + Integer.parseInt(minimumTemperature.split("°")[0])) / 2;
        String temperatureCelsius = String.valueOf(temp) + "°C";
        String temperatureFahrenheit = String.valueOf((temp * (9/5)) + 32) + "°F";
        String windDirection = description.substring(description.indexOf("Direction: ") + 11, description.lastIndexOf(", Wind"));
        String windSpeed = description.substring(description.indexOf("Speed: ") + 7, description.lastIndexOf(", Visibility"));
        String visibility = description.substring(description.indexOf("Visibility: ") + 12, description.lastIndexOf(", Pressure"));
        String pressure = description.substring(description.indexOf("Pressure: ") + 10, description.lastIndexOf(", Humidity"));
        String humidity = description.substring(description.indexOf("Humidity: ") + 10, description.lastIndexOf(", UV"));
        String uvRisk = description.substring(description.indexOf("UV Risk: ") + 9, description.lastIndexOf(", Pollution"));
//        String pollution = description.substring(description.indexOf("Pollution: ") + 11, description.lastIndexOf(", Sunrise"));
        String pollution = "Low";
        String sunrise = description.substring(description.indexOf("Sunrise: ") + 9, description.lastIndexOf(", Sunset"));
        String sunset = description.substring(description.indexOf("Sunset: ") + 9);
        String latitude = weatherUnit.getGeorss().split(" ")[0];
        String longitude = weatherUnit.getGeorss().split(" ")[1];

        return new DetailedWeatherDTO(location, day, weatherSummary, temperatureCelsius, temperatureFahrenheit,
                windDirection, windSpeed, humidity, pressure, visibility, latitude, longitude, minimumTemperature,
                maximumTemperature, uvRisk, pollution, sunrise, sunset);
    }
}
