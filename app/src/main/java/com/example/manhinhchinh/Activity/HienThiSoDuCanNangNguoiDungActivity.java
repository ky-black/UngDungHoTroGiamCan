package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manhinhchinh.R;

public class HienThiSoDuCanNangNguoiDungActivity extends AppCompatActivity {
    String s="";
    EditText edtChieuCao, edtCanNang, edtSoCanThua;
    Button btnTiepTuc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hien_thi_so_du_can_nang_nguoi_dung);

        edtChieuCao = findViewById(R.id.edtChieuCao);
        edtCanNang = findViewById(R.id.edtCanNang);
        edtSoCanThua = findViewById(R.id.edtSoCanThua);

        btnTiepTuc = findViewById(R.id.btnTiepTuc);

        Intent intent= getIntent();
        Bundle b=intent.getBundleExtra("Data");
        String s1=b.getString("chuoi1");
        String s2=b.getString("chuoi2");
        edtChieuCao.setText(s1);
        edtCanNang.setText(s2);
        //tiếng hành tính toán
        try {
            int n1=Integer.parseInt(s1);
            int n2=Integer.parseInt(s2);
            s=""+(n1+n2);
        } catch (Exception e) {
            s="nhap du lieu sai nha be";
        }
        edtSoCanThua.setText(s);

        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HienThiSoDuCanNangNguoiDungActivity.this, PhuongPhapActivity.class);
                startActivity(intent);
            }
        });
    }
}