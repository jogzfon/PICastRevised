package com.example.picastrevised;

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

import org.w3c.dom.Text;

import java.util.List;

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.FavoriteImageViewHolder> {
    private List<ArtData> mList;
    private OnItemClickListener onItemClick;
    public interface OnItemClickListener {
        void onItemClick(ArtData artData);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClick = listener;
    }
    public FavoriteAdapter(List<ArtData> list){
        if(list == null)
            throw new IllegalArgumentException("List cannot be null");
        mList = list;
    }

    public class FavoriteImageViewHolder extends RecyclerView.ViewHolder {
        public final ImageView favoriteImage;
        public final TextView favoriteTitle;

        public FavoriteImageViewHolder(View itemView) {
            super(itemView);
            favoriteImage = itemView.findViewById(R.id.favoriteImage);
            favoriteTitle = itemView.findViewById(R.id.favoriteTitle);
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
    public FavoriteImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_favorite, parent, false);
        return new FavoriteImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavoriteImageViewHolder holder, int position) {
        ArtData artData = mList.get(position);
        Glide.with(holder.itemView)
                .load(artData.getArtImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.favoriteImage);

        holder.favoriteTitle.setText(artData.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(onItemClick != null)
                    onItemClick.onItemClick(artData);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
