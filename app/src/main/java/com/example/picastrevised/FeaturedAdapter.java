package com.example.picastrevised;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.List;

public class FeaturedAdapter extends RecyclerView.Adapter<FeaturedAdapter.FeaturedImageViewHolder> {
    private List<ArtData> mList;
    private OnItemClickListener onItemClick;

    public interface OnItemClickListener {
        void onItemClick(ArtData artData);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClick = listener;
    }

    public FeaturedAdapter(List<ArtData> list) {
        if (list == null) {
            throw new IllegalArgumentException("List cannot be null");
        }
        mList = list;
    }

    public void setFilteredList(List<ArtData> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    public class FeaturedImageViewHolder extends RecyclerView.ViewHolder {
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
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // Store image in a bitmap variable
                    Toast.makeText(itemView.getContext(), "Image clicked!", Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    @NonNull
    @Override
    public FeaturedImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_featuredart, parent, false);
        return new FeaturedImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FeaturedImageViewHolder holder, int position) {
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

        holder.itemView.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(artData);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}