package com.example.adorablepet.contoller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.adorablepet.R;
import com.example.adorablepet.adapter.TokoAdapter;
import com.example.adorablepet.model.Produk;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class DetailProduk extends AppCompatActivity {

    private TokoAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_produk);

        SharedPreferences mSettings = getSharedPreferences("dataProduk", Context.MODE_PRIVATE);
        int id = mSettings.getInt("id",0);
        String kategori;
        mSettings = getSharedPreferences("Kategori", Context.MODE_PRIVATE);
        if (mSettings.getBoolean("isFood",false)) {
            kategori = "Food";
        }
        else {
            kategori = "Drink";
        }

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        Typeface fontBold=Typeface.createFromAsset(getAssets(),"font/YuGothB.ttf");
        Typeface fontRegular=Typeface.createFromAsset(getAssets(),"font/YuGothR.ttf");

        TextView TVDetailProduk = findViewById(R.id.TVDetailProduk);
        TextView TVOpenMaps = findViewById(R.id.TVOpenMaps);
        TextView TVLabelName = findViewById(R.id.TVLabelName);
        TextView TVLabelUsageFor = findViewById(R.id.TVLabelUsageFor);
        TextView TVLabelAvailableStore = findViewById(R.id.TVLabelAvailableStore);
        TextView TVLabelDesc = findViewById(R.id.TVLabelDescription);
        final TextView TVName = findViewById(R.id.TVName);
        final TextView TVUsageFor = findViewById(R.id.TVUsageFor);
        final TextView TVDesc = findViewById(R.id.TVDescription);
        TextView TVTitikDua1 = findViewById(R.id.TVTitikDua1);
        TextView TVTitikDua2 = findViewById(R.id.TVTitikDua2);
        TextView TVTitikDua3 = findViewById(R.id.TVTitikDua3);
        TextView TVTitikDua4 = findViewById(R.id.TVTitikDua4);

        final ImageView IVProduk = findViewById(R.id.IVProduk);
        final ImageView BtnBack = findViewById(R.id.BtnBack);
        final View viewButton = findViewById(R.id.viewButton);

        TVDetailProduk.setTypeface(fontBold);
        TVOpenMaps.setTypeface(fontBold);
        TVLabelName.setTypeface(fontRegular);
        TVLabelUsageFor.setTypeface(fontRegular);
        TVLabelAvailableStore.setTypeface(fontRegular);
        TVLabelDesc.setTypeface(fontRegular);

        TVName.setTypeface(fontRegular);
        TVUsageFor.setTypeface(fontRegular);
        TVDesc.setTypeface(fontRegular);

        TVTitikDua1.setTypeface(fontRegular);
        TVTitikDua2.setTypeface(fontRegular);
        TVTitikDua3.setTypeface(fontRegular);
        TVTitikDua4.setTypeface(fontRegular);

        //set home selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        bottomNavigationView.setItemIconTintList(null);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference reference = database.getReference().child("AdorablePet").child("Produk").child(kategori).child(String.valueOf(id));

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final Produk produk = dataSnapshot.getValue(Produk.class);
                TVName.setText(produk.nama);
                TVUsageFor.setText(produk.usageFor);
                TVDesc.setText(produk.description);
                Picasso.get().load(produk.linkFoto).into(IVProduk);
                viewButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = produk.getUrlToko();
                        Intent i = new Intent(Intent.ACTION_VIEW);
                        i.setData(Uri.parse(url));
                        startActivity(i);
                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "Periksa koneksi internet anda lalu coba lagi", Toast.LENGTH_LONG);
                toast.show();
            }
        });

        reference = database.getReference().child("AdorablePet").child("Toko").child(kategori).child(String.valueOf(id));

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                RecyclerView rv = findViewById(R.id.rv);
                ArrayList<String> dataToko = new ArrayList<>();
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String toko = dataSnapshot1.getValue(String.class);
                    dataToko.add(toko);
                }
                adapter = new TokoAdapter(dataToko,DetailProduk.this);
                rv.setLayoutManager(new LinearLayoutManager(DetailProduk.this));
                rv.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast toast = Toast.makeText(getApplicationContext(), "Periksa koneksi internet anda lalu coba lagi", Toast.LENGTH_LONG);
                toast.show();
            }
        });

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

        BtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
