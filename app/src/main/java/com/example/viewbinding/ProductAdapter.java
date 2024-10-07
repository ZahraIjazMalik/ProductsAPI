package com.example.viewbinding;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {
    Context context;
    List<ProductData> list;
    private int imageResource;
    OnProductClickListener listener;

    public ProductAdapter(Context context, List<ProductData> list) {
        this.context = context;
        this.list = list;
        this.imageResource = imageResource;

    }


    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_item, parent, false);
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        ProductData data = list.get(position);
        holder.title.setText(data.getTitle());
        holder.price.setText(String.valueOf(data.getPrice()));
        holder.id.setText(String.valueOf(data.getid()));


        Glide.with(context)
                .load(data.getImage())
                .error(R.drawable.ic_launcher_background)
                .into(holder.productImage);


        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // callback
                listener.onProductClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void OnProductClickListener(OnProductClickListener listener) {
        this.listener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView title, price, id;
        ImageView productImage;
        CardView materialCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.Title);
            price = itemView.findViewById(R.id.Price);
            id = itemView.findViewById(R.id.Id);
            productImage = itemView.findViewById(R.id.ProductImage);
            materialCardView = itemView.findViewById(R.id.maincardview);

        }
    }

    public interface OnProductClickListener {
        void onProductClick(int position);
    }

    public void updateByCategory(List<ProductData> list) {
        this.list = list;
        notifyDataSetChanged();
    }
}

