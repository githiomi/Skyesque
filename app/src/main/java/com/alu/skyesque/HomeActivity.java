package com.alu.skyesque;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.alu.skyesque.models.Constants.OBSERVATION_BASE_URL;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.airbnb.lottie.LottieAnimationView;
import com.alu.skyesque.models.Constants;
import com.alu.skyesque.models.Location;
import com.alu.skyesque.models.WeatherDTO;
import com.alu.skyesque.models.WeatherUnit;
import com.alu.skyesque.parsers.LatestObservationParser;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class HomeActivity extends AppCompatActivity {

    // Local Variables
    private WeatherUnit weatherUnit;
    private final List<Location> locations = Constants.LOCATIONS;
    private Location currentLocation;
    WeatherDTO weatherData;

    // Views
    LottieAnimationView errorAnimation;
    ProgressBar loadingProgressBar;
    ScrollView pageContent;
    ImageButton toProfile, toPrevious, toNext;
    TextView townName, currentDate, overview, details;
    FrameLayout overviewDetailsContainer;
    BottomNavigationView bottomNavigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.RL_mainView), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the views
        initViews();
        initNavigation();
        this.currentLocation = this.locations.get(0);
        this.getData(currentLocation);

        this.toProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        this.toPrevious.setOnClickListener(v -> {
            this.toggleLoading();
            this.goToPrevious();
        });
        this.toNext.setOnClickListener(v -> {
            this.toggleLoading();
            this.goToNext();
        });

        this.details.setOnClickListener(v -> switchToDetails());
        this.overview.setOnClickListener(v -> switchToOverview());
    }

    private void initViews() {
        this.errorAnimation = findViewById(R.id.LA_error);
        this.loadingProgressBar = findViewById(R.id.PB_loading);
        this.pageContent = findViewById(R.id.SV_pageContent);
        this.toProfile = findViewById(R.id.IV_toProfile);
        this.toPrevious = findViewById(R.id.IV_toPrevious);
        this.toNext = findViewById(R.id.IV_toNext);
        this.townName = findViewById(R.id.TV_townName);
        this.currentDate = findViewById(R.id.TV_currentDate);
        this.overview = findViewById(R.id.TV_overview);
        this.details = findViewById(R.id.TV_details);
        this.overviewDetailsContainer = findViewById(R.id.FL_overviewDetails);
        this.bottomNavigationView = findViewById(R.id.NV_bottomNavigation);
    }

    private void initNavigation() {
        this.bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        this.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.bottom_home)
                return true;
            else if (id == R.id.bottom_map) {
                startActivity(new Intent(this, MapActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (id == R.id.bottom_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });
    }

    private void goToPrevious() {
        int index = getIndex() - 1 < 0 ? this.locations.size() - 1 : getIndex() - 1;
        this.currentLocation = locations.get(index);
        this.getData(currentLocation);
    }

    private void goToNext() {
        int index = getIndex() + 1 > this.locations.size() - 1 ? 0 : getIndex() + 1;
        this.currentLocation = locations.get(index);
        this.getData(currentLocation);
    }

    private int getIndex() {
        return this.locations.indexOf(this.currentLocation);
    }

    /**
     * This is the async method to refresh the layout
     */
    public void refreshLayout() {
        int delayInSeconds = 10;
        int periodBetween = 10;
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                HomeActivity.this.runOnUiThread(() -> {
                    toggleLoading();
                    new Handler().postDelayed(() -> {
                        getData(currentLocation);
                    }, 2000);
                    Toast.makeText(getApplicationContext(), "Refreshing. Please wait...", Toast.LENGTH_SHORT).show();
                });
            }
        }, delayInSeconds * 1000, periodBetween * 1000);//put here time 1000 milliseconds=1 second
    }

    /**
     * Method definition to make a network call to retrieve data
     *
     * @param location the location to get the data
     */
    private void getData(Location location) {

        new Thread(() -> {
            StringBuilder result = new StringBuilder();
            URL url;
            URLConnection urlConnection;
            BufferedReader bufferedReader;
            String inputLine;

            try {
                Long townId = location.getLocationId();
                url = new URL(OBSERVATION_BASE_URL + townId);
                urlConnection = url.openConnection();
                bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                while ((inputLine = bufferedReader.readLine()) != null)
                    result.append(inputLine);
                bufferedReader.close();
            } catch (IOException ae) {
                HomeActivity.this.runOnUiThread(() -> Toast.makeText(this, "Could not get weather data. Check internet connection.", Toast.LENGTH_LONG).show());
                Log.e("Current Forecast URL Connection Exception", "ioexception -> " + ae.getMessage());

                HomeActivity.this.runOnUiThread(this::toggleErrorView);
                return;
            }

            // Clean result to remove unnecessary tags
            // Get rid of the first tag <?xml version="1.0" encoding="utf-8"?>
            int i = result.indexOf(">");
            result = new StringBuilder(result.substring(i + 1));
            //Get rid of the 2nd tag <rss version="2.0" xmlns:atom="http://www.w3.org/2005/Atom">
            i = result.indexOf(">");
            result = new StringBuilder(result.substring(i + 1));

            LatestObservationParser latestObservationParser = new LatestObservationParser();
            InputStream latestInputStream = new ByteArrayInputStream(String.valueOf(result).getBytes());
            weatherUnit = latestObservationParser.getWeatherUnit(latestInputStream);

            WeatherDTO dto = HomeActivity.this.populateWeatherDTO(weatherUnit, location.getLocationName());

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.FL_overviewDetails, OverviewFragment.newInstance(dto, currentLocation))
                    .commit();

            HomeActivity.this.runOnUiThread(() -> {
                this.weatherData = dto;
                this.displayData();
            });

        }).start();
    }

    /**
     * Method to get data from response into usable DTO format
     *
     * @param weatherUnit
     * @return a weather data DTO object
     */
    private WeatherDTO populateWeatherDTO(WeatherUnit weatherUnit, String townName) {
        String title = weatherUnit.getTitle();
        String description = weatherUnit.getDescription();

        String day = title.split(" ")[0];
        String weatherSummary = title.substring(title.indexOf(": ") + 2, title.indexOf(","));
        String temperatureCelsius = description.substring(description.indexOf("Temperature: ") + 13, description.indexOf("(") - 1);
        String temperatureFahrenheit = title.substring(title.indexOf("(") + 1, title.indexOf(")"));
        String windDirection = description.substring(description.indexOf("Direction: ") + 11, description.lastIndexOf(", Wind"));
        String windSpeed = description.substring(description.indexOf("Speed: ") + 7, description.lastIndexOf(", Humidity"));
        String humidity = description.substring(description.indexOf("Humidity: ") + 10, description.lastIndexOf(", Pressure"));
        String pressure = description.substring(description.indexOf("Pressure: ") + 10, description.lastIndexOf(", Visibility")).split(",")[0];
        String visibility = description.substring(description.indexOf("Visibility: ") + 12);
        String latitude = "latitude";
        String longitude = "longitude";

        return new WeatherDTO(townName, day, weatherSummary, temperatureCelsius, temperatureFahrenheit,
                windDirection, windSpeed, humidity, pressure, visibility, latitude, longitude);
    }

    /**
     * Method to take retrieved data and display on the UI
     */
    private void displayData() {
        this.townName.setText(this.weatherData.getLocation());

        String date = this.weatherData.getDay() + " " + getDate();
        this.currentDate.setText(date);

        this.togglePageContent();
        this.refreshLayout();
    }

    private String getDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.getDayOfMonth() + " " + localDate.getMonth() + ", " + localDate.getYear();
    }

    /**
     * This method will remove the progress bar and display loaded data
     */
    private void togglePageContent() {
        this.loadingProgressBar.setVisibility(View.GONE);
        this.pageContent.setVisibility(View.VISIBLE);
    }

    /**
     * Method definition to hide progress bar and show error animation
     */
    private void toggleErrorView() {
        this.loadingProgressBar.setVisibility(GONE);
        this.errorAnimation.setVisibility(VISIBLE);
    }

    /**
     * This method will set the loading view awaiting data
     */
    private void toggleLoading() {
        this.loadingProgressBar.setVisibility(View.VISIBLE);
        this.pageContent.setVisibility(View.GONE);
    }

    /**
     * This method switches to the Details Fragment
     */
    private void switchToDetails() {
        this.details.setBackground(getDrawable(R.drawable.active_tab_background));
        this.overview.setBackgroundColor(getColor(R.color.transparent));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, DetailsFragment.newInstance(weatherData))
                .commit();
    }

    /**
     * This method switches to the Overview Fragment
     */
    private void switchToOverview() {
        this.overview.setBackground(getDrawable(R.drawable.active_tab_background));
        this.details.setBackgroundColor(getColor(R.color.transparent));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, OverviewFragment.newInstance(weatherData, currentLocation))
                .commit();
    }
}