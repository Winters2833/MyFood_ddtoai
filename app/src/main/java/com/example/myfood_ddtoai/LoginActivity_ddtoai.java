package com.example.myfood_ddtoai;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity_ddtoai extends AppCompatActivity {

    EditText edtUsername_ddtoai, edtPassword_ddtoai;
    Button btnLogin_ddtoai;
    TextView txtRegister_ddtoai;
    DBHelper_ddtoai dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_ddtoai);

        edtUsername_ddtoai = findViewById(R.id.edtUsername_ddtoai);
        edtPassword_ddtoai = findViewById(R.id.edtPassword_ddtoai);
        btnLogin_ddtoai = findViewById(R.id.btnLogin_ddtoai);
        txtRegister_ddtoai = findViewById(R.id.txtRegister_ddtoai);
        dbHelper = new DBHelper_ddtoai(this);

        btnLogin_ddtoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = edtUsername_ddtoai.getText().toString();
                String pass = edtPassword_ddtoai.getText().toString();

                if (dbHelper.checkUser_ddtoai(user, pass)) {
                    Toast.makeText(LoginActivity_ddtoai.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(LoginActivity_ddtoai.this, HomeActivity_ddtoai.class));
                } else {
                    Toast.makeText(LoginActivity_ddtoai.this, "Sai tài khoản hoặc mật khẩu", Toast.LENGTH_SHORT).show();
                }
            }
        });

        txtRegister_ddtoai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity_ddtoai.this, RegisterActivity_ddtoai.class);
                startActivity(i);
            }
        });
    }
}
