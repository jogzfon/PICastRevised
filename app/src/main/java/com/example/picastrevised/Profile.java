package com.example.picastrevised;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Profile extends AppCompatActivity {
    TextView nName;
    TextView firstName;
    TextView lastName;
    TextView job;
    TextView bio;
    TextView balance;
    ImageView profile;
    private Account acc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        nName = findViewById(R.id.txtNickname);
//        firstName = findViewById(R.id.txtFirstname);
//        lastName = findViewById(R.id.txtLastname);
//        job = findViewById(R.id.txtJob);
//        bio = findViewById(R.id.txtBio);
        balance = findViewById(R.id.txtBalance);
        profile = findViewById(R.id.imgProfile);
        Button btnLogout = findViewById(R.id.btnLogout);

        SharedPreferences sharedPreferences = getSharedPreferences("User", MODE_PRIVATE);
        String uname = sharedPreferences.getString("username", "");
        System.out.println(uname);
        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://picast-548a1-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("UsersWithRegion").child(uname);
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String firstname = dataSnapshot.child("firstname").getValue(String.class);
                    String lastname = dataSnapshot.child("lastname").getValue(String.class);
                    String pJob = dataSnapshot.child("job").getValue(String.class);
                    String pBio = dataSnapshot.child("bio").getValue(String.class);
                    Double pBalance = dataSnapshot.child("balance").getValue(Double.class);
                    String pProfile = dataSnapshot.child("profile").getValue(String.class);

//                    acc = new Account(firstname, lastname, pBio, pProfile, pBalance);
//                    nName.setText(acc.getFirstname());
//                    firstName.setText(firstname);
//                    lastName.setText(lastname);
//                    job.setText(pJob);
//                    bio.setText(pBio);
                    balance.setText(String.valueOf(pBalance));
                    Picasso.get().load(pProfile).into(profile);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Profile", "Error retrieving data: " + databaseError.getMessage());
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
    }
}
