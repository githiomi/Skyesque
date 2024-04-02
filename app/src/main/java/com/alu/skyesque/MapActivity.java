package com.alu.skyesque;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.FragmentActivity;

import com.alu.skyesque.databinding.ActivityMapBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class MapActivity extends FragmentActivity implements OnMapReadyCallback {

    // Views
    BottomNavigationView bottomNavigationView;
    private GoogleMap mMap;
    private ActivityMapBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        this.bottomNavigationView = binding.NVBottomNavigation;
        initNavigation();

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void initNavigation() {
        this.bottomNavigationView.setSelectedItemId(R.id.bottom_map);
        this.bottomNavigationView.setOnItemSelectedListener(item -> {
            int id = item.getItemId();

            if (id == R.id.bottom_home) {
                startActivity(new Intent(this, HomeActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (id == R.id.bottom_map) {
                return true;
            } else if (id == R.id.bottom_profile) {
                startActivity(new Intent(this, ProfileActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        mMap.addMarker(new MarkerOptions().position(new LatLng(-34, 151)).title("Sydney, Australia Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-20, 57.5)).title("Port Louis, Mauritius Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-3, 37.4)).title("Mount Kilimanjaro, Tanzania Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(0.16, 37)).title("Nairobi, Kenya Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-23, 21)).title("Kalahari Desert, Botswana Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(27, 78)).title("Taj, Mahal, India Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(40, 116.6)).title("Beijing, China Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(-22, -43)).title("Rio de Janeiro, Brazil Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(41, 12)).title("Rome, Italy Marker"));
        mMap.addMarker(new MarkerOptions().position(new LatLng(29,31)).title("Cairo, Egypt Marker"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(new LatLng(-20, 57.5)));
    }
}