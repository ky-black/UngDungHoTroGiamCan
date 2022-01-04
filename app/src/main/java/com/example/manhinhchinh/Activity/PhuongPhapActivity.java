package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.manhinhchinh.Module.AccountModule;
import com.example.manhinhchinh.R;

public class PhuongPhapActivity extends AppCompatActivity {

    Button btnGiamCanTheoAnUon, btnGiamCanTheoTapTheDuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phuong_phap);

        btnGiamCanTheoAnUon = findViewById(R.id.btn_6Thang);
        btnGiamCanTheoTapTheDuc = findViewById(R.id.btn_3Thang);

        btnGiamCanTheoAnUon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhuongPhapActivity.this, ThoiGianActivity.class);
                AccountModule account = (AccountModule) getIntent().getSerializableExtra("object_account");
                account.setWeightLoss("Giảm cân theo ăn uống");
                intent.putExtra("object_account", account);
                startActivity(intent);
            }
        });

        btnGiamCanTheoTapTheDuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhuongPhapActivity.this, ThoiGianActivity.class);
                AccountModule account = (AccountModule) getIntent().getSerializableExtra("object_account");
                account.setWeightLoss("Giảm cân theo tập thể dục và ăn uống");
                intent.putExtra("object_account", account);
                startActivity(intent);
            }
        });
    }
}