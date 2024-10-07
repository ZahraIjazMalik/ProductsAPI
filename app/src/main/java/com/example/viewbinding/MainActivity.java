package com.example.viewbinding;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.viewbinding.databinding.ActivityMainBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private CategoriesAdapter categoriesAdapter;
    private ProductAdapter productAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        fetchProduct();
        fetchCategories();
    }

    private void fetchClickCategory() {
        if (categoriesAdapter != null) {
            categoriesAdapter.setOnCategoryClickListener(new CategoriesAdapter.OnCategoryClickListener() {
                @Override
                public void onCategoryClick(String category) {
                    RetrofitClient.getInstance().getCategory(category).enqueue(new Callback<List<ProductData>>() {
                        @Override
                        public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                            if (!response.body().isEmpty())  {
                                productAdapter.updateByCategory(response.body());
                            }
                        }

                        @Override
                        public void onFailure(Call<List<ProductData>> call, Throwable t) {

                        }
                    });
                }
            });
        }
    }

    private void fetchCategories() {
        RetrofitClient.getInstance().getProductCategories().enqueue(new Callback<List<String>>() {
            @Override
            public void onResponse(Call<List<String>> call, Response<List<String>> response) {
                if (!response.body().isEmpty()) {
                    categoriesAdapter = new CategoriesAdapter(MainActivity.this, response.body());
                    binding.categoriesRV.setAdapter(categoriesAdapter);
                    fetchClickCategory();
                }

                Log.d("zahra", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<String>> call, Throwable t) {
                Log.d("zahra", t.getLocalizedMessage());
            }
        });
    }

    private void fetchProduct() {
        RetrofitClient.getInstance().getproduct().enqueue(new Callback<List<ProductData>>() {
            @Override
            public void onResponse(Call<List<ProductData>> call, Response<List<ProductData>> response) {
                if (!response.body().isEmpty()) {

                    binding.progressbar.setVisibility(View.INVISIBLE);
                    binding.recycler.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                    productAdapter = new ProductAdapter(MainActivity.this, response.body());
                    binding.recycler.setAdapter(productAdapter);

                    productAdapter.OnProductClickListener(new ProductAdapter.OnProductClickListener() {
                        @Override
                        public void onProductClick(int position) {
                            Intent startActivity = new Intent(MainActivity.this, ProductDetailsActivity.class);
                            startActivity.putExtra("id", position + 1);
                            startActivity(startActivity);
                        }
                    });
                }

                Log.d("zahra", response.body().get(0).getTitle());
            }

            @Override
            public void onFailure(Call<List<ProductData>> call, Throwable t) {
                Log.d("zahra", t.getLocalizedMessage());
            }
        });
    }
}