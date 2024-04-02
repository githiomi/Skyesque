package com.alu.skyesque;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.ViewFlipper;

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
    ImageButton toPrevious, toNext;
    ViewFlipper chartViewFlipper;

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

        // Bind views
        bindViews(view);

        // Add data to fields
        populateWeatherData();

        // Click Listeners - To toggle through charts
        this.toPrevious.setOnClickListener(v -> this.chartViewFlipper.showPrevious());
        this.toNext.setOnClickListener(v -> this.chartViewFlipper.showNext());

        return view;
    }

    private void bindViews(View view) {
        this.toPrevious = view.findViewById(R.id.IV_toPrevious);
        this.chartViewFlipper = view.findViewById(R.id.VF_chartFlipper);
        this.toNext = view.findViewById(R.id.IV_toNext);
        this.windDirection = view.findViewById(R.id.TV_windDirection);
        this.uvIndex = view.findViewById(R.id.TV_uvIndexDesc);
        this.rainfall = view.findViewById(R.id.TV_rainfallDesc);
        this.visibility = view.findViewById(R.id.TV_visibilityDesc);
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