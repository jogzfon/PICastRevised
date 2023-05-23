package com.example.picastrevised;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Featured extends Fragment {

    private RecyclerView recyclerView;
    private ArrayList<ArtData> mList = new ArrayList<>();
    private FeaturedAdapter adapter;

    public Featured() {
        // Required empty public constructor
    }

    public static Featured newInstance() {
        return new Featured();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_featured, container, false);
        recyclerView = view.findViewById(R.id.featuredRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        adapter = new FeaturedAdapter(mList);
        adapter.setOnItemClickListener(new FeaturedAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ArtData artData) {
                Intent intent = new Intent(requireContext(), Product.class);
                intent.putExtra("art", artData);
                startActivity(intent);
                Toast.makeText(requireContext(), "Clicked", Toast.LENGTH_SHORT).show();
            }
        });
        recyclerView.setAdapter(adapter);
        addDataToList();
        return view;
    }

    private void addDataToList() {
        DatabaseReference databaseRef = FirebaseDatabase.getInstance("https://picast-548a1-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference()
                .child("ShopImages");

        databaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                mList.clear();
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String title = snapshot.getKey();
                    String artImage = snapshot.child("imagekey").getValue(String.class);
                    String authorImage = snapshot.child("authorImage").getValue(String.class);
                    String artAuthor = snapshot.child("author").getValue(String.class);
                    int isFeatured = snapshot.child("isFeatured").getValue(Integer.class);

                    ArtData artData = new ArtData(title, artImage, authorImage, artAuthor);
                    if (isFeatured == 1) {
                        mList.add(artData);
                    }
                }

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("Shop", "Error retrieving data: " + databaseError.getMessage());
            }
        });
    }
}