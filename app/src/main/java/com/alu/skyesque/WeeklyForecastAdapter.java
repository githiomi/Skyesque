package com.alu.skyesque;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.alu.skyesque.interfaces.ForecastInterface;
import com.alu.skyesque.models.DetailedWeatherDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class WeeklyForecastAdapter extends RecyclerView.Adapter<WeeklyForecastAdapter.ViewHolder> {

    // Adapter properties
    public final ForecastInterface forecastInterface;
    List<DetailedWeatherDTO> weatherDTOs;
    int[] weatherIcons = {R.drawable.cloudy, R.drawable.thunder, R.drawable.fog, R.drawable.mist, R.drawable.rain, R.drawable.snow, R.drawable.day_clear, R.drawable.night_sleet, R.drawable.day_rain, R.drawable.snow_thunder};
    Context context;

    public WeeklyForecastAdapter(Context context, List<DetailedWeatherDTO> weatherDTOs, ForecastInterface forecastInterface) {
        this.weatherDTOs = weatherDTOs;
        this.context = context;
        this.forecastInterface = forecastInterface;
    }

    @NonNull
    @Override
    public WeeklyForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_forecast_item, parent, false), context, weatherDTOs);
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyForecastAdapter.ViewHolder holder, int position) {

        DetailedWeatherDTO weatherDTO = weatherDTOs.get(position);

        if (position == 0) {
            holder.weeklySummaryItem.setBackground(ContextCompat.getDrawable(this.context, R.drawable.summary_item_active_background));
        } else {
            holder.weeklySummaryItem.setAlpha(0.6F);
        }

        holder.dayOfWeek.setText(LocalDate.now().plusDays(position).getDayOfWeek().toString());
        holder.weatherIcon.setImageResource(this.weatherIcons[new Random().nextInt((10 - 1) + 1)]);
        holder.minimumTemperature.setText(weatherDTO.getMinimumTemperature());
        holder.maximumTemperature.setText(weatherDTO.getMaximumTemperature());

    }

    @Override
    public int getItemCount() {
        return weatherDTOs.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        // Views
        RelativeLayout weeklySummaryItem;
        TextView dayOfWeek, minimumTemperature, maximumTemperature;
        ImageView weatherIcon;

        public ViewHolder(@NonNull View itemView, Context context, List<DetailedWeatherDTO> dtos) {
            super(itemView);
            this.weeklySummaryItem = itemView.findViewById(R.id.RL_weeklyForecast);
            this.dayOfWeek = itemView.findViewById(R.id.TV_dayOfTheWeek);
            this.weatherIcon = itemView.findViewById(R.id.IV_dailyWeatherImage);
            this.minimumTemperature = itemView.findViewById(R.id.TV_minimumTemperature);
            this.maximumTemperature = itemView.findViewById(R.id.TV_maximumTemperature);

            itemView.setOnClickListener(v -> {
//                if (forecastInterface != null) {
//                    forecastInterface.setOnItemClick(getAdapterPosition());
//                }
                Intent intent = new Intent(context, WeatherDetailsActivity.class);
                intent.putExtra("toDetailsDTO", dtos.get(getAdapterPosition()));
                context.startActivity(intent);

            });
        }
    }
}
