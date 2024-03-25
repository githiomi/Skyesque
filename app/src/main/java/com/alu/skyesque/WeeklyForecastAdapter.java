package com.alu.skyesque;

import android.content.Context;
import android.icu.util.LocaleData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.alu.skyesque.models.DetailedWeatherDTO;

import java.time.LocalDate;
import java.util.List;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class WeeklyForecastAdapter extends RecyclerView.Adapter<WeeklyForecastAdapter.ViewHolder> {

    // Adapter properties
    List<DetailedWeatherDTO> weatherDTOs;
    Context context;

    public WeeklyForecastAdapter(Context context, List<DetailedWeatherDTO> weatherDTOs) {
        this.weatherDTOs = weatherDTOs;
        this.context = context;
    }

    @NonNull
    @Override
    public WeeklyForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_forecast_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyForecastAdapter.ViewHolder holder, int position) {

        if (position == 0) {
            holder.weeklySummaryItem.setBackground(ContextCompat.getDrawable(this.context, R.drawable.summary_item_active_background));
        } else {
            holder.weeklySummaryItem.setAlpha(0.6F);
        }

        holder.dayOfWeek.setText(LocalDate.now().plusDays(position).getDayOfWeek().toString());

    }

    @Override
    public int getItemCount() {
        return weatherDTOs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Views
        RelativeLayout weeklySummaryItem;
        TextView dayOfWeek;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.weeklySummaryItem = itemView.findViewById(R.id.RL_weeklyForecast);
            this.dayOfWeek = itemView.findViewById(R.id.TV_dayOfTheWeek);
        }
    }
}
