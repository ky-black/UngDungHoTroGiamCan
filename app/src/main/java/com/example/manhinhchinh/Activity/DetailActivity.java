package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.manhinhchinh.Module.ExerciseModule;
import com.example.manhinhchinh.Module.FoodModule;
import com.example.manhinhchinh.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //ánh xạ detail food
        TextView tv_food_detail = findViewById(R.id.tv_food_detail);
        TextView tv_food_detail_calo = findViewById(R.id.tv_food_detail_calo);
        TextView tv_food_unit = findViewById(R.id.tv_food_unit);
        TextView tv_food_des = findViewById(R.id.tv_food_des);
        CircleImageView img_food = findViewById(R.id.img_food);


        Bundle bundle = getIntent().getExtras();
        if (bundle == null){
            return;
        }
        FoodModule food = (FoodModule) bundle.get("object_food");
        tv_food_detail.setText(food.getFoodName());
        tv_food_detail_calo.setText(food.getCalories());
        tv_food_des.setText(food.getDescription());
        tv_food_unit.setText(food.getUnit());
        Picasso.with(this).load(food.getPicture()).into(img_food);

    }
}