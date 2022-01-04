package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manhinhchinh.Module.AccountModule;
import com.example.manhinhchinh.R;

public class HienThiSoDuCanNangNguoiDungActivity extends AppCompatActivity {
    EditText edtChieuCao, edtCanNang, edtSoCanThua;
    Button btnTiepTuc;
    AccountModule account;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_so_du_can_nang_nguoi_dung);

        mapping();
        getData();


        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HienThiSoDuCanNangNguoiDungActivity.this, PhuongPhapActivity.class);
                intent.putExtra("object_account", account);
                startActivity(intent);
            }
        });
    }

    private void getData() {
         account = (AccountModule) getIntent().getSerializableExtra("object_account");
         edtCanNang.setText(account.getWeight());
         edtChieuCao.setText(account.getHeight());
         float height = Float.parseFloat(account.getHeight())/100;
         float canNang = (float) (24.9 * height * height);
         float soCanThua = (Float.parseFloat(account.getWeight()) - canNang);

         edtSoCanThua.setText(String.valueOf(soCanThua));
    }

    private void mapping() {
        edtChieuCao = findViewById(R.id.edtChieuCao);
        edtCanNang = findViewById(R.id.edtCanNang);
        edtSoCanThua = findViewById(R.id.edtSoCanThua);

        btnTiepTuc = findViewById(R.id.btnTiepTuc);
    }
}