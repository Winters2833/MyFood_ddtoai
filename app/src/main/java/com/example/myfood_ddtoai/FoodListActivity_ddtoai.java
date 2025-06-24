package com.example.myfood_ddtoai;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class FoodListActivity_ddtoai extends AppCompatActivity {

    ListView lvFood;
    ArrayList<Food_ddtoai> list;
    FoodAdapter_ddtoai adapter;
    DBHelper_ddtoai dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_list_ddtoai);

        lvFood = findViewById(R.id.lvFood_ddtoai);
        list = new ArrayList<>();
        dbHelper = new DBHelper_ddtoai(this);

        int restaurantId = getIntent().getIntExtra("restaurant_id", -1);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM Food_ddtoai WHERE restaurant_id=?", new String[]{String.valueOf(restaurantId)});
        while (c.moveToNext()) {
            list.add(new Food_ddtoai(
                    c.getInt(0),
                    c.getString(1),
                    c.getString(2),
                    c.getString(4),
                    c.getString(5),
                    c.getDouble(3)
            ));
        }
        c.close();

        adapter = new FoodAdapter_ddtoai(this, R.layout.item_food_ddtoai, list);
        lvFood.setAdapter(adapter);

        // TODO: Mở trang chi tiết món ăn ở câu 6
        lvFood.setOnItemClickListener((parent, view, position, id) -> {
            Food_ddtoai food = list.get(position);
            Intent intent = new Intent(this, FoodDetailActivity_ddtoai.class);
            intent.putExtra("food_id", food.id);
            startActivity(intent);
        });
    }
}
