package com.alu.skyesque;

import static com.alu.skyesque.models.Constants.LOGGED_IN_USER;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alu.skyesque.models.Constants;
import com.alu.skyesque.models.User;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class ProfileActivity extends AppCompatActivity {

    // Views
    ImageButton backButton;
    AppCompatButton editButton, logoutButton;
    TextInputLayout emailInputLayout, passwordInputLayout;
    TextView username, toSystemPreferences;

    // Activity properties
    User loggedInUser;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.sharedPreferences = getSharedPreferences(Constants.MY_SHARED_PREFERENCES, MODE_PRIVATE);
        this.sharedPreferencesEditor = this.sharedPreferences.edit();

        bindViews();

        populateUserDetails();

        // On Click Listeners
        this.backButton.setOnClickListener(v -> super.getOnBackPressedDispatcher().onBackPressed());
        this.editButton.setOnClickListener(v -> {

            if (this.editButton.getText().toString().equals("Edit")) {
                this.editButton.setText("Save");
                this.emailInputLayout.setEnabled(true);
                this.passwordInputLayout.setEnabled(true);
            } else {
                Toast.makeText(this, "Saving Changes. Please wait...", Toast.LENGTH_LONG).show();
                this.editButton.setText("Edit");
                this.emailInputLayout.setEnabled(false);
                this.passwordInputLayout.setEnabled(false);
            }
        });
        this.toSystemPreferences.setOnClickListener(v -> startActivity(new Intent(this, SettingsActivity.class)));
        this.logoutButton.setOnClickListener(v -> {
            this.sharedPreferencesEditor.remove(LOGGED_IN_USER).apply();
            startActivity(new Intent(this, AuthenticationActivity.class));
            finish();
        });
    }

    /**
     * Method definition to bind views
     */
    private void bindViews(){
        this.backButton = findViewById(R.id.IB_backArrow);
        this.username = findViewById(R.id.TV_username);
        this.editButton = findViewById(R.id.BTN_editDetails);
        this.emailInputLayout = findViewById(R.id.IL_emailAddress);
        this.passwordInputLayout = findViewById(R.id.IL_password);
        this.toSystemPreferences = findViewById(R.id.TV_toSystemPreferences);
        this.logoutButton = findViewById(R.id.BTN_logout);
    }

    private void populateUserDetails(){
        String username = this.sharedPreferences.getString(LOGGED_IN_USER, "");
        this.loggedInUser = username.equals("DGITH200") ? User.DGITH200 : User.ABART999;

        String fullName = this.loggedInUser.getFirstName() + "\n" + this.loggedInUser.getLastName();
        this.username.setText(fullName);
        this.emailInputLayout.setHint(this.loggedInUser.getEmail());
        this.passwordInputLayout.setHint(this.loggedInUser.getPassword());
    }

}