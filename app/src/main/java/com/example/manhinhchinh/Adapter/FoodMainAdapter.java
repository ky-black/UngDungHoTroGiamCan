package com.example.manhinhchinh.Adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.chauthai.swipereveallayout.SwipeRevealLayout;
import com.chauthai.swipereveallayout.ViewBinderHelper;
import com.example.manhinhchinh.Activity.MainActivity;
import com.example.manhinhchinh.Module.FoodDetailsModule;
import com.example.manhinhchinh.Module.FoodModule;
import com.example.manhinhchinh.R;
import com.example.manhinhchinh.ultil.MySingleton;
import com.example.manhinhchinh.ultil.Server;
import com.squareup.picasso.Picasso;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodMainAdapter extends RecyclerView.Adapter<FoodMainAdapter.FoodMainViewHolder>{

    public List<FoodModule> mListMainFood;
    private ViewBinderHelper viewBinderHelper = new ViewBinderHelper();
    Context mContext;
    private ArrayList<FoodDetailsModule> arrayListDetail;
    public String IDTK;
    public FoodMainAdapter(Context mContext, List<FoodModule> mListMainFood, String IDTK,ArrayList<FoodDetailsModule> arrayListDetail ) {
        this.mListMainFood = mListMainFood;
        this.mContext = mContext;
        this.IDTK = IDTK;
        this.arrayListDetail  =arrayListDetail;
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
                deleteFoodDetail(getIDByFood(mListMainFood.get(holder.getAdapterPosition())));
                mListMainFood.remove(holder.getAdapterPosition());
                notifyItemRemoved(holder.getAdapterPosition());
            }
        });
    }
    private String getIDByFood(FoodModule food){
        for (int i = 0 ; i < arrayListDetail.size(); i++){
            if (food.getID() == arrayListDetail.get(i).getIDTA()){
                return arrayListDetail.get(i).getID();
            }
        }
        return "";
    }

    private void deleteFoodDetail(String ID) {
        String url = Server.urlGetFoodByID + ID;
        RequestQueue requestQueue = Volley.newRequestQueue(mContext);
        StringRequest request = new StringRequest(Request.Method.DELETE,url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("Thành công", response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.i("Error", error.toString());
            }
        });
        requestQueue.add(request);
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
