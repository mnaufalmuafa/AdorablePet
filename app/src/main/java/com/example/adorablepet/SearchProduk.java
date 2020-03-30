package com.example.adorablepet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adorablepet.adapter.ProdukAdapter;
import com.example.adorablepet.model.Produk;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SearchProduk extends AppCompatActivity {

    private ArrayList<Produk> dataProduk = new ArrayList<>();
    private ProdukAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_produk);

        final String kategori;
        SharedPreferences mSettings = getSharedPreferences("Kategori", Context.MODE_PRIVATE);
        if (mSettings.getBoolean("isFood",false)) {
            kategori = "Food";
        }
        else {
            kategori = "Drink";
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        ImageView BtnBack = findViewById(R.id.BtnBack);
        TextView TVAnimalCare = findViewById(R.id.TVAnimalCare);
        final TextView TVWarning = findViewById(R.id.TVWaring);
        final RecyclerView rv = findViewById(R.id.rv);
        Typeface customfont=Typeface.createFromAsset(getAssets(),"font/YuGothR.ttf");
        final EditText ETSearchBar = findViewById(R.id.ETSearchBar);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference databaseReference = database.getReference().child("AdorablePet").child("Produk").child(kategori);

        bottomNavigationView.setItemIconTintList(null);
        TVAnimalCare.setTypeface(customfont);
        TVWarning.setVisibility(View.INVISIBLE);

        final ValueEventListener catFoodValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataProduk.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Produk produk = dataSnapshot1.getValue(Produk.class);
                    dataProduk.add(produk);
                }
                adapter = new ProdukAdapter(dataProduk);
                rv.setLayoutManager(new GridLayoutManager(SearchProduk.this, 3));
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "Periksa koneksi internet anda lalu coba lagi", Toast.LENGTH_LONG);
                toast.show();
            }
        };
        final ValueEventListener dogDrinkValueListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                dataProduk.clear();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    Produk produk = dataSnapshot1.getValue(Produk.class);
                    dataProduk.add(produk);
                }
                adapter = new ProdukAdapter(dataProduk);
                rv.setLayoutManager(new GridLayoutManager(SearchProduk.this, 3));
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "Periksa koneksi internet anda lalu coba lagi", Toast.LENGTH_LONG);
                toast.show();
            }
        };

        ETSearchBar.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {
                TVWarning.setVisibility(View.INVISIBLE);
                if (i == EditorInfo.IME_ACTION_SEARCH) {
                    dataProduk.clear();
                    rv.setLayoutManager(new GridLayoutManager(SearchProduk.this, 3));
                    rv.setAdapter(adapter);
                    final String keyword = ETSearchBar.getText().toString().toLowerCase();
                    if (kategori.equals("Food")) {
                        if (keyword.contains("cat") || keyword.contains("food")) {
                            databaseReference.addListenerForSingleValueEvent(catFoodValueListener);
                        }
                        else {
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    dataProduk.clear();
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        Produk produk = dataSnapshot1.getValue(Produk.class);
                                        if (produk.nama.toLowerCase().contains(keyword))
                                            dataProduk.add(produk);
                                    }
                                    if (dataProduk.size() > 0) {
                                        adapter = new ProdukAdapter(dataProduk);
                                        rv.setLayoutManager(new GridLayoutManager(SearchProduk.this, 3));
                                        rv.setAdapter(adapter);
                                    }
                                    else {
                                        TVWarning.setVisibility(View.VISIBLE);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Periksa koneksi internet anda lalu coba lagi", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });
                        }
                    }
                    else { // If the category is drink
                        if (keyword.contains("dog") || keyword.contains("drink")) {
                            databaseReference.addListenerForSingleValueEvent(dogDrinkValueListener);
                        }
                        else {
                            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    dataProduk.clear();
                                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                                        Produk produk = dataSnapshot1.getValue(Produk.class);
                                        if (produk.nama.toLowerCase().contains(keyword))
                                            dataProduk.add(produk);
                                    }
                                    if (dataProduk.size() > 0) {
                                        adapter = new ProdukAdapter(dataProduk);
                                        rv.setLayoutManager(new GridLayoutManager(SearchProduk.this, 3));
                                        rv.setAdapter(adapter);
                                    }
                                    else {
                                        TVWarning.setVisibility(View.VISIBLE);
                                    }
                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                    Toast toast = Toast.makeText(getApplicationContext(), "Periksa koneksi internet anda lalu coba lagi", Toast.LENGTH_LONG);
                                    toast.show();
                                }
                            });
                        }
                    }
                    return true;
                }
                return false;
            }
        });

        bottomNavigationView.setSelectedItemId(R.id.home);

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

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
