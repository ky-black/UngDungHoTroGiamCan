package com.example.manhinhchinh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        TextView tv_food_detail = findViewById(R.id.tv_food_detail);
        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        Food food = (Food) bundle.get("object_food");
        tv_food_detail.setText(food.getName());
    }
}