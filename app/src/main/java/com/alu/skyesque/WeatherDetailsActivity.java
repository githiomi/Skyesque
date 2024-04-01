package com.alu.skyesque;

import static com.alu.skyesque.models.Constants.COORDINATES;
import static com.alu.skyesque.models.Constants.PIN_LOCATION;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alu.skyesque.models.Constants;
import com.alu.skyesque.models.DetailedWeatherDTO;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Random;

public class WeatherDetailsActivity extends AppCompatActivity {

    // Views
    ImageButton backButton;
    TextView townName, pinLocation, currentDate, currentTemperature, minMaxTemperature, currentState,
            windSpeedSummary, humiditySummary, pressureSummary, windDirection, uvIndex, rainfall, visibility;

    // Activity properties
    DetailedWeatherDTO detailedWeatherDTO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_weather_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Bind views
        bindViews();

        this.detailedWeatherDTO = getIntent().getParcelableExtra(Constants.WEATHER_DTO_TRANSFER);
        assert this.detailedWeatherDTO != null;
        populateData(this.detailedWeatherDTO);

        // CLick listeners
        this.pinLocation.setOnClickListener(v -> {
            Intent mapIntent = new Intent(this, MapsActivity.class);
            String coordinates = detailedWeatherDTO.getLatitude() + "," + detailedWeatherDTO.getLongitude();
            mapIntent.putExtra(PIN_LOCATION, detailedWeatherDTO.getLocation());
            mapIntent.putExtra(COORDINATES, coordinates);
            startActivity(mapIntent);
        });
        this.backButton.setOnClickListener(v -> getOnBackPressedDispatcher().onBackPressed());
    }

    private void bindViews() {
        this.backButton = findViewById(R.id.IB_backArrow);
        this.townName = findViewById(R.id.TV_townName);
        this.pinLocation = findViewById(R.id.TV_pinLocation);
        this.currentDate = findViewById(R.id.TV_currentDate);
        this.currentTemperature = findViewById(R.id.TV_currentTemperature);
        this.minMaxTemperature = findViewById(R.id.TV_minMaxTemperature);
        this.currentState = findViewById(R.id.TV_currentState);
        this.windSpeedSummary = findViewById(R.id.TV_windSummary);
        this.humiditySummary = findViewById(R.id.TV_humiditySummary);
        this.pressureSummary = findViewById(R.id.TV_sunSummary);
        this.windDirection = findViewById(R.id.TV_windDirection);
        this.uvIndex = findViewById(R.id.TV_uvIndexDesc);
        this.rainfall = findViewById(R.id.TV_rainfallDesc);
        this.visibility = findViewById(R.id.TV_visibilityDesc);
    }

    private void populateData(DetailedWeatherDTO dto) {

        this.townName.setText(dto.getLocation());
        this.currentDate.setText(getDate(dto.getDay()));
        this.currentTemperature.setText(dto.getTemperatureCelsius());
        String minMax = dto.getMinimumTemperature() + " / " + dto.getMaximumTemperature();
        this.minMaxTemperature.setText(minMax);
        this.currentState.setText(dto.getWeatherSummary());
        this.windSpeedSummary.setText(dto.getWindSpeed());
        this.humiditySummary.setText(dto.getHumidity());
        this.pressureSummary.setText(dto.getPressure());
        this.windDirection.setText(dto.getWindDirection());
        this.uvIndex.setText(getUvIndex(dto.getUvRisk()));
        this.rainfall.setText(getRainfall());
        this.visibility.setText(dto.getVisibility());

    }

    private String getDate(String day) {
        LocalDate localDate = LocalDate.now();
        return day.toUpperCase() + " " + localDate.getDayOfMonth() + " " + localDate.getMonth() + ", " + localDate.getYear();
    }

    private String getUvIndex(String uv) {
        return Integer.parseInt(uv) <= 5 ? uv + " - Low" : uv + " - High";
    }

    private String getRainfall() {
        float min = 20.0F, max = 300.0F;
        return String.valueOf(round(new Random().nextFloat() * (max - min) + min)) + "mm";
    }

    private String round(float d) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return String.valueOf(bd);
    }
}