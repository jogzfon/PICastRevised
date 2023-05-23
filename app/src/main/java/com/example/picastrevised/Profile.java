package com.example.picastrevised;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    SharedPreferences sp;
    TextView nName;
    TextView email;
    TextView occupation;
    TextView bio;
    TextView region;
    ImageView profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_profile);

        nName = findViewById(R.id.txtNickname);
        email = findViewById(R.id.txtEmail);
        occupation = findViewById(R.id.txtOccupation);
        region =    findViewById(R.id.txtRegion);
        profile = findViewById(R.id.imgProfile);
        bio = findViewById(R.id.txtBio);
        Button btnLogout = findViewById(R.id.btnLogout);
        ImageButton btnBack = findViewById(R.id.imgBtnBack);
        sp = getSharedPreferences("User", Context.MODE_PRIVATE);
        String uname = sp.getString("username","");
        nName.setText(uname);

        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://picast-548a1-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference()
                .child("UsersWithRegion");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String imgtitle = snapshot.getKey();
                    if (imgtitle.equals(uname)) {
                        String image = snapshot.child("profile").getValue(String.class);
                        String em = snapshot.child("email").getValue(String.class);
                        String biot = snapshot.child("bio").getValue(String.class);
                        String rg = snapshot.child("region").getValue(String.class);
                        String job = snapshot.child("occupation").getValue(String.class);

                        bio.setText(biot);
                        region.setText(rg);
                        email.setText(em);
                        occupation.setText(job);
                        Picasso.get().load(image).into(profile);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Shop", "Error retrieving data: " + databaseError.getMessage());
            }
        });
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, Login.class);
                startActivity(intent);
                finish();
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Profile.this, HomePage.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
