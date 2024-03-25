package com.alu.skyesque;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.alu.skyesque.models.WeatherDTO;

import java.time.LocalTime;
import java.util.Random;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {

    // Adapter properties
    private final Context context;
    private final WeatherDTO weatherDTO;

    private final int[] weatherIcons = {R.drawable.cloudy, R.drawable.thunder, R.drawable.fog,
            R.drawable.mist, R.drawable.rain, R.drawable.snow, R.drawable.day_clear,
            R.drawable.night_sleet, R.drawable.day_rain, R.drawable.snow_thunder};

    // Constructor
    public SummaryAdapter(Context context, WeatherDTO weatherDTO) {
        this.context = context;
        this.weatherDTO = weatherDTO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        LocalTime currentTime = LocalTime.now();

        if (position == 0) {
            holder.rootLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.summary_item_active_background));
            holder.summaryTemperature.setText(this.weatherDTO.getTemperatureCelsius());
            holder.summaryWeatherIcon.setImageResource(weatherIcons[getRandom()]);
            String time = String.valueOf(currentTime.getHour()) + ":" + currentTime.getMinute();
            holder.summaryTime.setText(time);
            return;
        }

        String time = String.valueOf(currentTime.plusHours(2L * position).getHour()) + ":00";
        String temperature = String.valueOf(getRandomTemperature()) + context.getResources().getString(R.string.degrees_celsius);
        holder.summaryTemperature.setText(temperature);
        holder.summaryWeatherIcon.setImageResource(weatherIcons[getRandom()]);
        holder.summaryTime.setText(time);

    }

    private int getRandom() {
        int min = 1, max = 9;
        return new Random().nextInt((max - min + 1) + min);
    }

    private int getRandomTemperature() {
        int min = 16, max = 38;
        return new Random().nextInt((max - min + 1) + min);
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rootLayout;
        TextView summaryTemperature, summaryTime;
        ImageView summaryWeatherIcon;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rootLayout = itemView.findViewById(R.id.LL_summaryItem);
            summaryTemperature = itemView.findViewById(R.id.TV_summaryItemTemp);
            summaryWeatherIcon = itemView.findViewById(R.id.IV_summaryItemImage);
            summaryTime = itemView.findViewById(R.id.TV_summaryItemTime);
        }
    }
}
