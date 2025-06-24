package com.example.myfood_ddtoai;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class HomeActivity_ddtoai extends AppCompatActivity {

    ListView lvRestaurant;
    ArrayList<Restaurant_ddtoai> list;
    RestaurantAdapter_ddtoai adapter;
    DBHelper_ddtoai dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_ddtoai);

        lvRestaurant = findViewById(R.id.lvRestaurant_ddtoai);
        list = new ArrayList<>();
        dbHelper = new DBHelper_ddtoai(this);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Restaurant_ddtoai", null);
        while (c.moveToNext()) {
            list.add(new Restaurant_ddtoai(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(3)
            ));
        }
        c.close();

        adapter = new RestaurantAdapter_ddtoai(this, R.layout.item_restaurant_ddtoai, list);
        lvRestaurant.setAdapter(adapter);

        // Xử lý click chuyển sang danh sách món ăn (Câu 5)
        lvRestaurant.setOnItemClickListener((parent, view, position, id) -> {
            Restaurant_ddtoai r = list.get(position);
            Intent intent = new Intent(this, FoodListActivity_ddtoai.class);
            intent.putExtra("restaurant_id", r.id);
            startActivity(intent);
        });
    }
}
