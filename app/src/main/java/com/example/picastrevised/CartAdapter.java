package com.example.picastrevised;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ArtImageViewHolder> {

    private List<CartData> mList;

    public CartAdapter(List<CartData> mList) {
        this.mList = mList;
    }
    public void setFilteredList(List<CartData> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    public static class ArtImageViewHolder extends RecyclerView.ViewHolder {
        public final ImageView artImage;
        public final TextView titleTv;

        public ArtImageViewHolder(View itemView) {
            super(itemView);
            artImage = itemView.findViewById(R.id.artImage);
            titleTv = itemView.findViewById(R.id.artTitle);
            View view = itemView;
            view.setOnClickListener(view1 -> {
                Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public ArtImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_art_item, parent, false);
        return new ArtImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ArtImageViewHolder holder, int position) {
        Picasso.get().load(mList.get(position).getArtImage()).into(holder.artImage);
        //holder.artImage.setImageResource(mList.get(position).getArtImage()));
        holder.titleTv.setText(mList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
