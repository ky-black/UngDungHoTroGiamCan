package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.manhinhchinh.Module.FoodModule;
import com.example.manhinhchinh.R;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tv_food_detail = findViewById(R.id.tv_food_detail);
        TextView tv_food_detail_calo = findViewById(R.id.tv_food_detail_calo);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        FoodModule food = (FoodModule) bundle.get("object_food");
        tv_food_detail.setText(food.getFoodName());
        tv_food_detail_calo.setText(food.getCalories());
    }
}