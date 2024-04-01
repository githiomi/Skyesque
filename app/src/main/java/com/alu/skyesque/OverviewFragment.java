package com.alu.skyesque;

import static com.alu.skyesque.models.Constants.LOCATION_ID;
import static com.alu.skyesque.models.Constants.WEATHER_DETAILS_TRANSFER;
import static com.alu.skyesque.models.Constants.WEATHER_DTO_TRANSFER;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alu.skyesque.models.Location;
import com.alu.skyesque.models.WeatherDTO;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class OverviewFragment extends Fragment {

    // Fragment Properties
    private static WeatherDTO weatherData;
    private static String locationName;
    private static Long locationId;

    // Views
    RecyclerView summaryRecyclerView;
    TextView currentTemperature, currentState, windSummary, humiditySummary, sunSummary, toWeeklyForecast;

    // Adapters
    SummaryAdapter summaryAdapter;

    public OverviewFragment() {
        // Required empty public constructor
    }

    public static OverviewFragment newInstance(WeatherDTO weatherDTO, Location location) {
        OverviewFragment overviewFragment = new OverviewFragment();
        Bundle weatherBundle = new Bundle();
        weatherBundle.putParcelable(WEATHER_DTO_TRANSFER, weatherDTO);
        weatherBundle.putLong(LOCATION_ID, location.getLocationId());
        overviewFragment.setArguments(weatherBundle);
        return overviewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            weatherData = getArguments().getParcelable(WEATHER_DTO_TRANSFER);
            locationId = getArguments().getLong(LOCATION_ID);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        bindViews(view);
        populateData();
        setSummaryAdapter(container);

        // On Click Listeners
        this.toWeeklyForecast.setOnClickListener(v -> {
            Intent forecastIntent = new Intent(getContext(), WeeklyForecastActivity.class);
            forecastIntent.putExtra(WEATHER_DETAILS_TRANSFER, weatherData);
            forecastIntent.putExtra(LOCATION_ID, locationId);
            requireActivity().startActivity(forecastIntent);
        });

        return view;
    }

    private void bindViews(View view) {
        this.currentTemperature = view.findViewById(R.id.TV_currentTemperature);
        this.currentState = view.findViewById(R.id.TV_currentState);
        this.windSummary = view.findViewById(R.id.TV_windSummary);
        this.humiditySummary = view.findViewById(R.id.TV_humiditySummary);
        this.sunSummary = view.findViewById(R.id.TV_sunSummary);
        this.summaryRecyclerView = view.findViewById(R.id.RV_todaySummary);
        this.toWeeklyForecast = view.findViewById(R.id.TV_toFullForecast);
    }

    private void populateData() {
        this.currentTemperature.setText(weatherData.getTemperatureCelsius());
        this.currentState.setText(weatherData.getWeatherSummary());
        this.windSummary.setText(weatherData.getWindSpeed());
        this.humiditySummary.setText(weatherData.getHumidity());
        this.sunSummary.setText(weatherData.getPressure());
    }

    private void setSummaryAdapter(ViewGroup container) {
        summaryAdapter = new SummaryAdapter(container.getContext(), weatherData);
        summaryRecyclerView.setAdapter(summaryAdapter);
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false));
        summaryRecyclerView.setHasFixedSize(true);
    }

}