package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.example.manhinhchinh.Module.ExerciseModule;
import com.example.manhinhchinh.R;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class DetailExerciseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_exercise1);

        //ánh xạ detail exercise
        TextView tv_exercise_detail = findViewById(R.id.tv_exercise_detail);
        TextView tv_exercise_detail_calo = findViewById(R.id.tv_exercise_detail_calo);
        TextView tv_exercise_unit = findViewById(R.id.tv_exercise_unit);
        TextView tv_exercise_des = findViewById(R.id.tv_exercise_des);
        CircleImageView img_exercise = findViewById(R.id.img_exercise);

        //detail Exercise
        Bundle bundleEX = getIntent().getExtras();
        if (bundleEX == null){
            return;
        }
        ExerciseModule exerciseModule = (ExerciseModule) bundleEX.get("object_exercise");
        tv_exercise_detail.setText(exerciseModule.getExerciseName());
        tv_exercise_detail_calo.setText(exerciseModule.getCalories());
        tv_exercise_unit.setText(exerciseModule.getUnit());
        tv_exercise_des.setText(exerciseModule.getDescription());
        Picasso.with(this).load(exerciseModule.getPicture()).into(img_exercise);
    }
}