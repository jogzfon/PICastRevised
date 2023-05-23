package com.example.picastrevised;

import static androidx.core.content.ContextCompat.startActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.menu.MenuView;
import androidx.appcompat.widget.SearchView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Locale;

public class HomePage extends AppCompatActivity implements View.OnClickListener {
    private RecyclerView recyclerView,fRecyclerView, sRecyclerView;
    private SearchView searchView;

    private ArrayList<ArtData> mList = new ArrayList<ArtData>();
    private ArtAdapter adapter;

    BottomNavigationView bottomNavigationView;
    FloatingActionButton fab;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        setContentView(R.layout.activity_homepage);

        replacementFragment(new Featured());

        //Bottom Nav
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        fab = findViewById(R.id.fabCamera);
        bottomNavigationView.setBackground(null);

        //Recycler and Search
        recyclerView = findViewById(R.id.searchRecycleView);
        searchView = findViewById(R.id.searchView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        addDataToList();
        adapter = new ArtAdapter(mList);
        adapter.setOnItemClickListener(new ArtAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ArtData artData) {
                Intent intent = new Intent(HomePage.this, Product.class);
                intent.putExtra("art", artData);
                startActivity(intent);
                Toast.makeText(HomePage.this, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);

        recyclerView.setVisibility(View.GONE);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                recyclerView.setVisibility(View.GONE);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText.isEmpty()){
                    recyclerView.setVisibility(View.GONE);
                }else{
                    recyclerView.setVisibility(View.VISIBLE);
                }
                filterList(newText);
                return true;
            }
        });

        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_home:
                        // handle click on home button
                        replacementFragment(new Featured());
                        return true;
                    case R.id.nav_notification:
                        // handle click on dashboard button
                        return true;
                    case R.id.nav_favorites:
                        // handle click on notifications button

                        return true;
                    case R.id.nav_account:
                        // handle click on notifications button
                        return true;
                }
                return false;
            }
        });
        if(Login.region == "Philippines"){
            Toast.makeText(this, "Phil account", Toast.LENGTH_SHORT).show();
        }
        Toast.makeText(this, Login.region, Toast.LENGTH_SHORT).show();
    }
    private void filterList(String query){
        if(query != null){
            ArrayList<ArtData> filteredList = new ArrayList<ArtData>();
            for(ArtData artData : mList){
                if(artData.getTitle().toLowerCase(Locale.ROOT).contains(query)){
                    filteredList.add(artData);
                }
            }

            if(filteredList.isEmpty()){
                Toast.makeText(this, "No Data Found", Toast.LENGTH_SHORT).show();
            }else{
                adapter.setFilteredList(filteredList);
            }
        }
    }
    private void addDataToList(){
        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://picast-548a1-default-rtdb.asia-southeast1.firebasedatabase.app").getReference().child("ShopImages");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Clear the list before adding new data
                mList.clear();

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String title = snapshot.getKey();
                    System.out.println();
                    String artImage = snapshot.child("imagekey").getValue(String.class);
                    int isFeatured = snapshot.child("isFeatured").getValue(Integer.class);

                    ArtData artData = new ArtData(title, artImage);
                    mList.add(artData);
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle any errors that occur during data retrieval
                Log.e("Shop", "Error retrieving data: " + databaseError.getMessage());
            }
        });
    }
    public void replacementFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_layout, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnShop:
                replacementFragment(new Shop());
                break;
            case R.id.btnConverter:
                replacementFragment(new Converter());
                break;
            case R.id.fabCamera:
                openCamera();
                break;
            case R.id.cardView:
//                TextView featuredArtTitle = findViewById(R.id.featuredTitle);
//                Toast.makeText(this, featuredArtTitle.getText(), Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private void openCamera() {
        ContentValues values = new ContentValues();
        values.put(MediaStore.Images.Media.TITLE, "new image");
        values.put(MediaStore.Images.Media.DESCRIPTION, "Taken by Camera");
        Uri img_uri = getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values);

        Intent cam_intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        cam_intent.putExtra(MediaStore.EXTRA_OUTPUT, img_uri);
        startActivity(cam_intent);
    }
}