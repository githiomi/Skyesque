package com.alu.skyesque;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.alu.skyesque.models.User;
import com.google.android.material.textfield.TextInputLayout;

/**
 * Name                 Daniel Githiomi
 * Student ID           S2110911
 * Programme of Study   Computing - ALU Mauritius
 */
public class AuthenticationActivity extends AppCompatActivity {

    // Views
    TextInputLayout usernameInputLayout, passwordInputLayout;
    AppCompatButton loginButton;

    // Class variables
    // MODE_PUBLIC will make the file public which could be accessible by other applications on the device
    // MODE_PRIVATE keeps the files private and secures the user’s data.
    // MODE_APPEND is used while reading the data from the SP file.
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authentication);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        this.sharedPreferences = getSharedPreferences("authentication", Context.MODE_PRIVATE);
        this.sharedPreferencesEditor = this.sharedPreferences.edit();

        // View Binding
        bindViews();

        // Click Listeners
        this.loginButton.setOnClickListener(v -> {

            String username = String.valueOf(this.usernameInputLayout.getEditText().getText());
            String password = String.valueOf(this.passwordInputLayout.getEditText().getText());

            if (username.equals("ABART999"))
                checkPassword(User.ABART999, password);
            else if (username.equals("DGITH200"))
                checkPassword(User.DGITH200, password);
            else {
                Toast.makeText(this, "Invalid Credentials. Try again.", Toast.LENGTH_SHORT).show();
            }

        });
    }

    /**
     * Method definition for binding views
     */
    private void bindViews() {
        this.usernameInputLayout = findViewById(R.id.IL_username);
        this.passwordInputLayout = findViewById(R.id.IL_password);
        this.loginButton = findViewById(R.id.BTN_login);
    }

    /**
     * Method definition for checking password and editing shared preferences
     */
    private void checkPassword(User user, String password) {
        Log.e(TAG, "passed: " + password);
        Log.e(TAG, "user: " + user.getPassword());

        if (!password.equals(user.getPassword())) {
            Toast.makeText(this, "Invalid password. Try again.", Toast.LENGTH_SHORT).show();
            return;
        }

        this.sharedPreferencesEditor.putString("loggedInUser", user.getUsername()).apply();
        startActivity(new Intent(this, HomeActivity.class));
        finish();

    }

    @Override
    protected void onStart() {
        super.onStart();

        // Check if the user is already logged in
        String user = this.sharedPreferences.getString("loggedInUser", "");

        if (!user.isEmpty()){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }
}