package com.alu.skyesque;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class OverviewFragment extends Fragment {

    // Views
    RecyclerView summaryRecyclerView;

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

        summaryRecyclerView = view.findViewById(R.id.RV_todaySummary);

        summaryAdapter = new SummaryAdapter(container.getContext());
        summaryRecyclerView.setAdapter(summaryAdapter);
        summaryRecyclerView.setLayoutManager(new LinearLayoutManager(container.getContext(), LinearLayoutManager.HORIZONTAL, false ));
        summaryRecyclerView.setHasFixedSize(true);

        return view;
    }
}