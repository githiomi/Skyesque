package com.alu.skyesque;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.alu.skyesque.models.WeatherDTO;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class OverviewFragment extends Fragment {

    // Fragment Properties
    private static final String ARGUMENT = "weatherData";
    private static WeatherDTO weatherData;

    // Views
    RecyclerView summaryRecyclerView;
    TextView currentTemperature, currentState, windSummary, humiditySummary, sunSummary, toWeeklyForecast;

    // Adapters
    SummaryAdapter summaryAdapter;

    public OverviewFragment() {
        // Required empty public constructor
    }

    public static OverviewFragment newInstance(WeatherDTO weatherDTO) {
        OverviewFragment overviewFragment = new OverviewFragment();
        Bundle weatherBundle = new Bundle();
        weatherBundle.putParcelable(ARGUMENT, weatherDTO);
        overviewFragment.setArguments(weatherBundle);
        return overviewFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            weatherData = getArguments().getParcelable(ARGUMENT);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        this.currentTemperature = view.findViewById(R.id.TV_currentTemperature);
        this.currentState = view.findViewById(R.id.TV_currentState);
        this.windSummary = view.findViewById(R.id.TV_windSummary);
        this.humiditySummary = view.findViewById(R.id.TV_humiditySummary);
        this.sunSummary = view.findViewById(R.id.TV_sunSummary);
        this.summaryRecyclerView = view.findViewById(R.id.RV_todaySummary);
        this.toWeeklyForecast = view.findViewById(R.id.TV_toFullForecast);

        populateData();
        setSummaryAdapter(container);

        // On Click Listeners
        this.toWeeklyForecast.setOnClickListener(v -> {
            Intent forecastIntent = new Intent(getContext(), WeeklyForecastActivity.class);
            forecastIntent.putExtra(ARGUMENT, weatherData);
            requireActivity().startActivity(forecastIntent);
        });

        return view;
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