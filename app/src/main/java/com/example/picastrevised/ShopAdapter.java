package com.example.picastrevised;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.ShopImageViewHolder> {

    private List<ArtData> mList;
    private OnItemClickListener onItemClick;

    public interface OnItemClickListener {
        void onItemClick(ArtData artData);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClick = listener;
    }

    public ShopAdapter(List<ArtData> mList) {
        this.mList = mList;
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

        holder.shopArtTitle.setText(artData.getTitle());
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
