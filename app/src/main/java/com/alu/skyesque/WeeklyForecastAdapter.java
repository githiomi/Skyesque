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

import java.time.LocalDate;

public class WeeklyForecastAdapter extends RecyclerView.Adapter<WeeklyForecastAdapter.ViewHolder> {

    // Adapter properties
    String[] weekdays = new String[7];

    Context context;

    public WeeklyForecastAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public WeeklyForecastAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_forecast_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull WeeklyForecastAdapter.ViewHolder holder, int position) {

        if(position == 0){
            holder.weeklySummaryItem.setBackground(ContextCompat.getDrawable(this.context, R.drawable.summary_item_active_background));
        }

        holder.dayOfWeek.setText(LocalDate.now().plusDays(position).getDayOfWeek().toString());

    }

    @Override
    public int getItemCount() {
        return 7;
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