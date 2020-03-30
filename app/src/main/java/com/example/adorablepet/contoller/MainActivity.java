package com.example.adorablepet.contoller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.adorablepet.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Initialize and assign variable
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        LinearLayout LLFood = findViewById(R.id.LLFood);
        LinearLayout LLDrink = findViewById(R.id.LLDrink);
        TextView TVAnimalCare = findViewById(R.id.TVAnimalCare);
        TextView TVFood = findViewById(R.id.TVFood);
        TextView TVDrink = findViewById(R.id.TVDrink);
        Typeface customfont=Typeface.createFromAsset(getAssets(),"font/YuGothR.ttf");

        bottomNavigationView.setItemIconTintList(null);
        TVAnimalCare.setTypeface(customfont);
        TVFood.setTypeface(customfont);
        TVDrink.setTypeface(customfont);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

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
                        return true;
                    case R.id.donation:
                        startActivity(new Intent(getApplicationContext(),Donation.class));
                        overridePendingTransition(0,0);
                        finish();
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

        LLFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences mSettings = getSharedPreferences("Kategori", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putBoolean("isFood",true);
                editor.apply();
                startActivity(new Intent(MainActivity.this,SearchProduk.class));
            }
        });

        LLDrink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences mSettings = getSharedPreferences("Kategori", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mSettings.edit();
                editor.putBoolean("isFood",false);
                editor.apply();
                startActivity(new Intent(MainActivity.this,SearchProduk.class));
            }
        });
    }
}
