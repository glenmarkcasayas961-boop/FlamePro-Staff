package com.example.flameprostaff;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        setupPasswordToggle();
        setupNavigation();
    }

    private void setupNavigation() {
        View btnLogin = findViewById(R.id.btnLogin);
        if (btnLogin != null) {
            btnLogin.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, DashboardActivity.class));
            });
        }

        View btnCreateAccount = findViewById(R.id.btnCreateAccount);
        if (btnCreateAccount != null) {
            btnCreateAccount.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, StaffRegisterActivity.class));
            });
        }

        View btnRegister = findViewById(R.id.btnRegister);
        if (btnRegister != null) {
            btnRegister.setOnClickListener(v -> {
                startActivity(new Intent(MainActivity.this, StaffRegisterActivity.class));
            });
        }
    }

    private void setupPasswordToggle() {
        final EditText etPassword = findViewById(R.id.etPassword);
        final ImageView ivPasswordToggle = findViewById(R.id.ivPasswordToggle);

        if (etPassword != null && ivPasswordToggle != null) {
            ivPasswordToggle.setOnClickListener(new View.OnClickListener() {
                private boolean isPasswordVisible = false;

                @Override
                public void onClick(View v) {
                    if (isPasswordVisible) {
                        // Hide Password
                        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        ivPasswordToggle.setImageResource(R.drawable.ic_eye);
                    } else {
                        // Show Password
                        etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        ivPasswordToggle.setImageResource(R.drawable.ic_eye_off);
                    }
                    isPasswordVisible = !isPasswordVisible;
                    // Move cursor to the end
                    etPassword.setSelection(etPassword.getText().length());
                }
            });
        }
    }
}