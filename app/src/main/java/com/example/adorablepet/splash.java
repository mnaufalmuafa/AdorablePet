package com.example.adorablepet;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.widget.TextView;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        TextView TVAdorablePet = findViewById(R.id.TVAdorablePet);
        Typeface customfont=Typeface.createFromAsset(getAssets(),"font/YuGothR.ttf");

        TVAdorablePet.setTypeface(customfont);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences mSettings = getSharedPreferences("LoginStatus", Context.MODE_PRIVATE);
                if (mSettings.getBoolean("isLogin",false)) {
                    Intent IntentMainActivity=new Intent(splash.this, MainActivity.class);
                    startActivity(IntentMainActivity);
                }
                else {
                    Intent login=new Intent(splash.this, Login.class);
                    startActivity(login);
                }
                finish();

            }
        },2000);
    }
}
