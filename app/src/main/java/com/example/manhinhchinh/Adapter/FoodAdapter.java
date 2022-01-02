package com.example.manhinhchinh.Adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.media.MediaCodec;
import android.os.Bundle;
import android.util.Log;
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

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.manhinhchinh.Activity.BreakFast;
import com.example.manhinhchinh.Activity.MainActivity;
import com.example.manhinhchinh.Activity.DetailActivity;
import com.example.manhinhchinh.Module.FoodModule;
import com.example.manhinhchinh.R;
import com.example.manhinhchinh.ultil.Server;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodAdapter extends  RecyclerView.Adapter<FoodAdapter.UserViewHolder> implements Filterable {

    private Context bConText;
    private List<FoodModule> mListFood;
    private List<FoodModule> mListFoodOld;


    public FoodAdapter(Context bConText, List<FoodModule> mListFood) {
        this.bConText = bConText;
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
        FoodModule food = mListFood.get(position);
        if (food == null){
            return;
        }
        Picasso.with(bConText).load(food.getPicture()).into(holder.imgFood);
        holder.tvName.setText(food.getFoodName());
        holder.tvCalo.setText(food.getCalories());
        holder.layout_food_and_calo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(food);
            }
        });
        holder.imgFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickGoToDetail(food);
            }
        });

        holder.btnAnSang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postDataToSever(food);
                Intent intent = new Intent(bConText, MainActivity.class);
                bConText.startActivity(intent);

            }
        });
    }

    private void postDataToSever(FoodModule food) {
        RequestQueue requestQueue = Volley.newRequestQueue(bConText);
        final String requestBody = getJSONObj(food);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, Server.urlPostFood, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.i("VOLLEY", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("VOLLEY", error.toString());
            }
        }) {
            @Override
            public String getBodyContentType() {
                return "application/json; charset=utf-8";
            }

            @Override
            public byte[] getBody() throws AuthFailureError {
                try {
                    return requestBody == null ? null : requestBody.getBytes("utf-8");
                } catch (UnsupportedEncodingException uee) {
                    return null;
                }
            }

            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String responseString = "";
                if (response != null) {
                    responseString = String.valueOf(response.statusCode);
                    // can get more details such as response.headers
                }
                return Response.success(responseString, HttpHeaderParser.parseCacheHeaders(response));
            }
        };
        requestQueue.add(stringRequest);
    }

    private String getJSONObj(FoodModule food){
        long millis=System.currentTimeMillis();
        java.sql.Date date=new java.sql.Date(millis);
        JSONObject jsonBody = new JSONObject();
        try {
            jsonBody.put("ID", null);
            jsonBody.put("IDTK", MainActivity.IDTK);
            jsonBody.put("IDTA", food.getID());
            jsonBody.put("Quantily", 1);
            jsonBody.put("Date", date);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  jsonBody.toString();
    }


    private void onClickGoToDetail(FoodModule food) {
        Intent intent = new Intent(bConText, DetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("object_food", food);
        intent.putExtras(bundle);
        bConText.startActivity(intent);
    }
    public void onClickPassFood(FoodModule food) {

        Intent intent1 = new Intent(bConText,MainActivity.class);
        Bundle bundle1 = new Bundle();
//        bundle1.putString("pass_name_food", food.getName());
        bundle1.putString("pass_calo_food", food.getCalories());
        intent1.putExtras(bundle1);
        bConText.startActivity(intent1);
    }


    @Override
    public int getItemCount() {
        if (mListFood != null){
            return mListFood.size();
        }
        return 0;
    }

    public class UserViewHolder extends RecyclerView.ViewHolder{

        private LinearLayout layout_item_food;
        private LinearLayout layout_food_and_calo;
        private CircleImageView imgFood;
        private TextView tvName;
        private TextView tvCalo, tv_food_des, tv_food_unit;
        private Button btnAnSang;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            layout_item_food = itemView.findViewById(R.id.layout_item_food);
            layout_food_and_calo = itemView.findViewById(R.id.layout_food_and_calo);
            imgFood = itemView.findViewById(R.id.img_food);
            tvName = itemView.findViewById(R.id.tv_name);
            tvCalo = itemView.findViewById(R.id.tv_calo);
            tv_food_des = itemView.findViewById(R.id.tv_food_des);
            tv_food_unit = itemView.findViewById(R.id.tv_food_unit);
            btnAnSang = itemView.findViewById(R.id.btnAnSang);
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
                    List<FoodModule> list = new ArrayList<>();
                    for (FoodModule food : mListFoodOld){
                        if (food.getFoodName().toLowerCase().contains(strSearch.toLowerCase())){
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
                mListFood = (List<FoodModule>) results.values;
                notifyDataSetChanged();
            }
        };
    }

}
