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

import com.alu.skyesque.databinding.FragmentOverviewBinding;

public class OverviewFragment extends Fragment {

    // Views
    RecyclerView summaryRecyclerView;
    TextView toWeeklyForecast;

    // Adapters
    SummaryAdapter summaryAdapter;

    public OverviewFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_overview, container, false);

        this.summaryRecyclerView = view.findViewById(R.id.RV_todaySummary);
        this.toWeeklyForecast = view.findViewById(R.id.TV_toFullForecast);

        setSummaryAdapter(container);

        this.toWeeklyForecast.setOnClickListener(v -> requireActivity().startActivity(new Intent(getContext(), WeeklyForecastActivity.class)));

        return view;
    }

    private void setSummaryAdapter(ViewGroup container) {
        summaryAdapter = new SummaryAdapter(container.getContext());
        summaryRecyclerView.setAdapter(summaryAdapter);
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false));
        summaryRecyclerView.setHasFixedSize(true);
    }

}