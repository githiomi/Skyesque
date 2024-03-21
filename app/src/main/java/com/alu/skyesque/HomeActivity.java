package com.alu.skyesque;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alu.skyesque.databinding.ActivityHomeBinding;

import java.time.LocalDate;

public class HomeActivity extends AppCompatActivity {

    ImageView toProfile;
    TextView townName, currentDate, overview, details;
    FrameLayout overviewDetailsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);

        setContentView(R.layout.activity_home);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize the views
        ActivityHomeBinding homeBinding = ActivityHomeBinding.inflate(getLayoutInflater());
        initViews(homeBinding);

        this.toProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
        this.townName.setText("Nairobi");
        this.currentDate.setText(getDate());
        this.details.setOnClickListener(v -> switchToDetails());
        this.overview.setOnClickListener(v -> switchToOverview());
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, new OverviewFragment())
                .commit();
    }

    private void initViews(ActivityHomeBinding binding) {
        this.toProfile = findViewById(R.id.IV_toProfile);
        this.townName = binding.TVTownName;
        this.currentDate = binding.TVCurrentDate;
        this.overview = findViewById(R.id.TV_overview);
        this.details = findViewById(R.id.TV_details);
        this.overviewDetailsContainer = binding.FLOverviewDetails;
    }

    private String getDate() {
        LocalDate localDate = LocalDate.now();
        return localDate.getDayOfMonth() + " " + localDate.getMonth() + ", " + localDate.getYear();
    }

    /**
     * This method switches to the Details Fragment
     */
    private void switchToDetails() {
        this.details.setBackground(getDrawable(R.drawable.active_tab_background));
        this.overview.setBackgroundColor(getColor(R.color.transparent));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, new DetailsFragment())
                .commit();
    }

    /**
     * This method switches to the Overview Fragment
     */
    private void switchToOverview() {
        this.overview.setBackground(getDrawable(R.drawable.active_tab_background));
        this.details.setBackgroundColor(getColor(R.color.transparent));
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.FL_overviewDetails, new OverviewFragment())
                .commit();
    }
}