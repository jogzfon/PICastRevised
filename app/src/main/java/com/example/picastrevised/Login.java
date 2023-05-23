package com.example.picastrevised;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_login);

        Button login = findViewById(R.id.btnLogin);
        Button signup = findViewById(R.id.btnCreateAccount);
        EditText username = findViewById(R.id.editTextUsername);
        EditText password = findViewById(R.id.editTextPassword);

        login.setOnClickListener(view -> {
            getUser(username,password);
        });

        signup.setOnClickListener(view -> {
            GoToRegistration();
        });

        sp = getSharedPreferences("User",Context.MODE_PRIVATE);
    }
    private void GoToRegistration(){
        Intent intent = new Intent(this, Registration.class);
        startActivity(intent);
    }
    private void GoToHomePage(){
        Intent intent = new Intent(this, HomePage.class);
        startActivity(intent);
    }
    String tempUserName,tempEmail,tempPassword;
    public void getUser(EditText username,EditText password) {
        FirebaseDatabase db =FirebaseDatabase.getInstance("https://picast-548a1-default-rtdb.asia-southeast1.firebasedatabase.app");
        DatabaseReference myRef;
        myRef = db.getReference().child("Users").child(username.getText().toString());
        String uname = username.getText().toString();
        String upass = password.getText().toString();
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // User with the specified username exists
                    tempUserName = dataSnapshot.child("username").getValue(String.class);
                    tempPassword= dataSnapshot.child("password").getValue(String.class);
                    tempEmail = dataSnapshot.child("email").getValue(String.class);
                    System.out.println(tempUserName+tempPassword+tempEmail);

                    System.out.println(tempUserName+" "+uname);
                    if(!tempUserName.equals(uname)){
                        Toast.makeText(Login.this, "Username is incorrect", Toast.LENGTH_SHORT).show();
                        System.out.println("name is wrong");
                        username.setText("");
                        password.setText("");
                    }else{
                        if(tempPassword.equals(upass)){
                            SharedPreferences.Editor editor = sp.edit();
                            editor.putString("name", uname);
                            editor.commit();
                            Toast.makeText(Login.this, "Log-in successful!", Toast.LENGTH_SHORT).show();
                            GoToHomePage();
                        }else{
                            Toast.makeText(Login.this, "Password is incorrect", Toast.LENGTH_SHORT).show();
                            System.out.println(tempPassword+" "+upass);
                            username.setText("");
                            password.setText("");
                        }
                    }
                    // Perform any actions or return the account object
                } else {
                    // User with the specified username does not exist
                    // Handle the non-existence of the user here
                    Toast.makeText(Login.this, "Account does not exist", Toast.LENGTH_SHORT).show();
                    System.out.println("No alien were found");
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that may occur during the database operation
            }
        });
    }
}