package com.example.adorablepet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.adorablepet.model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Login extends AppCompatActivity {
    private ArrayList<User> ArrayListUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button BtnLogin = findViewById(R.id.BtnLogin);

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("TAG","Kesini");

                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("AdorablePet").child("user");

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference().child("AdorablePet").child("child");

                myRef.setValue("Hello, World!");
                databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        ArrayListUser = new ArrayList<>();
                        for(DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                            User user = dataSnapshot1.getValue(User.class);
                            ArrayListUser.add(user);
                        }
                        EditText ETUsername = findViewById(R.id.ETUsername);
                        EditText ETPassword = findViewById(R.id.ETPassword);
                        for (User user : ArrayListUser) {
                            if (ETUsername.getText().toString().equals(user.getUsername()) && ETPassword.getText().toString().equals(user.getPassword())) {
                                Intent home=new Intent(Login.this, MainActivity.class);
                                startActivity(home);
                                finish();
                            }
                        }
                        //Toast.makeText(this,"Username atau password salah",Toast.LENGTH_LONG).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.d("TAG","Gagal mengambil data user");
                    }
                });
            }
        });
    }
}
