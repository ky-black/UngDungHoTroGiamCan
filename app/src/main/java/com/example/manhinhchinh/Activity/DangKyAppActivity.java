package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.manhinhchinh.R;
import com.example.manhinhchinh.ultil.CheckInfo;
import com.example.manhinhchinh.ultil.MySingleton;
import com.example.manhinhchinh.ultil.Server;

public class DangKyAppActivity extends AppCompatActivity {
    Button btn_SignUp;
    EditText et_UserName,et_Password, et_Re_enterPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_app);

        mapping();

        catchOnEvent();

    }
    private boolean checkInformation() {
        boolean tmp = true;

        if (!et_Password.getText().toString().trim().equals(et_Re_enterPassword.getText().toString().trim())){
            et_Re_enterPassword.setError("Mật khẩu không khớm");
            tmp = false;
        }
        return tmp;
    }

    private void catchOnEvent() {
        btn_SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckAccount(new CallBackAccount() {
                    @Override
                    public void onResponse(Boolean status) {

                        if (!status)
                            Toast.makeText(getApplicationContext(),"Tên đăng nhập đã tồn tại",Toast.LENGTH_SHORT ).show();

                        if (status && checkInformation()){
                            Intent intent = new Intent(DangKyAppActivity.this, NhapThongTinNguoiDungActivity.class);
                            intent.putExtra("UserName", et_UserName.getText().toString().trim());
                            intent.putExtra("Password", et_Password.getText().toString().trim());
                            startActivity(intent);
                        }


                    }
                });

            }
        });
    }
    public interface CallBackAccount{
        void onResponse(Boolean status);
    }
    private void CheckAccount(CallBackAccount callBackAccount) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET, Server.urlCheckUserByUserName + et_UserName.getText().toString().trim(), new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                callBackAccount.onResponse(response.length() > 2 ? false : true);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Loi gi " + error, Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    private void mapping() {
        btn_SignUp = findViewById(R.id.btn_3Thang);
        et_UserName = findViewById(R.id.et_UserName);
        et_Password = findViewById(R.id.et_Password);
        et_Re_enterPassword = findViewById(R.id.et_Re_enterPassword);

    }
}