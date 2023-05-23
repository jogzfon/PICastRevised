package com.example.picastrevised;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Featured#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Featured extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private RecyclerView recyclerView;
    private ArrayList<ArtData> mList = new ArrayList<ArtData>();
    private FeaturedAdapter adapter;
    public Featured() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Featured.
     */
    // TODO: Rename and change types and number of parameters
    public static Featured newInstance(String param1, String param2) {
        Featured fragment = new Featured();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_featured, container, false);
        recyclerView = view.findViewById(R.id.cartRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
        addDataToList();
        adapter = new FeaturedAdapter(mList);
        recyclerView.setAdapter(adapter);
        return view;
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
                    String authorImage = snapshot.child("authorImage").getValue(String.class);
                    String artAuthor = snapshot.child("author").getValue(String.class);
                    int isFeatured = snapshot.child("isFeatured").getValue(Integer.class);

                    ArtData artData = new ArtData(title, artImage,authorImage,artAuthor);
                    if(isFeatured==1){
                        mList.add(artData);
                    }
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
}