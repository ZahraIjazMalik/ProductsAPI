package com.example.viewbinding;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.MyViewHolder> {
    Context context;
    List<String> list;
    OnCategoryClickListener listener;

    public CategoriesAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;

    }
    
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.product_categories, parent, false);
        return (new MyViewHolder(view));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {

        String data = list.get(position);
        holder.categories.setText(data.toString());



        holder.materialCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                listener.onCategoryClick(data);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setOnCategoryClickListener(OnCategoryClickListener listener) {
        this.listener = listener;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView categories;
       MaterialCardView materialCardView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);


            categories = itemView.findViewById(R.id.categoriesTV);
            materialCardView=itemView.findViewById(R.id.materialcardView);


        }
    }


    public interface OnCategoryClickListener {
        void onCategoryClick(String category);
    }
}

