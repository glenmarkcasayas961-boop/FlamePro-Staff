package com.example.flameprostaff;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class StaffRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_staff_register);

        // Back button
        ImageView ivBack = findViewById(R.id.ivBack);
        if (ivBack != null) {
            ivBack.setOnClickListener(v -> finish());
        }

        // Back to login text
        TextView tvBackToLogin = findViewById(R.id.tvBackToLogin);
        if (tvBackToLogin != null) {
            tvBackToLogin.setOnClickListener(v -> finish());
        }

        setupPasswordToggle(R.id.etPassword, R.id.ivPasswordToggle);
        setupPasswordToggle(R.id.etConfirmPassword, R.id.ivConfirmPasswordToggle);
    }

    private void setupPasswordToggle(int editTextId, int toggleId) {
        final EditText editText = findViewById(editTextId);
        final ImageView toggle = findViewById(toggleId);

        if (editText != null && toggle != null) {
            toggle.setOnClickListener(new View.OnClickListener() {
                private boolean isVisible = false;

                @Override
                public void onClick(View v) {
                    if (isVisible) {
                        editText.setTransformationMethod(PasswordTransformationMethod.getInstance());
                        toggle.setImageResource(R.drawable.ic_eye);
                    } else {
                        editText.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                        toggle.setImageResource(R.drawable.ic_eye_off);
                    }
                    isVisible = !isVisible;
                    editText.setSelection(editText.getText().length());
                }
            });
        }
    }
}