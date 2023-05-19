package com.example.picastrevised;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopImageViewHolder> {

    private List<ArtData> mList;

    public ShopAdapter(List<ArtData> mList) {
        this.mList = mList;
    }

    public void setFilteredList(List<ArtData> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class ShopImageViewHolder extends RecyclerView.ViewHolder {
        public final ImageView shopArtImage;
        public final TextView shopArtTitle;

        public ShopImageViewHolder(View itemView) {
            super(itemView);
            shopArtImage = itemView.findViewById(R.id.shopArtImage);
            shopArtTitle = itemView.findViewById(R.id.shopArtTitle);
        }
    }

    @Override
    public ShopImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_shopart, parent, false);
        return new ShopImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ShopImageViewHolder holder, int position) {
        ArtData artData = mList.get(position);
        Glide.with(holder.itemView.getContext())
                .load(artData.getArtImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.shopArtImage);
//        holder.shopArtImage.setImageResource(artData.getArtImage());
        holder.shopArtTitle.setText(artData.getTitle());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    // Set up grid layout manager with 3 columns
    public void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new GridLayoutManager(recyclerView.getContext(), 3));
    }
}
