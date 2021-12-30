package com.example.manhinhchinh.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.manhinhchinh.Activity.MainActivity;
import com.example.manhinhchinh.Module.FoodModule;
import com.example.manhinhchinh.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodMainAdapter extends RecyclerView.Adapter<FoodMainAdapter.FoodMainViewHolder>{

    public List<FoodModule> mListMainFood;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    Context mContext;

    public FoodMainAdapter(Context mContext, List<FoodModule> mListMainFood) {
        this.mListMainFood = mListMainFood;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public FoodMainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_breakfast, parent, false);
        return new FoodMainViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodMainViewHolder holder, int position) {
        FoodModule food = mListMainFood.get(position);
        if (food == null){
            return;
        }

        viewBinderHelper.bind(holder.swipeRevealLayout, String.valueOf(food.getID()));
        holder.tvFoodDelete.setText(food.getFoodName());
        holder.tv_calo_delete.setText(food.getCalories());
        Picasso.with(mContext).load(food.getPicture()).into(holder.img_food);
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
        private Button btnAnSang;
        private CircleImageView img_food;


        public FoodMainViewHolder(@NonNull View itemView) {
            super(itemView);

            swipeRevealLayout = itemView.findViewById(R.id.swipeRevealLayout);
            layoutDelete = itemView.findViewById(R.id.layout_item_delete);
            tvFoodDelete = itemView.findViewById(R.id.tv_food_delete);
            tv_calo_delete = itemView.findViewById(R.id.tv_calo_delete);
            btnAnSang = itemView.findViewById(R.id.btnAnSang);
            img_food = itemView.findViewById(R.id.img_food);
        }
    }
}
