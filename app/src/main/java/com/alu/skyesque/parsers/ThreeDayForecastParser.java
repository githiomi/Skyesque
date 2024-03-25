package com.alu.skyesque.parsers;

import com.alu.skyesque.models.WeatherUnit;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

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
    List<WeatherUnit> weatherUnits = new ArrayList<WeatherUnit>();
    WeatherUnit weatherUnit;
    private String text;

    public List<WeatherUnit> getThreeDayForecast(InputStream inputStream) {

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

        return weatherUnits;
    }
}
