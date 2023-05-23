package com.example.picastrevised;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Cart#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Cart extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public Cart() {
        // Required empty public constructor
    }

    public static Cart newInstance(String param1, String param2) {
        Cart fragment = new Cart();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private RecyclerView recyclerView;
    private ArrayList<ArtData> mList = new ArrayList<>();
    private CartAdapter adapter;
    private TextView totalView;
    public double totalAmount = 0;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        recyclerView = view.findViewById(R.id.cartRecyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        totalView = view.findViewById(R.id.totalAmount);

        adapter = new CartAdapter(mList);
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
                totalAmount = 0;

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String title = snapshot.getKey();
                    String artImage = snapshot.child("imagekey").getValue(String.class);
                    String authorImage = snapshot.child("authorImage").getValue(String.class);
                    String artAuthor = snapshot.child("author").getValue(String.class);
                    double price = snapshot.child("price").getValue(Double.class);
                    int isCart = snapshot.child("isCart").getValue(Integer.class);

                    ArtData artData = new ArtData(title, artImage, authorImage, artAuthor);
                    if (isCart == 1) {
                        mList.add(artData);
                        totalAmount += price;
                    }
                }

                adapter.notifyDataSetChanged();
                totalView.setText(String.valueOf(totalAmount));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Handle database error
            }
        });
    }
}