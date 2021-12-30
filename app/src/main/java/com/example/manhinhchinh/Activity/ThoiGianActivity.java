package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.manhinhchinh.R;

public class ThoiGianActivity extends AppCompatActivity {

    Button btnGiamCanTrong3Thang, btnGiamCanTrong6Thang,btnGiamCanTrong9Thang, btnGiamCanTrong12Thang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_gian);

        btnGiamCanTrong3Thang = findViewById(R.id.btnGiamCanTrong3Thang);
        btnGiamCanTrong6Thang = findViewById(R.id.btnGiamCanTrong6Thang);
        btnGiamCanTrong9Thang = findViewById(R.id.btnGiamCanTrong9Thang);
        btnGiamCanTrong12Thang = findViewById(R.id.btnGiamCanTrong12Thang);
        btnGiamCanTrong3Thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThoiGianActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        btnGiamCanTrong6Thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThoiGianActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnGiamCanTrong9Thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThoiGianActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        btnGiamCanTrong12Thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThoiGianActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

    }

}