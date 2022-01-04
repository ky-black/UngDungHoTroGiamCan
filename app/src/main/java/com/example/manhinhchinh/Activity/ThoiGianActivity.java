package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.manhinhchinh.Module.AccountModule;
import com.example.manhinhchinh.Module.FoodModule;
import com.example.manhinhchinh.R;
import com.example.manhinhchinh.ultil.Server;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class ThoiGianActivity extends AppCompatActivity {

    Button btnGiamCanTrong3Thang, btnGiamCanTrong6Thang,btnGiamCanTrong9Thang, btnGiamCanTrong12Thang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_thoi_gian);

        btnGiamCanTrong3Thang = findViewById(R.id.btn_3Thang);
        btnGiamCanTrong6Thang = findViewById(R.id.btn_6Thang);
        btnGiamCanTrong9Thang = findViewById(R.id.btn_9Thang);
        btnGiamCanTrong12Thang = findViewById(R.id.btn_12Thang);


        btnGiamCanTrong3Thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThoiGianActivity.this, MainActivity.class);
                AccountModule account = (AccountModule) getIntent().getSerializableExtra("object_account");
                account.setWeightLossTime("3");
                putUserToSever(account);
                intent.putExtra("object_account", account);
                startActivity(intent);
            }
        });
        btnGiamCanTrong6Thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThoiGianActivity.this, MainActivity.class);
                AccountModule account = (AccountModule) getIntent().getSerializableExtra("object_account");
                account.setWeightLossTime("6");
                account.setWeightLoss("Giảm cân theo ăn uống");
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_account", account);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnGiamCanTrong9Thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThoiGianActivity.this, MainActivity.class);
                AccountModule account = (AccountModule) getIntent().getSerializableExtra("object_account");
                account.setWeightLossTime("9");
                account.setWeightLoss("Giảm cân theo ăn uống");
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_account", account);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        btnGiamCanTrong12Thang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThoiGianActivity.this, MainActivity.class);
                AccountModule account = (AccountModule) getIntent().getSerializableExtra("object_account");
                account.setWeightLossTime("12");
                Bundle bundle = new Bundle();
                bundle.putSerializable("object_account", account);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    private void putUserToSever(AccountModule account) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        final String requestBody = getJSONObj(account);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlPostUser, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
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

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        requestQueue.add(stringRequest);
    }
    private String getJSONObj(AccountModule account){
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("ID", null);
            jsonBody.put("UserName", account.getUserName());
            jsonBody.put("Password", getMd5(account.getPassword()));
            jsonBody.put("Gmail", account.getGmail());
            jsonBody.put("Age", account.getAge());
            jsonBody.put("Height", account.getHeight());
            jsonBody.put("Weight", account.getWeight());
            jsonBody.put("WeightLoss", account.getWeightLoss());
            jsonBody.put("WeightLossTime", account.getWeightLossTime());

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonBody.toString();
    }
    public static String getMd5(String input)
    {
        try {

            // Static getInstance method is called with hashing MD5
            MessageDigest md = MessageDigest.getInstance("MD5");

            // digest() method is called to calculate message digest
            //  of an input digest() return array of byte
            byte[] messageDigest = md.digest(input.getBytes());

            // Convert byte array into signum representation
            BigInteger no = new BigInteger(1, messageDigest);

            // Convert message digest into hex value
            String hashtext = no.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
            return hashtext;
        }

        // For specifying wrong message digest algorithms
        catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
}