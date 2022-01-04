package com.example.manhinhchinh.Activity;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.manhinhchinh.Adapter.ExerciseAdapter;
import com.example.manhinhchinh.Adapter.FoodAdapter;
import com.example.manhinhchinh.Adapter.FoodMainAdapter;
import com.example.manhinhchinh.Module.AccountModule;
import com.example.manhinhchinh.Module.ExerciseModule;
import com.example.manhinhchinh.Module.FoodDetailsModule;
import com.example.manhinhchinh.Module.FoodModule;
import com.example.manhinhchinh.R;
import com.example.manhinhchinh.ultil.MySingleton;
import com.example.manhinhchinh.ultil.Server;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity{
    FloatingActionButton add_btn, fab_breakfast, fab_lunch, fab_dinner, fab_train;
    Animation Move_Breakfast, Move_Lunch, Move_Dinner, Move_Train,
            Back_Breakfast, Back_Lunch, Back_Dinner, Back_Train;
    boolean moveBack = false;

    ProgressBar progress_bar;
    TextView text_view_progress;

    private SwipeRefreshLayout refreshLayout;

    public static List<FoodModule> food = new ArrayList<FoodModule>();
    public static List<ExerciseModule> exerciseModules = new ArrayList<ExerciseModule>();

    public static AccountModule account;
    FoodMainAdapter foodMainAdapter;

    public static ArrayList<FoodDetailsModule> arrayListDetail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getAccount();
        //Ánh Xạ
        mapping();
        //updateProgressBar();

        //Float Button
        catchOnEventFloatingActionButton();
        // Lấy dữ liệu món ăn của người dùng đã chọn trong ngày
        getFoodData(new CallBackFoodDetails() {
            @Override
            public void onResponse(ArrayList<FoodDetailsModule> list) {
                arrayListDetail = list;
                foodMainAdapter.notifyDataSetChanged();
            }
        });
        getFoodDetail();
        putCaloFromList();



    }

    private void getAccount() {
        account = (AccountModule) getIntent().getSerializableExtra("object_account");
        getIDTK(new CallBackAccount() {
            @Override
            public void onResponse(AccountModule ac) {
                account.setID(ac.getID());
            }
        });
    }

    public interface CallBackAccount{
        void onResponse(AccountModule account);
    }
    private void getIDTK(CallBackAccount callBackAccount) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());

        // Cast to json
        JSONObject jsonBody = new JSONObject();
        try {

            jsonBody.put("UserName",account.getUserName());
            jsonBody.put("Password",account.getPassword());
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

    public interface CallBackFoodDetails{
        void onResponse(ArrayList<FoodDetailsModule> list);
    }
    private void getFoodData(CallBackFoodDetails callBackFoodDetails) {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Server.urlGetFoodDetailsByIDTK + account.getID(), new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

//                Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                if (response != null){
                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            FoodDetailsModule tmp = new FoodDetailsModule(jsonObject.getString("IDTK"),
                                    jsonObject.getString("Quantily"),
                                    jsonObject.getString("IDTA"),
                                    jsonObject.getString("ID"),
                                    jsonObject.getString("Date"));
                            arrayListDetail.add(tmp);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                    callBackFoodDetails.onResponse(arrayListDetail);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }





    private void getFoodDetail() {
        String url = Server.urlGetFoodByID + account.getID();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        StringRequest request = new StringRequest(Request.Method.GET,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
            if (response != null && response.length() > 2) {
                food.clear();
                try {
                    JSONArray jsonArray = new JSONArray(response);
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        FoodModule tmp = new FoodModule(
                                jsonObject.getString("Description"),
                                jsonObject.getString("Picture"),
                                jsonObject.getString("ID"),
                                jsonObject.getString("Unit"),
                                jsonObject.getString("TypeFood"),
                                jsonObject.getString("Calories"),
                                jsonObject.getString("FoodName"));
                        food.add(tmp);
                        foodMainAdapter.notifyDataSetChanged();
                    }
//                    Log.i("response", response.toString());
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", error.toString());
            }
        });
        requestQueue.add(request);
    }

    private void catchOnEventFloatingActionButton() {
        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (moveBack == false){
                    Move();
                    moveBack = true;
                }
                else {
                    BackAnimation();
                    moveBack = false;
                }
            }
        });
        //Button Sáng
        fab_breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sáng", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, BreakFast.class);
                intent.putExtra("type_food", "Bữa sáng");
                startActivity(intent);
            }
        });
        //Button Trưa
        fab_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Trưa", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, BreakFast.class);
                intent.putExtra("type_food", "Bữa trưa");
                startActivity(intent);
            }
        });
        //Button Chiều
        fab_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Chiều", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, BreakFast.class);
                intent.putExtra("type_food", "Bữa tối");
                startActivity(intent);
            }
        });
        //Button Tập Luyện
        fab_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Tập Luyện", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Exercise.class);
                startActivity(intent);
            }
        });

        //SwipeRefresh
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                putCaloFromList();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        refreshLayout.setRefreshing(false);
                    }
                }, 500);
            }
        });
    }

    private void mapping() {
        arrayListDetail = new ArrayList<>();

        add_btn = findViewById(R.id.add_btn);
        fab_breakfast = findViewById(R.id.fab_breakfast);
        fab_lunch = findViewById(R.id.fab_lunch);
        fab_dinner = findViewById(R.id.fab_dinner);
        fab_train = findViewById(R.id.fab_train);

        Move_Breakfast = AnimationUtils.loadAnimation(this, R.anim.rotate_open_anim);
        Move_Lunch = AnimationUtils.loadAnimation(this, R.anim.rotate_close_anim);
        Move_Dinner = AnimationUtils.loadAnimation(this, R.anim.from_bottom_anim);
        Move_Train = AnimationUtils.loadAnimation(this, R.anim.to_bottom_anim);

        Back_Breakfast = AnimationUtils.loadAnimation(this, R.anim.back_breakfast);
        Back_Lunch = AnimationUtils.loadAnimation(this, R.anim.back_lunch);
        Back_Dinner = AnimationUtils.loadAnimation(this, R.anim.back_dinner);
        Back_Train = AnimationUtils.loadAnimation(this, R.anim.back_train);

        progress_bar = findViewById(R.id.progress_bar);
        text_view_progress = findViewById(R.id.text_view_progress);
        refreshLayout = findViewById(R.id.refreshLayout);

        //Main RecyclerView
        RecyclerView rcv_main_food = findViewById(R.id.rcv_main_food);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_main_food.setLayoutManager(linearLayoutManager);


        foodMainAdapter = new FoodMainAdapter(getApplicationContext(), food, String.valueOf(account.getID()),arrayListDetail);
        rcv_main_food.setAdapter(foodMainAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcv_main_food.addItemDecoration(itemDecoration);


    }

    public void putCaloFromList() {
        int sumCalo = 0;
        for (int i=0; i< food.size(); i++){
            sumCalo += Integer.parseInt(food.get(i).getCalories());
        }
        progress_bar.setProgress(sumCalo);
        text_view_progress.setText(sumCalo + "/2000kcal");
    }

    //Move Animation Float Button
    private void Move(){
        FrameLayout.LayoutParams paramsBreakfast = (FrameLayout.LayoutParams) fab_breakfast.getLayoutParams();
        paramsBreakfast.bottomMargin = (int) (fab_breakfast.getWidth() * 1.5);
        fab_breakfast.setLayoutParams(paramsBreakfast);
        fab_breakfast.startAnimation(Move_Breakfast);

        FrameLayout.LayoutParams paramsLunch = (FrameLayout.LayoutParams) fab_lunch.getLayoutParams();
        paramsLunch.bottomMargin = (int) (fab_lunch.getWidth() * 2.6);
        fab_lunch.setLayoutParams(paramsLunch);
        fab_lunch.startAnimation(Move_Lunch);

        FrameLayout.LayoutParams paramsDinner = (FrameLayout.LayoutParams) fab_dinner.getLayoutParams();
        paramsDinner.bottomMargin = (int) (fab_dinner.getWidth() * 3.7);
        fab_dinner.setLayoutParams(paramsDinner);
        fab_dinner.startAnimation(Move_Dinner);

        FrameLayout.LayoutParams paramsTrain = (FrameLayout.LayoutParams) fab_train.getLayoutParams();
        paramsTrain.bottomMargin = (int) (fab_train.getWidth() * 4.8);
        fab_train.setLayoutParams(paramsTrain);
        fab_train.startAnimation(Move_Train);

    }
    //Back Animation Float Button
    private void BackAnimation(){
        FrameLayout.LayoutParams paramsBreakfast = (FrameLayout.LayoutParams) fab_breakfast.getLayoutParams();
        paramsBreakfast.bottomMargin -= (int) (fab_breakfast.getWidth() * 1.5);
        fab_breakfast.setLayoutParams(paramsBreakfast);
        fab_breakfast.startAnimation(Back_Breakfast);

        FrameLayout.LayoutParams paramsLunch = (FrameLayout.LayoutParams) fab_lunch.getLayoutParams();
        paramsLunch.bottomMargin -= (int) (fab_lunch.getWidth() * 2.6);
        fab_lunch.setLayoutParams(paramsLunch);
        fab_lunch.startAnimation(Back_Lunch);

        FrameLayout.LayoutParams paramsDinner = (FrameLayout.LayoutParams) fab_dinner.getLayoutParams();
        paramsDinner.bottomMargin -= (int) (fab_dinner.getWidth() * 3.7);
        fab_dinner.setLayoutParams(paramsDinner);
        fab_dinner.startAnimation(Back_Dinner);

        FrameLayout.LayoutParams paramsTrain = (FrameLayout.LayoutParams) fab_train.getLayoutParams();
        paramsTrain.bottomMargin -= (int) (fab_train.getWidth() * 4.8);
        fab_train.setLayoutParams(paramsTrain);
        fab_train.startAnimation(Back_Train);

    }

}