package com.alu.skyesque;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.bottomnavigation.BottomNavigationView;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class SettingsActivity extends AppCompatActivity {

    // Views
    ImageButton backButton;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_settings);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initViews();
        initNavigation();

        // Click Listeners
        this.backButton.setOnClickListener(v -> super.getOnBackPressedDispatcher().onBackPressed());
    }

    // Method to assign views
    private void initViews() {
        this.backButton = findViewById(R.id.IB_backArrow);
        this.bottomNavigationView = findViewById(R.id.NV_bottomNavigation);
    }

    private void initNavigation() {
        this.bottomNavigationView.setSelectedItemId(R.id.bottom_profile);
        this.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.bottom_home) {
                startActivity(new Intent(this, HomeActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            else if (id == R.id.bottom_map) {
                startActivity(new Intent(this, MapActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            else return id == R.id.bottom_profile;
        });
    }

}