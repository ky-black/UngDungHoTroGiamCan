package com.example.manhinhchinh.Activity;

import static java.lang.Integer.parseInt;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manhinhchinh.Adapter.FoodAdapter;
import com.example.manhinhchinh.Adapter.FoodMainAdapter;
import com.example.manhinhchinh.Module.FoodModule;
import com.example.manhinhchinh.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton add_btn, fab_breakfast, fab_lunch, fab_dinner, fab_train;
    Animation Move_Breakfast, Move_Lunch, Move_Dinner, Move_Train,
            Back_Breakfast, Back_Lunch, Back_Dinner, Back_Train;
    boolean moveBack = false;

    Button button_decr, button_incr, btnAnSang;
    ProgressBar progress_bar;
    TextView text_view_progress;
    int ketQua;

    int progress = 0;


    public static List<FoodModule> food = new ArrayList<FoodModule>();
    //chọn 1 trong 2
//    FoodMainAdapter foodMainAdapter = null;
    FoodAdapter foodMainAdapter = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Ánh Xạ
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

        button_decr = findViewById(R.id.button_decr);
        button_incr = findViewById(R.id.button_incr);
        progress_bar = findViewById(R.id.progress_bar);
        text_view_progress = findViewById(R.id.text_view_progress);

        //Main RecyclerView
        RecyclerView rcv_main_food = findViewById(R.id.rcv_main_food);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_main_food.setLayoutManager(linearLayoutManager);


        FoodMainAdapter foodMainAdapter = new FoodMainAdapter(getApplicationContext(), food);
        rcv_main_food.setAdapter(foodMainAdapter);
        //updateProgressBar();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String valueShow = bundle.getString("pass_calo_food", "");
            progress += Integer.parseInt(valueShow);
            Toast.makeText(this, "Show value: " + progress, Toast.LENGTH_SHORT).show();
        }

        for (int i=0; i< progress; i++){
            ketQua += Integer.parseInt(MainActivity.food.get(i).getCalories().substring(0, 3));
        }
        progress_bar.setProgress(ketQua);
        text_view_progress.setText(String.valueOf(ketQua));
        foodMainAdapter.notifyDataSetChanged();


//        FoodAdapter foodMainAdapter = new FoodAdapter(this,list);
//        rcv_main_food.setAdapter(foodMainAdapter);


        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcv_main_food.addItemDecoration(itemDecoration);


//        button_decr.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (progress >= 0){
//                    progress -= 10;
//                    updateProgressBar();
//                }
//            }
//        });

        //Float Button
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

        fab_breakfast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Sáng", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, BreakFast.class);
                startActivity(intent);
            }
        });

        fab_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Trưa", Toast.LENGTH_SHORT).show();
            }
        });

        fab_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Chiều", Toast.LENGTH_SHORT).show();
            }
        });

        fab_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Tập Luyện", Toast.LENGTH_SHORT).show();
            }
        });

    }

//    @Override
//    protected void onRestart() {
//        super.onRestart();
//        updateProgressBar();
//    }


    //    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        btnAnSang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                if (progress <= 100){
//                    progress += 10;
//                    updateProgressBar();
//                }
//            }
//        });
//    }



    private int sumCalo(){
        int sum = 0;
        for(int i = 0; i < food.size(); i++)
            sum += parseInt(food.get(i).getCalories());
        return sum;
    }

    private void updateProgressBar() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null) {
            String valueShow = bundle.getString("pass_calo_food", "");
//            progress = Integer.parseInt(valueShow.substring(0, 3));
            progress = Integer.parseInt(valueShow);
            Toast.makeText(this, "Show value: " + progress, Toast.LENGTH_SHORT).show();

        }

        progress_bar.setProgress(progress);
        text_view_progress.setText(String.valueOf(progress));
//        progress_bar.setProgress(progress);
//        text_view_progress.setText(progress);
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