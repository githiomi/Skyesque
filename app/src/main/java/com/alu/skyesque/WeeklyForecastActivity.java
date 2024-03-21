package com.alu.skyesque;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alu.skyesque.databinding.ActivityWeeklyForecastBinding;

public class WeeklyForecastActivity extends AppCompatActivity {

    // View Binding
    ImageButton backArrow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weekly_forecast);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();

        // Click listeners
        this.backArrow.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    }

    /**
     * Method to bind views
     */
    private void initViews(){
        this.backArrow = findViewById(R.id.IB_backArrow);
    }


}