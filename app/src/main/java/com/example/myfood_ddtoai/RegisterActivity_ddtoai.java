package com.example.myfood_ddtoai;

import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity_ddtoai extends AppCompatActivity {

    EditText edtUsername, edtPassword, edtRepassword;
    Button btnRegister;
    DBHelper_ddtoai dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_ddtoai);

        edtUsername = findViewById(R.id.edtUsernameRegister_ddtoai);
        edtPassword = findViewById(R.id.edtPasswordRegister_ddtoai);
        edtRepassword = findViewById(R.id.edtRePassword_ddtoai);
        btnRegister = findViewById(R.id.btnRegister_ddtoai);
        dbHelper = new DBHelper_ddtoai(this);

        btnRegister.setOnClickListener(v -> {
            String user = edtUsername.getText().toString().trim();
            String pass = edtPassword.getText().toString().trim();
            String repass = edtRepassword.getText().toString().trim();

            if (user.isEmpty() || pass.isEmpty() || repass.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!pass.equals(repass)) {
                Toast.makeText(this, "Mật khẩu không khớp", Toast.LENGTH_SHORT).show();
                return;
            }

            boolean inserted = dbHelper.registerUser_ddtoai(user, pass);
            if (inserted) {
                Toast.makeText(this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(this, LoginActivity_ddtoai.class));
                finish();
            } else {
                Toast.makeText(this, "Tài khoản đã tồn tại", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
