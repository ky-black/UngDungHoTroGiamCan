package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manhinhchinh.Module.AccountModule;
import com.example.manhinhchinh.R;
import com.example.manhinhchinh.ultil.CheckInfo;

public class NhapThongTinNguoiDungActivity extends AppCompatActivity {

    Button btnTiepTuc;
    EditText edtHoTen, edtTuoi, edtChieuCao, edtCanNang, edtGmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nhap_thong_tin_nguoi_dung);

        mapping();

        catchOnEvent();

    }

    private void catchOnEvent() {
        btnTiepTuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();

                if (checkInformation()){
                    AccountModule account = new AccountModule(null,
                            intent.getExtras().getString("UserName"),
                            intent.getExtras().getString("Password"),
                            edtGmail.getText().toString().trim(),
                            edtTuoi.getText().toString().trim(),
                            edtChieuCao.getText().toString().trim(),
                            edtCanNang.getText().toString().trim(), null, null);
                    Intent intent1 = new Intent(NhapThongTinNguoiDungActivity.this, HienThiSoDuCanNangNguoiDungActivity.class);
                    intent1.putExtra("object_account", account);
                    NhapThongTinNguoiDungActivity.this.startActivity(intent1);
                }
            }
        });
    }

    private boolean checkInformation() {
        boolean tmp = true;
        if (edtGmail.getText().toString().length() <= 0){
            edtGmail.setError("Vui lòng nhập thông tin!");
            tmp = false;
        }
        else if (!CheckInfo.isValidEmail(edtGmail.getText().toString().trim())){
            edtGmail.setError("Email không chính xác!");
            tmp = false;
        }

        if (edtChieuCao.getText().toString().length() <= 0){
            edtChieuCao.setError("Vui lòng nhập thông tin!");
            tmp = false;
        }
        else if (!CheckInfo.isNumeric(edtChieuCao.getText().toString())){
            edtChieuCao.setError("Vui lòng nhập số");
            tmp = false;
        }


        if (edtCanNang.getText().toString().length() <= 0){
            edtCanNang.setError("Vui lòng nhập thông tin!");
            tmp = false;
        }
        else if (!CheckInfo.isNumeric(edtCanNang.getText().toString())){
            edtCanNang.setError("Vui lòng nhập số");
            tmp = false;
        }


        if (edtTuoi.getText().toString().length() <= 0){
            edtTuoi.setError("Vui lòng nhập thông tin!");
            tmp = false;
        }
        else if (!CheckInfo.isNumeric(edtTuoi.getText().toString())){
            edtTuoi.setError("Vui lòng nhập số");
            tmp = false;
        }
        return tmp;
    }


    private void mapping() {
        edtHoTen = findViewById(R.id.edtHoTen);
        edtTuoi = findViewById(R.id.edtTuoi);
        edtChieuCao = findViewById(R.id.edtChieuCao);
        edtCanNang = findViewById(R.id.edtCanNang);
        btnTiepTuc = findViewById(R.id.btnTiepTuc);
        edtGmail = findViewById(R.id.edtGmail);
    }
}