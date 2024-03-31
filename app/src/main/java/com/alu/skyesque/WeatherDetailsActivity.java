package com.alu.skyesque;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alu.skyesque.models.DetailedWeatherDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Random;

public class WeatherDetailsActivity extends AppCompatActivity {

    // Views
    TextView townName, pinLocation, currentDate, currentTemperature, minMaxTemperature, currentState,
            windSpeedSummary, humiditySummary, pressureSummary, windDirection, uvIndex, rainfall, visibility;

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

        DetailedWeatherDTO detailedWeatherDTO = getIntent().getParcelableExtra("toDetailsDTO");
        Log.e(TAG, "onCreate: " + detailedWeatherDTO.toString());

        populateData(detailedWeatherDTO);

        // CLick listeners
        this.pinLocation.setOnClickListener(v -> {
//            Intent mapIntent = new Intent(this, MapActivity.class);
//            String coordinates = detailedWeatherDTO.getLatitude() + "," + detailedWeatherDTO.getLongitude();
//            mapIntent.putExtra("coordinates", coordinates);
//            startActivity(mapIntent);
        });

    }

    private void bindViews() {
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

        this.townName.setText("Glasgow");
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
        return String.valueOf(round(new Random().nextFloat() * (max - min) + min, 2)) + "mm";
    }

    private String round(float d, int decimalPlace) {
        BigDecimal bd = new BigDecimal(Float.toString(d));
        bd = bd.setScale(decimalPlace, BigDecimal.ROUND_HALF_UP);
        return String.valueOf(bd);
    }
}