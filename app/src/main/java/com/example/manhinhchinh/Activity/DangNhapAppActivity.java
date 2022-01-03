package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.manhinhchinh.R;

public class DangNhapAppActivity extends AppCompatActivity {
    Button btnDangNhap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_app);
        btnDangNhap = findViewById(R.id.btnDangKy);
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapAppActivity.this, NhapThongTinNguoiDungActivity.class);
                startActivity(intent);
            }
        });
    }
}