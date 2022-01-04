package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.manhinhchinh.Module.AccountModule;
import com.example.manhinhchinh.R;
import com.example.manhinhchinh.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class DangNhapAppActivity extends AppCompatActivity {
    Button btnDangNhap;
    EditText et_UserName, et_Password;
    TextView txt_forgotPassword, txt_gotoRegister;
//    public AccountModule account = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap_app);

        mapping();

        catchOnEvent();

    }

    private void catchOnEvent() {
        btnDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAccount(new CallBackAccount() {

                    @Override
                    public void onResponse(AccountModule account) {
                        if (account != null){
                            Intent intent = new Intent(DangNhapAppActivity.this, MainActivity.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("object_account", account);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        } else Toast.makeText(getApplicationContext(),"Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_LONG ).show();
                    }
                });

            }
        });
        txt_gotoRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DangNhapAppActivity.this, DangKyAppActivity.class);
                startActivity(intent);
            }
        });

    }

    public interface CallBackAccount{
        void onResponse(AccountModule account);
    }

    private void checkAccount(CallBackAccount callBackAccount) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Cast to json
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("UserName", et_UserName.getText().toString().trim());
            jsonBody.put("Password", et_Password.getText().toString().trim());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        final String requestBody = jsonBody.toString();
        Toast.makeText(getApplicationContext(), jsonBody.toString(), Toast.LENGTH_LONG).show();


        //Create req

        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlCheckUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_LONG).show();
//                Log.i("VOLLEY", response);
                try {
                    JSONArray jsonArrayAccount = new JSONArray(response);
                        JSONArray jsonArray = new JSONArray(response);
                        JSONObject jsonObjectAccount = jsonArray.getJSONObject(0);
                        AccountModule tmp = new AccountModule(jsonObjectAccount.getString("ID"),
                                jsonObjectAccount.getString("UserName"),
                                jsonObjectAccount.getString("Password"),
                                jsonObjectAccount.getString("Gmail"),
                                jsonObjectAccount.getString("Age"),
                                jsonObjectAccount.getString("Height"),
                                jsonObjectAccount.getString("Weight"),
                                jsonObjectAccount.getString("WeightLoss"),
                                jsonObjectAccount.getString("WeightLossTime"));
                        callBackAccount.onResponse(tmp);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }
        };
        requestQueue.add(stringRequest);
    }

    private void mapping() {

        btnDangNhap = findViewById(R.id.btn_3Thang);
        et_UserName = findViewById(R.id.et_UserName);
        et_Password = findViewById(R.id.et_Password);
        txt_forgotPassword = findViewById(R.id.txt_forgotPassword);
        txt_gotoRegister = findViewById(R.id.txt_gotoRegister);

    }
}