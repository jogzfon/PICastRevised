package com.example.picastrevised;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
public class Registration extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_registration);


        Button createAccount = findViewById(R.id.btnRegister_CreateAccount);
        EditText newUsername = findViewById(R.id.editTextCreateUsername);
        EditText newEmail = findViewById(R.id.editTextCreateEmail);
        EditText newPassword = findViewById(R.id.editTextCreatePassword);
        EditText confirmPassword = findViewById(R.id.editTextConfirmPassword);
        createAccount.setOnClickListener(view -> {
            String username = newUsername.getText().toString();
            String password = newPassword.getText().toString();
            String email = newEmail.getText().toString();
            if(!username.isEmpty()){
                if(!email.isEmpty()){
                    if(password.length()>=6){
                        if(confirmPassword.getText().toString().equals(password) && (!password.isEmpty() && !confirmPassword.getText().toString().isEmpty())) {
                            Account acc = new Account(username, password, email);
                            FirebaseControl firebaseControl = new FirebaseControl();
                            firebaseControl.AddUser(acc);
                            Toast.makeText(this, "Account successfully created!", Toast.LENGTH_SHORT).show();
                            BackToLogIn();
                        } else
                            Toast.makeText(this, "Password does not match", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT).show();
                } else
                    Toast.makeText(this, "Enter e-mail address", Toast.LENGTH_SHORT).show();
            } else
                Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
        });
    }
    public void BackToLogIn(){
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }
}