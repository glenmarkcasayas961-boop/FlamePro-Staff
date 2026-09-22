package com.example.flameprostaff;

import android.graphics.Color;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class StaffRegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_staff_register);

        // Adjust top margins to handle system status bar insets cleanly
        View ivBack = findViewById(R.id.ivBack);
        View headerBranding = findViewById(R.id.headerBranding);
        View mainView = findViewById(R.id.main);

        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());

                if (ivBack != null) {
                    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) ivBack.getLayoutParams();
                    lp.topMargin = systemBars.top + 8;
                    ivBack.setLayoutParams(lp);
                }

                if (headerBranding != null) {
                    ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) headerBranding.getLayoutParams();
                    lp.topMargin = systemBars.top + 36;
                    headerBranding.setLayoutParams(lp);
                }

                v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        // Back button
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
        setupRoleSelector();
        setupTermsCheckbox();
    }

    private void setupTermsCheckbox() {
        View llTerms = findViewById(R.id.llTerms);
        CheckBox cbTerms = findViewById(R.id.cbTerms);
        View tvTerms = findViewById(R.id.tvTerms);

        if (cbTerms != null) {
            View.OnClickListener toggleCheck = v -> cbTerms.setChecked(!cbTerms.isChecked());
            if (llTerms != null) {
                llTerms.setOnClickListener(toggleCheck);
            }
            if (tvTerms != null) {
                tvTerms.setOnClickListener(toggleCheck);
            }
        }
    }

    private void setupRoleSelector() {
        View flRole = findViewById(R.id.flRole);
        TextView tvRole = findViewById(R.id.tvRole);

        if (flRole != null && tvRole != null) {
            final String[] roles = new String[]{
                    "Manager",
                    "Sales Staff",
                    "Inventory Staff",
                    "Service Technician",
                    "Delivery Staff"
            };

            View.OnClickListener clickListener = v -> new AlertDialog.Builder(StaffRegisterActivity.this)
                    .setTitle("Select Role")
                    .setItems(roles, (dialog, which) -> {
                        tvRole.setText(roles[which]);
                        tvRole.setTextColor(Color.parseColor("#111111"));
                    })
                    .show();

            flRole.setOnClickListener(clickListener);
            tvRole.setOnClickListener(clickListener);
        }
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