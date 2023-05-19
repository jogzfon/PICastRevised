package com.example.picastrevised;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedImageViewHolder> {

    private List<ArtData> mList;

    public FeaturedAdapter(List<ArtData> mList) {
        if (mList == null) {
            throw new IllegalArgumentException("List cannot be null");
        }
        this.mList = mList;
    }

    public void setFilteredList(List<ArtData> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }

    public static class FeaturedImageViewHolder extends RecyclerView.ViewHolder {
        public final ImageView featuredArtImage;
        public final ImageView featuredAuthorImage;
        public final TextView featuredArtTitle;
        public final TextView featuredAuthorName;


        public FeaturedImageViewHolder(View itemView) {
            super(itemView);
            featuredArtImage = itemView.findViewById(R.id.featuredImage);
            featuredArtTitle = itemView.findViewById(R.id.featuredTitle);
            featuredAuthorName = itemView.findViewById(R.id.featuredAuthorName);
            featuredAuthorImage = itemView.findViewById(R.id.featuredAuthorImage);
        }
    }

    @Override
    public FeaturedImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_featuredart, parent, false);
        return new FeaturedAdapter.FeaturedImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FeaturedImageViewHolder holder, int position) {
        ArtData artData = mList.get(position);
        Glide.with(holder.itemView)
                .load(artData.getArtImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.featuredArtImage);

        holder.featuredArtTitle.setText(artData.getTitle());
        holder.featuredAuthorName.setText(artData.getAuthor());
        Glide.with(holder.itemView)
                .load(artData.getAuthorImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.featuredAuthorImage);
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public void setLayoutManager(RecyclerView recyclerView) {
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
    }
}
