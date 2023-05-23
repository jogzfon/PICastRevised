package com.example.picastrevised;

import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class FirebaseControl {
    String artTitle,userName,itemName;
    FirebaseDatabase db;
    DatabaseReference myRef;
    DataSnapshot dataSnapshot;
    public FirebaseControl(){
        db = FirebaseDatabase.getInstance("https://picast-548a1-default-rtdb.asia-southeast1.firebasedatabase.app");
    }
    public void GetShopImages(){

    }
    public void AddArt(ArtData data){
        db = FirebaseDatabase.getInstance();
        myRef = db.getReference("Artworks");
        artTitle = data.getTitle();
        myRef.child(artTitle).setValue(data);
    }
    public void AddUser(Account acc){
        DatabaseReference postRef = FirebaseDatabase.getInstance().getReference().child("Users");
        System.out.println("User Added Successfully!");
        myRef = db.getReference("Users");
        userName = acc.getUsername();
        myRef.child(userName).setValue(acc);
    }
    public void AddToCart(ArtData art){
        DatabaseReference postRef = FirebaseDatabase.getInstance().getReference().child("Cart");
        System.out.println("Item added to Cart!");
        myRef = db.getReference("Cart");
        itemName = art.getTitle();
        myRef.child(itemName).setValue(art);
    }
}
