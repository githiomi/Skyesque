package com.alu.skyesque;

import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.textfield.TextInputLayout;

public class ProfileActivity extends AppCompatActivity {

    // Views
    ImageButton backButton;
    AppCompatButton editButton, logoutButton;
    TextInputLayout emailInputLayout, passwordInputLayout;

    String email = "daniel@gmail.com", password = "**********";

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

        this.backButton = findViewById(R.id.IB_backArrow);
        this.editButton = findViewById(R.id.BTN_editDetails);
        this.emailInputLayout = findViewById(R.id.IL_emailAddress);
        this.passwordInputLayout = findViewById(R.id.IL_password);
        this.emailInputLayout.setHint(email);
        this.passwordInputLayout.setHint(password);

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

    }
}