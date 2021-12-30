package com.example.manhinhchinh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.manhinhchinh.Activity.DetailActivity;
import com.example.manhinhchinh.Activity.DetailExerciseActivity;
import com.example.manhinhchinh.Activity.Exercise;
import com.example.manhinhchinh.Activity.MainActivity;
import com.example.manhinhchinh.Module.ExerciseModule;
import com.example.manhinhchinh.Module.FoodModule;
import com.example.manhinhchinh.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> implements Filterable {

    private Context eConText;
    private List<ExerciseModule> mListExercise;
    private List<ExerciseModule> mListExerciseOld;

    public ExerciseAdapter(Context eConText, List<ExerciseModule> mListExercise) {
        this.eConText = eConText;
        this.mListExercise = mListExercise;
        this.mListExerciseOld = mListExercise;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercire, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        ExerciseModule exercise = mListExercise.get(position);
        if (exercise == null){
            return;
        }
        Picasso.with(eConText).load(exercise.getPicture()).into(holder.img_exercise);
        holder.tv_name_exercise.setText(exercise.getExerciseName());
        holder.tv_calo_exercise.setText(exercise.getCalories());

        holder.layout_exercise_and_calo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetailExercise(exercise);
            }
        });

        holder.btnExercise.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.exerciseModules.add(exercise);
                Intent intent = new Intent(eConText, MainActivity.class);
                eConText.startActivity(intent);

            }
        });
    }

    private void onClickGoToDetailExercise(ExerciseModule exercise) {
        Intent intent = new Intent(eConText, DetailExerciseActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_exercise", exercise);
        intent.putExtras(bundle);
        eConText.startActivity(intent);
    }

    @Override
    public int getItemCount() {
        if (mListExercise != null){
            return mListExercise.size();
        }
        return 0;
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    mListExercise = mListExerciseOld;
                } else {
                    List<ExerciseModule> list = new ArrayList<>();
                    for (ExerciseModule exercise : mListExerciseOld){
                        if (exercise.getExerciseName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(exercise);
                        }
                    }

                    mListExercise = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListExercise;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListExercise = (List<ExerciseModule>) results.values;
                notifyDataSetChanged();
            }
        };
    }

    public class ExerciseViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout layout_item_exercise;
        private LinearLayout layout_exercise_and_calo;
        private CircleImageView img_exercise;
        private TextView tv_name_exercise;
        private TextView tv_calo_exercise;
        private Button btnExercise;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item_exercise = itemView.findViewById(R.id.layout_item_exercise);
            layout_exercise_and_calo = itemView.findViewById(R.id.layout_exercise_and_calo);
            img_exercise = itemView.findViewById(R.id.img_exercise);
            tv_name_exercise = itemView.findViewById(R.id.tv_name_exercise);
            tv_calo_exercise = itemView.findViewById(R.id.tv_calo_exercise);
            btnExercise = itemView.findViewById(R.id.btnExercise);


        }
    }
}
