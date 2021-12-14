package com.example.manhinhchinh;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodAdapter extends  RecyclerView.Adapter<FoodAdapter.UserViewHolder> implements Filterable {

    private List<Food> mListFood;
    private List<Food> mListFoodOld;

    public FoodAdapter(List<Food> mListFood) {
        this.mListFood = mListFood;
        this.mListFoodOld = mListFood;
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food, parent, false);
        return new UserViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder holder, int position) {
        Food food = mListFood.get(position);
        if (food == null){
            return;
        }
        holder.imgFood.setImageResource(food.getImage());
        holder.tvName.setText(food.getName());
        holder.tvCalo.setText(food.getCalo());
    }

    @Override
    public int getItemCount() {
        if (mListFood != null){
            return mListFood.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private CircleImageView imgFood;
        private TextView tvName;
        private TextView tvCalo;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCalo = itemView.findViewById(R.id.tv_calo);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String strSearch = constraint.toString();
                if (strSearch.isEmpty()){
                    mListFood = mListFoodOld;
                } else {
                    List<Food> list = new ArrayList<>();
                    for (Food food : mListFoodOld){
                        if (food.getName().toLowerCase().contains(strSearch.toLowerCase())){
                            list.add(food);
                        }
                    }

                    mListFood = list;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = mListFood;

                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mListFood = (List<Food>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
