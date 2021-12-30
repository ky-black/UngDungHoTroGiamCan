package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manhinhchinh.R;

public class NhapThongTinNguoiDungActivity extends AppCompatActivity {

    Button btnTiepTuc;
    EditText edtHoTen, edtTuoi, edtGioiTinh, edtChieuCao, edtCanNang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_nhap_thong_tin_nguoi_dung);
        edtHoTen = findViewById(R.id.edtHoTen);
        edtTuoi = findViewById(R.id.edtTuoi);
        edtGioiTinh = findViewById(R.id.edtGioiTinh);
        edtChieuCao = findViewById(R.id.edtChieuCao);
        edtCanNang = findViewById(R.id.edtCanNang);
        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ChieuCao = edtChieuCao.getText().toString();
                String CanNang = edtCanNang.getText().toString();
                Bundle bundle= new Bundle();
                bundle.putString("chuoi1",ChieuCao);
                bundle.putString("chuoi2",CanNang);
                Intent intent = new Intent(NhapThongTinNguoiDungActivity.this, HienThiSoDuCanNangNguoiDungActivity.class);
                intent.putExtra("Data",bundle);
                NhapThongTinNguoiDungActivity.this.startActivity(intent);
            }
        });
    }
}