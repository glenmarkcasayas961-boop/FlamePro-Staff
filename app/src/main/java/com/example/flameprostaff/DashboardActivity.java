package com.example.flameprostaff;

import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.PopupMenu;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);

        View mainView = findViewById(R.id.main);
        View headerContent = findViewById(R.id.headerContent);

        if (mainView != null) {
            ViewCompat.setOnApplyWindowInsetsListener(mainView, (v, insets) -> {
                Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
                if (headerContent != null) {
                    headerContent.setPadding(
                            headerContent.getPaddingLeft(),
                            systemBars.top + 16,
                            headerContent.getPaddingRight(),
                            headerContent.getPaddingBottom()
                    );
                }
                v.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom);
                return insets;
            });
        }

        setupMonthFilter();
        setupYearFilter();
    }

    private void setupMonthFilter() {
        View llMonthFilter = findViewById(R.id.llMonthFilter);
        TextView tvMonthValue = findViewById(R.id.tvMonthValue);

        if (llMonthFilter != null && tvMonthValue != null) {
            final String[] months = new String[]{
                    "January", "February", "March", "April",
                    "May", "June", "July", "August",
                    "September", "October", "November", "December"
            };

            llMonthFilter.setOnClickListener(v -> {
                PopupMenu popup = new PopupMenu(DashboardActivity.this, v);
                Menu menu = popup.getMenu();
                int id = 0;
                for (String m : months) {
                    menu.add(0, id++, Menu.NONE, m);
                }
                popup.setOnMenuItemClickListener(item -> {
                    int selectedIndex = item.getItemId();
                    if (selectedIndex >= 0 && selectedIndex < months.length) {
                        tvMonthValue.setText(months[selectedIndex]);
                    }
                    return true;
                });
                popup.show();
            });
        }
    }

    private void setupYearFilter() {
        View llYearFilter = findViewById(R.id.llYearFilter);
        TextView tvYearValue = findViewById(R.id.tvYearValue);

        if (llYearFilter != null && tvYearValue != null) {
            final String[] years = new String[]{
                    "2018", "2019", "2020", "2021", "2022", "2023",
                    "2024", "2025", "2026", "2027", "2028", "2029", "2030"
            };

            llYearFilter.setOnClickListener(v -> {
                PopupMenu popup = new PopupMenu(DashboardActivity.this, v);
                Menu menu = popup.getMenu();
                int id = 0;
                for (String y : years) {
                    menu.add(0, id++, Menu.NONE, y);
                }
                popup.setOnMenuItemClickListener(item -> {
                    int selectedIndex = item.getItemId();
                    if (selectedIndex >= 0 && selectedIndex < years.length) {
                        tvYearValue.setText(years[selectedIndex]);
                    }
                    return true;
                });
                popup.show();
            });
        }
    }
}