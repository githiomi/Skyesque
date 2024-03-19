package com.alu.skyesque;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

public class SummaryAdapter extends RecyclerView.Adapter<SummaryAdapter.ViewHolder> {

    private final Context context;
    // Constructor
    public SummaryAdapter(Context context){
        this.context = context;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.summary_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        if (position == 0){
            holder.rootLayout.setBackground(ContextCompat.getDrawable(context, R.drawable.summary_item_active_background));
        }

    }

    @Override
    public int getItemCount() {
        return 6;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout rootLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            rootLayout = itemView.findViewById(R.id.LL_summaryItem);
        }
    }
}
