package com.alu.skyesque;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alu.skyesque.interfaces.ForecastInterface;
import com.alu.skyesque.models.DetailedWeatherDTO;
import com.alu.skyesque.parsers.ThreeDayForecastParser;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class WeeklyForecastActivity extends AppCompatActivity implements ForecastInterface {

    // Views
    ImageButton backArrow;
    RecyclerView weeklyForecastRecyclerView;
    RelativeLayout heroSection;
    TextView location, temperature, summary;
    ImageView weatherVisual;
    int[] weatherIcons = {R.drawable.cloudy, R.drawable.thunder, R.drawable.fog, R.drawable.mist, R.drawable.rain, R.drawable.snow, R.drawable.day_clear, R.drawable.night_sleet, R.drawable.day_rain, R.drawable.snow_thunder};

    // Adapters
    WeeklyForecastAdapter weeklyForecastAdapter;

    // Activity Properties
    private final String sourceUrl = "https://weather-broker-cdn.api.bbci.co.uk/en/forecast/rss/3day/2643123";
    private List<DetailedWeatherDTO> detailedWeatherDTOs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weekly_forecast);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        changeHeroImage();

        get3DayForecast();

        // Click listeners
        this.backArrow.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());

    }

    /**
     * Method to change hero image based on time
     */
    private void changeHeroImage() {
        LocalTime localTime = LocalTime.now();
        System.out.println(localTime.getHour());
        if (localTime.getHour() >= 19)
            heroSection.setBackground(ContextCompat.getDrawable(this, R.drawable.night_sky));
        else
            heroSection.setBackground(ContextCompat.getDrawable(this, R.drawable.day_sky));
    }

    /**
     * Method to bind views
     */
    private void initViews() {
        this.backArrow = findViewById(R.id.IB_backArrow);
        this.heroSection = findViewById(R.id.RL_hero);
        this.location = findViewById(R.id.TV_heroLocation);
        this.temperature = findViewById(R.id.TV_currentTemperature);
        this.weatherVisual = findViewById(R.id.IV_currentWeather);
        this.summary = findViewById(R.id.TV_weatherSummary);
        this.weeklyForecastRecyclerView = findViewById(R.id.RV_weeklyForecast);
    }

    /**
     * To get the weather data in background UI thread
     */
    private void get3DayForecast() {

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
                WeeklyForecastActivity.this.runOnUiThread(() -> Toast.makeText(this, "Could not get weather data. Check internet connection.", Toast.LENGTH_LONG).show());
                Log.e("Weekly Forecast URL Connection Exception", "ioexception -> " + ae.getMessage());
            }

            // Clean result to remove unnecessary tags
            //Get rid of the first tag <?xml version="1.0" encoding="utf-8"?>
            int i = result.indexOf(">");
            result = new StringBuilder(result.substring(i + 1));
            //Get rid of the 2nd tag <rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
            i = result.indexOf(">");
            result = new StringBuilder(result.substring(i + 1));

            ThreeDayForecastParser threeDayForecastParser = new ThreeDayForecastParser();
            InputStream forecastInputStream = new ByteArrayInputStream(String.valueOf(result).getBytes());
            this.detailedWeatherDTOs = threeDayForecastParser.getThreeDayForecast(forecastInputStream);

            WeeklyForecastActivity.this.runOnUiThread(() -> {
                this.setUpAdapter(detailedWeatherDTOs);
                this.populateViews(detailedWeatherDTOs.get(0));
            });

        }).start();

    }

    private void populateViews(DetailedWeatherDTO data) {
        String location = data.getLocation();
        String temperature = data.getTemperatureCelsius().split("°")[0];
        String summary = data.getWeatherSummary();

        this.location.setText(location);
        this.temperature.setText(temperature);
        this.weatherVisual.setImageResource(this.weatherIcons[new Random().nextInt((9 - 1) + 1) + 1]);
        this.summary.setText(summary);
    }

    private void setUpAdapter(List<DetailedWeatherDTO> data) {
        this.weeklyForecastRecyclerView.setAdapter(new WeeklyForecastAdapter(this, data, this));
        this.weeklyForecastRecyclerView.setHasFixedSize(true);
        this.weeklyForecastRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public void setOnItemClick(int position) {

        Intent intent = new Intent(this, WeatherDetailsActivity.class);
        intent.putExtra("toDetailsDTO", this.detailedWeatherDTOs.get(position));
        startActivity(intent);

    }
}