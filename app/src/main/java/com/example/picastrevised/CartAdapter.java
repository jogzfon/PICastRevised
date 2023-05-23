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
import com.squareup.picasso.Picasso;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartImageViewHolder> {

    private List<ArtData> mList;
    private OnItemClickListener onItemClick;

    public interface OnItemClickListener {
        void onItemClick(ArtData artData);
    }

    public void setOnItemClickListener(CartAdapter.OnItemClickListener listener) {
        onItemClick = listener;
    }

    public CartAdapter(List<ArtData> mList) {
        this.mList = mList;
    }
    public void setFilteredList(List<ArtData> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    public class CartImageViewHolder extends RecyclerView.ViewHolder {
        public final ImageView cartImage;
        public final TextView cartTitle;
        public final TextView cartPrice;


        public CartImageViewHolder(View itemView) {
            super(itemView);
            cartImage = itemView.findViewById(R.id.cartImage);
            cartTitle = itemView.findViewById(R.id.cartTitle);
            cartPrice = itemView.findViewById(R.id.cartPrice);
            View view = itemView;
            view.setOnClickListener(view1 -> {
                Toast.makeText(view.getContext(), "Item clicked", Toast.LENGTH_SHORT).show();
            });
        }
    }

    @Override
    public CartImageViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.each_cart_item, parent, false);
        return new CartImageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartImageViewHolder holder, int position) {
        ArtData cartData = mList.get(position);
        Glide.with(holder.itemView)
                .load(cartData.getArtImage())
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.cartImage);

        holder.cartTitle.setText(cartData.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClick != null) {
                    onItemClick.onItemClick(cartData);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
