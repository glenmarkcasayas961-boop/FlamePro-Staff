package com.example.flameprostaff;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class StaffLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_login);
        setupPasswordToggle();
        setupNavigation();
    }

    private void setupNavigation() {
        View btnRegister = findViewById(R.id.btnRegister);
        if (btnRegister != null) {
            btnRegister.setOnClickListener(v -> {
                startActivity(new Intent(StaffLoginActivity.this, StaffRegisterActivity.class));
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
                        etPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        ivPasswordToggle.setImageResource(R.drawable.ic_eye);
                    } else {
                        etPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        ivPasswordToggle.setImageResource(R.drawable.ic_eye_off);
                    }
                    isPasswordVisible = !isPasswordVisible;
                    etPassword.setSelection(etPassword.getText().length());
                }
            });
        }
    }
}