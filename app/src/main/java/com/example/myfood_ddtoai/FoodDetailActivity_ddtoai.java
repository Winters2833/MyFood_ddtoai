package com.example.myfood_ddtoai;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class FoodDetailActivity_ddtoai extends AppCompatActivity {

    DBHelper_ddtoai dbHelper;
    int foodId;
    String size = "Lớn";
    double basePrice;
    double currentPrice;
    int quantity = 1;

    TextView txtFoodName, txtDescription, txtStoreName, txtPrice, txtQuantity;
    ImageView imgFood;
    Button btnSmall, btnMedium, btnLarge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_detail_ddtoai);

        // Ánh xạ View
        txtFoodName = findViewById(R.id.txtFoodName);
        txtDescription = findViewById(R.id.txtDescription);
        txtStoreName = findViewById(R.id.txtStoreName);
        txtPrice = findViewById(R.id.txtPrice);
        txtQuantity = findViewById(R.id.txtQuantity);
        imgFood = findViewById(R.id.imgFood);
        btnSmall = findViewById(R.id.btnSmall);
        btnMedium = findViewById(R.id.btnMedium);
        btnLarge = findViewById(R.id.btnLarge);

        // Quay lại
        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        // Tăng/giảm số lượng
        findViewById(R.id.btnPlus).setOnClickListener(v -> {
            quantity++;
            txtQuantity.setText(String.valueOf(quantity));
            updatePrice();
        });

        findViewById(R.id.btnMinus).setOnClickListener(v -> {
            if (quantity > 1) quantity--;
            txtQuantity.setText(String.valueOf(quantity));
            updatePrice();
        });

        dbHelper = new DBHelper_ddtoai(this);
        foodId = getIntent().getIntExtra("food_id", -1);

        loadFoodDetail(foodId);
    }

    private void loadFoodDetail(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT f.name, f.description, f.image, f.price, r.name, r.address " +
                        "FROM Food_ddtoai f JOIN Restaurant_ddtoai r ON f.restaurant_id = r.id WHERE f.id = ?",
                new String[]{String.valueOf(id)});
        if (c.moveToFirst()) {
            String foodName = c.getString(0);
            String description = c.getString(1);
            String imageName = c.getString(2);
            basePrice = c.getDouble(3); // size LỚN
            String restaurant = c.getString(4);
            String address = c.getString(5);

            txtFoodName.setText(foodName);
            txtDescription.setText(description);
            txtStoreName.setText("Nhà hàng " + restaurant + "\n" + address);
            txtQuantity.setText(String.valueOf(quantity));

            // Set ảnh
            int imageId = getResources().getIdentifier(imageName, "drawable", getPackageName());
            imgFood.setImageResource(imageId);

            // Set giá vào nút
            btnSmall.setText("NHỎ\n" + String.format("%.0f", basePrice - 20000));
            btnMedium.setText("VỪA\n" + String.format("%.0f", basePrice - 10000));
            btnLarge.setText("LỚN\n" + String.format("%.0f", basePrice));

            // Chọn mặc định là LỚN
            size = "Lớn";
            currentPrice = basePrice;
            updatePrice();

            // Xử lý chọn size
            btnSmall.setOnClickListener(v -> {
                size = "Nhỏ";
                currentPrice = basePrice - 20000;
                updatePrice();
            });

            btnMedium.setOnClickListener(v -> {
                size = "Vừa";
                currentPrice = basePrice - 10000;
                updatePrice();
            });

            btnLarge.setOnClickListener(v -> {
                size = "Lớn";
                currentPrice = basePrice;
                updatePrice();
            });
        }
        c.close();
    }

    private void updatePrice() {
        double total = currentPrice * quantity;
        txtPrice.setText(String.format("%.0f VND", total));
    }
}
