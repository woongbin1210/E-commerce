package com.example.ecommobile;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ApiService {

    @GET("/api/products")
    Call<List<Product>> getProducts();

    @POST("/api/products")
    Call<Product> addProduct(@Body Product product);

    @PUT("/api/products/{id}")
    Call<Product> updateProduct(@Path("id") long id, @Body Product product);

    @DELETE("/api/products/{id}")
    Call<Void> deleteProduct(@Path("id") long id);
}
