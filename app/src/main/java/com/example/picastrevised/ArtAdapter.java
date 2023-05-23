package com.example.picastrevised;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.squareup.picasso.Picasso;

import java.util.List;

public class ArtAdapter extends RecyclerView.Adapter<ArtAdapter.ArtImageViewHolder> {

    private List<ArtData> mList;
    private ArtAdapter.OnItemClickListener onItemClick;

    public interface OnItemClickListener {
        void onItemClick(ArtData artData);
    }

    public void setOnItemClickListener(ArtAdapter.OnItemClickListener listener) {
        onItemClick = listener;
    }

    public ArtAdapter(List<ArtData> mList) {
        this.mList = mList;
    }
    public void setFilteredList(List<ArtData> mList){
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
        ArtData artData = mList.get(position);
        Picasso.get().load(mList.get(position).getArtImage()).into(holder.artImage);
        //holder.artImage.setImageResource(mList.get(position).getArtImage()));
        holder.titleTv.setText(mList.get(position).getTitle());
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
