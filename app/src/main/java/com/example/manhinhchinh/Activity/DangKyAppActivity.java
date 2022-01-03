package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.manhinhchinh.R;

public class DangKyAppActivity extends AppCompatActivity {
    Button btnDangKy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_app);
        btnDangKy = findViewById(R.id.btnDangKy);
        btnDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangKyAppActivity.this, NhapThongTinNguoiDungActivity.class);
                startActivity(intent);
            }
        });
    }
}