package com.example.manhinhchinh;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;

import java.util.List;

public class FoodMainAdapter extends RecyclerView.Adapter<FoodMainAdapter.FoodMainViewHolder>{

    private List<Food> mListMainFood;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();

    public FoodMainAdapter(List<Food> mListMainFood) {
        this.mListMainFood = mListMainFood;
    }

    @NonNull
    @Override
    public FoodMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_breakfast, parent, false);
        return new FoodMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodMainViewHolder holder, int position) {
        Food food = mListMainFood.get(position);
        if (food == null){
            return;
        }

        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(food.getId()));
        holder.tvFoodDelete.setText(food.getName());
        holder.tv_calo_delete.setText(food.getCalo());
        holder.layoutDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListMainFood.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        if (mListMainFood != null){
            return mListMainFood.size();
        }
        return 0;
    }

    public class FoodMainViewHolder extends RecyclerView.ViewHolder{

        private SwipeRevealLayout swipeRevealLayout;
        private LinearLayout layoutDelete;
        private TextView tvFoodDelete, tv_calo_delete;


        public FoodMainViewHolder(@NonNull View itemView) {
            super(itemView);

            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
            layoutDelete = itemView.findViewById(R.id.layout_item_delete);
            tvFoodDelete = itemView.findViewById(R.id.tv_food_delete);
            tv_calo_delete = itemView.findViewById(R.id.tv_calo_delete);
        }
    }
}
