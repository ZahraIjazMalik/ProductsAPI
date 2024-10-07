package com.example.viewbinding;

import android.icu.text.CaseMap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.viewbinding.databinding.ActivityMainBinding;
import com.example.viewbinding.databinding.ActivityProductDetailsBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductDetailsActivity extends AppCompatActivity {
    private ActivityProductDetailsBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        int clickedPosition = getIntent().getIntExtra("id", 0);

        if (clickedPosition != 0) {

            RetrofitClient.getInstance().getProductDetail(clickedPosition).enqueue(new Callback<ProductData>() {
                @Override
                public void onResponse(Call<ProductData> call, Response<ProductData> response) {
                    ProductData data = response.body();
                    binding.Id.setText(String.valueOf(data.getid()));
                    binding.Title.setText(data.getTitle());
                    binding.Discription.setText(data.getDescription());
                    binding.Price.setText(String.valueOf(data.getPrice()));


                    Glide
                            .with(ProductDetailsActivity.this)
                            .load(data.getImage())
                            .centerCrop()
                            .placeholder(R.drawable.ic_launcher_background)
                            .into(binding.ProductImage);
                }



                @Override
                public void onFailure(Call<ProductData> call, Throwable t) {
                    Log.d("zahra",t.getLocalizedMessage());
                }
            });

        }
    }
}