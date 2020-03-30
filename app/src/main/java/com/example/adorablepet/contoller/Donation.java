package com.example.adorablepet.contoller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.adorablepet.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Donation extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setItemIconTintList(null);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.donation);

        //Perform item selected listener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.missing:
                        startActivity(new Intent(getApplicationContext(),missing.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.checkup:
                        startActivity(new Intent(getApplicationContext(),checkup.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.home :
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                    case R.id.donation:
                        return true;
                    case R.id.deposit :
                        startActivity(new Intent(getApplicationContext(),deposit.class));
                        overridePendingTransition(0,0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}
