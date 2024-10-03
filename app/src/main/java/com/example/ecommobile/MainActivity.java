package com.example.ecommobile;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private EditText etProductId, etProductName, etProductDescription, etProductPrice;
    private Button btnAddProduct, btnUpdateProduct, btnDeleteProduct;
    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etProductId = findViewById(R.id.etProductId);
        etProductName = findViewById(R.id.etProductName);
        etProductDescription = findViewById(R.id.etProductDescription);
        etProductPrice = findViewById(R.id.etProductPrice);

        btnAddProduct = findViewById(R.id.btnAddProduct);
        btnUpdateProduct = findViewById(R.id.btnUpdateProduct);
        btnDeleteProduct = findViewById(R.id.btnDeleteProduct);

        apiService = RetrofitClient.getClient("http://10.0.2.2:8080").create(ApiService.class);

        btnAddProduct.setOnClickListener(v -> {
            Product newProduct = new Product(
                    etProductName.getText().toString(),
                    etProductDescription.getText().toString(),
                    Double.parseDouble(etProductPrice.getText().toString())
            );
            addProduct(newProduct);
        });

        btnUpdateProduct.setOnClickListener(v -> {
            long productId = Long.parseLong(etProductId.getText().toString());
            Product updatedProduct = new Product(
                    etProductName.getText().toString(),
                    etProductDescription.getText().toString(),
                    Double.parseDouble(etProductPrice.getText().toString())
            );
            updateProduct(productId, updatedProduct);
        });

        btnDeleteProduct.setOnClickListener(v -> {
            long productId = Long.parseLong(etProductId.getText().toString());
            deleteProduct(productId);
        });
    }

    private void addProduct(Product product) {
        apiService.addProduct(product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Product added!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to add product", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void updateProduct(long productId, Product product) {
        apiService.updateProduct(productId, product).enqueue(new Callback<Product>() {
            @Override
            public void onResponse(Call<Product> call, Response<Product> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Product updated!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Product> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to update product", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void deleteProduct(long productId) {
        apiService.deleteProduct(productId).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (response.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "Product deleted!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Failed to delete product", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
