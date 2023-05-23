package com.example.picastrevised;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
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
    public boolean[] AddUser(Account acc){
        myRef = db.getReference("UsersWithRegion");
        userName = acc.getUsername();
        final boolean[] added = new boolean[1];
        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot data : snapshot.getChildren()){
                    if(data.child(userName).exists()){
                        System.out.println("User already exists");
                        added[0] = false;
                    }else{
                        myRef.child(userName).setValue(acc);
                        myRef.child(userName).child("region").setValue(acc.getRegion());
                        System.out.println("User Added Successfully!");
                        added[0] = true;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        return added;
    }
//    public void AddToCart(CartData cart){
//        db = FirebaseDatabase.getInstance();
//        myRef = db.getReference("Cart");
//        artTitle = cart.getTitle();
//        myRef.child(artTitle).setValue(cart);
//    }
    public void AddToCart(CartData cart){
        myRef = db.getReference("Cart");
        itemName = cart.getTitle();
        DatabaseReference priceRef = db.getReference("ShopImages").child(itemName);
        priceRef.addValueEventListener(new ValueEventListener() {
            double price;
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                price = snapshot.child("price").getValue(Double.class);
                cart.setArtPrice(price);
                if(Login.region.equals("United States")) {
                    myRef.child(Login.globalUsername).child(itemName).child("artImage").setValue(cart.getArtImage());
                    myRef.child(Login.globalUsername).child(itemName).child("artPrice").setValue(cart.getArtPriceUSD());
                }else if(Login.region.equals("Japan")) {
                    myRef.child(Login.globalUsername).child(itemName).child("artImage").setValue(cart.getArtImage());
                    myRef.child(Login.globalUsername).child(itemName).setValue(cart.getArtPriceJPY());
                }else if(Login.region.equals("Singapore")) {
                    myRef.child(Login.globalUsername).child(itemName).child("artImage").setValue(cart.getArtImage());
                    myRef.child(Login.globalUsername).child(itemName).setValue(cart.getArtPriceSGD());
                }else {
                    myRef.child(Login.globalUsername).child(itemName).child("artImage").setValue(cart.getArtImage());
                    myRef.child(Login.globalUsername).child(itemName).child("artPrice").setValue(cart.getArtPrice());
                }
                System.out.println("Item added to Cart!");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}
