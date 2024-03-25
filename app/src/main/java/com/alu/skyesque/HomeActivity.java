package com.alu.skyesque;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alu.skyesque.models.WeatherUnit;
import com.alu.skyesque.parsers.LatestObservationParser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class HomeActivity extends AppCompatActivity {

    // Local Variables
    private final String sourceUrl = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123";
    //    private final String sourceUrl = "https://weather-broker-cdn.api.bbci.co.uk/en/observation/rss/2643123";
    private WeatherUnit weatherUnit;

    // Views
    ImageButton toProfile;
    TextView townName, currentDate, overview, details;
    FrameLayout overviewDetailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the views
        initViews();

        getData();

        this.toProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        this.townName.setText("Nairobi");
        this.currentDate.setText(getDate());
        this.details.setOnClickListener(v -> switchToDetails());
        this.overview.setOnClickListener(v -> switchToOverview());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, new OverviewFragment())
                .commit();
    }

    private void initViews() {
        this.toProfile = findViewById(R.id.IV_toProfile);
        this.townName = findViewById(R.id.TV_townName);
        this.currentDate = findViewById(R.id.TV_currentDate);
        this.overview = findViewById(R.id.TV_overview);
        this.details = findViewById(R.id.TV_details);
        this.overviewDetailsContainer = findViewById(R.id.FL_overviewDetails);
    }

    private void getData() {
        // new Thread(new BackgroundTask(this.urlSource)).start();
        new Thread(() -> {
            StringBuilder result = new StringBuilder();
            URL url;
            URLConnection urlConnection;
            BufferedReader bufferedReader;
            String inputLine;

            try {
                url = new URL(sourceUrl);
                urlConnection = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((inputLine = bufferedReader.readLine()) != null)
                    result.append(inputLine);
                bufferedReader.close();
            } catch (IOException ae) {
                Log.e("MyTag", "ioexception");
            }

            // Clean result to remove unnecessary tags
            //Get rid of the first tag <?xml version="1.0" encoding="utf-8"?>
            int i = result.indexOf(">");
            result = new StringBuilder(result.substring(i + 1));
            //Get rid of the 2nd tag <rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
            i = result.indexOf(">");
            result = new StringBuilder(result.substring(i + 1));

            LatestObservationParser latestObservationParser = new LatestObservationParser();
            InputStream latestInputStream = new ByteArrayInputStream(String.valueOf(result).getBytes());
            weatherUnit = latestObservationParser.getWeatherUnit(latestInputStream);

        }).start();
    }

    private String getDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.getDayOfMonth() + " " + localDate.getMonth() + ", " + localDate.getYear();
    }

    /**
     * This method switches to the Details Fragment
     */
    private void switchToDetails() {
        this.details.setBackground(getDrawable(R.drawable.active_tab_background));
        this.overview.setBackgroundColor(getColor(R.color.transparent));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, new DetailsFragment())
                .commit();
    }

    /**
     * This method switches to the Overview Fragment
     */
    private void switchToOverview() {
        this.overview.setBackground(getDrawable(R.drawable.active_tab_background));
        this.details.setBackgroundColor(getColor(R.color.transparent));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, new OverviewFragment())
                .commit();
    }
}