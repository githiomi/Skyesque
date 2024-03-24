package com.alu.skyesque.parsers;

import com.alu.skyesque.models.WeatherChannel;
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
 * Date of creation: Sunday 24, 03 2024
 */
public class LatestObservationParser {

    private final List<WeatherChannel> weatherChannels = new ArrayList<>();
    private WeatherChannel weatherChannel;
    private WeatherUnit weatherUnit;
    private String text;

    public WeatherChannel getWeatherChannel(InputStream inputStream) {
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
                        if (tagname.equalsIgnoreCase("channel")) {
                            // create a new instance of faculty
                            weatherChannel = new WeatherChannel();
                        }
                        if (tagname.equalsIgnoreCase("item")) {
                            weatherUnit = new WeatherUnit();
                        }
                        break;

                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagname.equalsIgnoreCase("channel")) {
                            weatherChannels.add(weatherChannel);
                        } else if (tagname.equalsIgnoreCase("title")) {
                            weatherChannel.setTitle(text);
                        } else if (tagname.equalsIgnoreCase("link")) {
                            weatherChannel.setLink(text);
                        } else if (tagname.equalsIgnoreCase("description")) {
                            weatherChannel.setDescription(text);
                        } else if (tagname.equalsIgnoreCase("language")) {
                            weatherChannel.setLanguage(text);
                        } else if (tagname.equalsIgnoreCase("copyright")) {
                            weatherChannel.setCopyright(text);
                        } else if (tagname.equalsIgnoreCase("pubDate")) {
                            weatherChannel.setPubDate(text);
                        } else if (tagname.equalsIgnoreCase("dc:date")) {
                            weatherChannel.setDcDate(text);
                        } else if (tagname.equalsIgnoreCase("dc:language")) {
                            weatherChannel.setDcLanguage(text);
                        } else if (tagname.equalsIgnoreCase("dc:rights")) {
                            weatherChannel.setDcRights(text);
                        } else if (tagname.equalsIgnoreCase("atom:link")) {
                            weatherChannel.setAtomLink(text);
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

        return weatherChannel;
    }

}
