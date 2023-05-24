package com.example.picastrevised;

import androidx.appcompat.app.ActionBar;
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

import com.bumptech.glide.Glide;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class Product extends AppCompatActivity {

    private CartData cartData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_product);

        TextView imgTitle = findViewById(R.id.imgTitle);
        TextView price = findViewById(R.id.txtprice);
        ImageView imgProduct = findViewById(R.id.imgProduct);

        Button btnAdd = findViewById(R.id.btnAddToCart);
        Button btnAddToFav = findViewById(R.id.btnAddToFavorites);

        // Retrieve the stored artTitle from SharedPreferences
        SharedPreferences sharedPreferences1 = getSharedPreferences("Art", MODE_PRIVATE);
        String artTitle = sharedPreferences1.getString("artTitle", "");
        SharedPreferences sharedPreferences2 = getSharedPreferences("User", MODE_PRIVATE);
        String uname = sharedPreferences2.getString("username", "");

        // Find the product in the Firebase database
        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://picast-548a1-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference()
                .child("ShopImages");
        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String imgtitle = snapshot.getKey();
                    if (imgtitle.equals(artTitle)) {
                        String artImage = snapshot.child("imagekey").getValue(String.class);
                        String authorImage = snapshot.child("authorImage").getValue(String.class);
                        String artAuthor = snapshot.child("author").getValue(String.class);
                        Double artPrice = snapshot.child("price").getValue(Double.class);

                        cartData = new CartData(imgtitle, artImage, artPrice, artAuthor, uname);
                        Picasso.get().load(cartData.getArtImage()).into(imgProduct);
                        imgTitle.setText(cartData.getTitle());

                        if(Login.region.equals("United States")) {
                            price.setText("$ "+String.format("%.2f", cartData.getArtPriceUSD()));
                        }else if(Login.region.equals("Japan")) {
                            price.setText("¥ "+String.format("%.2f", cartData.getArtPriceJPY()));
                        }else if(Login.region.equals("Singapore")) {
                            price.setText("SGD "+String.format("%.2f", cartData.getArtPriceSGD()));
                        }else
                            price.setText("₱ "+String.format("%.2f", artPrice));
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Shop", "Error retrieving data: " + databaseError.getMessage());
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://picast-548a1-default-rtdb.asia-southeast1.firebasedatabase.app");
                DatabaseReference shopImagesRef = database.getReference("ShopImages");
                TextView title = findViewById(R.id.imgTitle);
                DatabaseReference adventOfLightRef = shopImagesRef.child(title.getText().toString());

                adventOfLightRef.child("isCart").setValue(1);
                Toast.makeText(Product.this, "Added to Cart", Toast.LENGTH_SHORT).show();
            }
        });
        btnAddToFav.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseDatabase database = FirebaseDatabase.getInstance("https://picast-548a1-default-rtdb.asia-southeast1.firebasedatabase.app");
                DatabaseReference shopImagesRef = database.getReference("ShopImages");
                TextView title = findViewById(R.id.imgTitle);
                DatabaseReference adventOfLightRef = shopImagesRef.child(title.getText().toString());

                adventOfLightRef.child("isFavorite").setValue(1);
                Toast.makeText(Product.this, "Added to Favorites", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Product.this, HomePage.class);
                startActivity(intent);
            }
        });
    }
}