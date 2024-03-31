package com.alu.skyesque;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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

        // View Binding
        bindViews();

        // Confirm Login
        confirmLogin();
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
     * Method definition for redirecting to home activity if logged in
     */
    private void confirmLogin(){

    }
}