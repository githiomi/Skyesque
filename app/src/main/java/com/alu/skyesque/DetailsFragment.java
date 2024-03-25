package com.alu.skyesque;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.alu.skyesque.models.WeatherDTO;

import java.util.Random;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class DetailsFragment extends Fragment {

    // Fragment Properties
    private static final String ARGUMENT = "weatherData";
    private static WeatherDTO weatherData;

    // Views
    TextView windDirection, uvIndex, rainfall, visibility;

    public DetailsFragment() {
        // Required empty public constructor
    }

    public static DetailsFragment newInstance(WeatherDTO weatherDTO) {
        DetailsFragment detailsFragment = new DetailsFragment();
        Bundle weatherBundle = new Bundle();
        weatherBundle.putParcelable(ARGUMENT, weatherDTO);
        detailsFragment.setArguments(weatherBundle);
        return detailsFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            weatherData = getArguments().getParcelable(ARGUMENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        // Initialize views
        this.windDirection = view.findViewById(R.id.TV_windDirection);
        this.uvIndex = view.findViewById(R.id.TV_uvIndexDesc);
        this.rainfall = view.findViewById(R.id.TV_rainfallDesc);
        this.visibility = view.findViewById(R.id.TV_visibilityDesc);

        // Add data to fields
        populateWeatherData();

        return view;
    }

    private void populateWeatherData() {
        this.windDirection.setText(weatherData.getWindDirection());
        this.uvIndex.setText(getUVIndex());
        this.visibility.setText(weatherData.getVisibility());
    }

    private String getUVIndex() {
        int min = 1, max = 10;
        int random = new Random().nextInt((max - min) + min) + 1;
        return random <= 4 ? random + " - Low" : random + " - High";
    }
}