package com.example.myfood_ddtoai;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper_ddtoai extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Food_ddtoai.db";
    public static final int DATABASE_VERSION = 1;

    public DBHelper_ddtoai(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tạo bảng User
        db.execSQL("CREATE TABLE User_ddtoai (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "username TEXT NOT NULL UNIQUE," +
                "password TEXT NOT NULL)");

        // Tạo bảng Restaurant
        db.execSQL("CREATE TABLE Restaurant_ddtoai (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "address TEXT NOT NULL," +
                "image TEXT)");

        // Tạo bảng Food
        db.execSQL("CREATE TABLE Food_ddtoai (" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name TEXT NOT NULL," +
                "description TEXT," +
                "price REAL," +
                "size TEXT," +
                "image TEXT," +
                "restaurant_id INTEGER," +
                "FOREIGN KEY (restaurant_id) REFERENCES Restaurant_ddtoai(id))");

        // Dữ liệu mẫu
        insertSampleData(db);
    }

    private void insertSampleData(SQLiteDatabase db) {
        // Dữ liệu User
        db.execSQL("INSERT INTO User_ddtoai (username, password) VALUES " +
                "('alice', '123')," +
                "('bob', '123')," +
                "('charlie', '123')," +
                "('david', '123')," +
                "('eva', '123')");

        // Dữ liệu Restaurant
        db.execSQL("INSERT INTO Restaurant_ddtoai (name, address, image) VALUES " +
                "('Nhà hàng Gà Rán', '12 Trần Hưng Đạo', 'ga_ran')," +
                "('Nhà hàng Bún Bò', '45 Nguyễn Huệ', 'bun_bo')," +
                "('Nhà hàng Pizza', '89 Lê Lợi', 'pizza')," +
                "('Nhà hàng Sushi', '32 Phan Đình Phùng', 'sushi')," +
                "('Nhà hàng Lẩu', '21 Hoàng Diệu', 'lau')");

        // Dữ liệu Food
        db.execSQL("INSERT INTO Food_ddtoai (name, description, price, size, image, restaurant_id) VALUES " +
                "('Gà Rán Cay', 'Gà rán giòn, cay nồng', 45000, 'Nhỏ', 'ga_cay', 1)," +
                "('Gà Rán Truyền Thống', 'Gà giòn, vị truyền thống', 40000, 'Nhỏ', 'ga_tt', 1)," +
                "('Bún Bò Huế', 'Bún bò đặc trưng Huế', 50000, 'Vừa', 'bunbo', 2)," +
                "('Bún Bò Tái', 'Bún bò với thịt tái', 55000, 'Vừa', 'bunbo_tai', 2)," +
                "('Pizza Hải Sản', 'Pizza topping hải sản', 99000, 'Lớn', 'pizza_hs', 3)," +
                "('Pizza Phô Mai', 'Pizza nhiều phô mai', 85000, 'Lớn', 'pizza_pm', 3)," +
                "('Sushi Cá Hồi', 'Sushi tươi sống', 65000, 'Vừa', 'sushi_ch', 4)," +
                "('Sushi Thanh Cua', 'Sushi ngon giá rẻ', 55000, 'Nhỏ', 'sushi_tc', 4)," +
                "('Lẩu Thái', 'Lẩu chua cay kiểu Thái', 120000, 'Lớn', 'lau_thai', 5)," +
                "('Lẩu Nấm', 'Lẩu thanh đạm nấm tươi', 100000, 'Lớn', 'lau_nam', 5)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Food_ddtoai");
        db.execSQL("DROP TABLE IF EXISTS Restaurant_ddtoai");
        db.execSQL("DROP TABLE IF EXISTS User_ddtoai");
        onCreate(db);
    }
    public boolean checkUser_ddtoai(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM User_ddtoai WHERE username=? AND password=?",
                new String[]{username, password}
        );
        boolean result = cursor.getCount() > 0;
        cursor.close();
        return result;
    }
    public boolean registerUser_ddtoai(String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        try {
            db.execSQL("INSERT INTO User_ddtoai (username, password) VALUES (?, ?)", new Object[]{username, password});
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}

