package com.example.picastrevised;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class Registration extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner spinner;
    String region;
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
        spinner = findViewById(R.id.spinRegion);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.regions_array,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(this);

        createAccount.setOnClickListener(view -> {
            String username = newUsername.getText().toString();
            String password = newPassword.getText().toString();
            String email = newEmail.getText().toString();
            if(!username.isEmpty()){
                if(!email.isEmpty()){
                    if(password.length()>=6){
                        if(confirmPassword.getText().toString().equals(password) && (!password.isEmpty() && !confirmPassword.getText().toString().isEmpty())) {
                            Account acc = new Account(username, password, email, region);
                            FirebaseControl firebaseControl = new FirebaseControl();
                            boolean[] succ = firebaseControl.AddUser(acc);
                            if(succ[0] == true) {
                                Toast.makeText(this, "Account successfully created!", Toast.LENGTH_SHORT).show();
                                BackToLogIn();
                            }else {
                                Toast.makeText(this, "Username is already taken", Toast.LENGTH_SHORT).show();
                                newUsername.setText("");
                            }
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

    //spinner events, for the region selection
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        region = adapterView.getItemAtPosition(i).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}