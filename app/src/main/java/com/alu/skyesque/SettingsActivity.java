package com.alu.skyesque;

import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

/**
* Name                 Daniel Githiomi
* Student ID           S2110911
* Programme of Study   Computing - ALU Mauritius
*/
public class SettingsActivity extends AppCompatActivity {

    // Views
    ImageButton backButton;

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

        // Click Listeners
        this.backButton.setOnClickListener(v -> super.getOnBackPressedDispatcher().onBackPressed());

    }

    // Method to assign views
    private void initViews(){
        this.backButton = findViewById(R.id.IB_backArrow);
    }
}