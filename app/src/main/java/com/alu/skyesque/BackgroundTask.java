package com.alu.skyesque;

import android.util.Log;

import com.alu.skyesque.models.WeatherChannel;
import com.alu.skyesque.parsers.LatestObservationParser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import lombok.Getter;

/**
 * Author: dangit
 * Project Name: Skyesque
 * Name: Daniel Githiomi
 * Student Id: S2110911
 * Programme of Study: Computing
 * Date of creation: Sunday 24, 03 2024
 */
public class BackgroundTask implements Runnable {

    private final String sourceUrl;
    @Getter
    private WeatherChannel weatherChannel;

    public BackgroundTask(String sourceUrl) {
        this.sourceUrl = sourceUrl;
    }

    @Override
    public void run() {

        StringBuilder result = new StringBuilder();
        URL url;
        URLConnection urlConnection;
        BufferedReader bufferedReader;
        String inputLine;

        Log.e("MyTag", "in run");

        try {
            Log.e("MyTag", "in try");
            url = new URL(sourceUrl);
            urlConnection = url.openConnection();
            bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            while ((inputLine = bufferedReader.readLine()) != null) {
                result.append(inputLine);
                Log.e("MyTag", inputLine);
            }
            bufferedReader.close();
        } catch (IOException ae) {
            Log.e("MyTag", "ioexception");
        }

        //Get rid of the first tag <?xml version="1.0" encoding="utf-8"?>
        int i = result.indexOf(">");
        result = new StringBuilder(result.substring(i + 1));
        //Get rid of the 2nd tag <rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
        i = result.indexOf(">");
        result = new StringBuilder(result.substring(i + 1));
        Log.e("MyTag - cleaned", String.valueOf(result));

        LatestObservationParser latestObservationParser = new LatestObservationParser();
        InputStream latestInputStream = new ByteArrayInputStream(String.valueOf(result).getBytes());
        weatherChannel = latestObservationParser.getWeatherChannel(latestInputStream);

        Log.e("From weather channel", weatherChannel.toString());

    }
}
