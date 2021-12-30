package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.manhinhchinh.R;

public class PhuongPhapActivity extends AppCompatActivity {

    Button btnGiamCanTheoAnUon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phuong_phap);

        btnGiamCanTheoAnUon = findViewById(R.id.btnGiamCanTrong6Thang);

        btnGiamCanTheoAnUon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhuongPhapActivity.this, ThoiGianActivity.class);
                startActivity(intent);
            }
        });
    }
}