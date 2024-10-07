
package com.example.viewbinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("products")
    Call<List<ProductData>> getproduct();

    @GET("products/{id}")
    Call<ProductData> getProductDetail(@Path("id") int id);

    @GET("products/categories")
    Call<List<String>> getProductCategories();


    @GET("products/category/{categories}")
    Call<List<ProductData>> getCategory(@Path("categories") String category);
}
