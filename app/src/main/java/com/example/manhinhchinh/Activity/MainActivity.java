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

import com.example.manhinhchinh.Adapter.ExerciseAdapter;
import com.example.manhinhchinh.Adapter.FoodAdapter;
import com.example.manhinhchinh.Adapter.FoodMainAdapter;
import com.example.manhinhchinh.Module.ExerciseModule;
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



    public static List<FoodModule> food = new ArrayList<FoodModule>();
    public static List<ExerciseModule> exerciseModules = new ArrayList<ExerciseModule>();
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

        progress_bar = findViewById(R.id.progress_bar);
        text_view_progress = findViewById(R.id.text_view_progress);

        //Main RecyclerView
        RecyclerView rcv_main_food = findViewById(R.id.rcv_main_food);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_main_food.setLayoutManager(linearLayoutManager);


        FoodMainAdapter foodMainAdapter = new FoodMainAdapter(getApplicationContext(), food);
        rcv_main_food.setAdapter(foodMainAdapter);
        //updateProgressBar();

        putCaloFromList();




        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcv_main_food.addItemDecoration(itemDecoration);



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
                intent.putExtra("type_food", "Bữa sáng");
                startActivity(intent);
            }
        });

        fab_lunch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Trưa", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, BreakFast.class);
                intent.putExtra("type_food", "Bữa trưa");
                startActivity(intent);
            }
        });

        fab_dinner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Trưa", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, BreakFast.class);
                intent.putExtra("type_food", "Bữa tối");
                startActivity(intent);
            }
        });

        fab_train.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Tập Luyện", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, Exercise.class);
                startActivity(intent);
            }
        });

    }

    public void putCaloFromList() {
        int sumCalo = 0;
        for (int i=0; i< food.size(); i++){
            sumCalo += Integer.parseInt(food.get(i).getCalories());
        }
        progress_bar.setProgress(sumCalo);
        text_view_progress.setText(sumCalo + "/2000kcal");
    }





    private int sumCalo(){
        int sum = 0;
        for(int i = 0; i < food.size(); i++)
            sum += parseInt(food.get(i).getCalories());
        return sum;
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