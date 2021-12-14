package com.example.manhinhchinh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;

import java.util.ArrayList;
import java.util.List;

public class BreakFast extends AppCompatActivity {

    private RecyclerView rcv_food;
    private FoodAdapter foodAdapter;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_break_fast);

        rcv_food = findViewById(R.id.rcv_food);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_food.setLayoutManager(linearLayoutManager);

        foodAdapter = new FoodAdapter(getListFood());
        rcv_food.setAdapter(foodAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcv_food.addItemDecoration(itemDecoration);
    }

    private List<Food> getListFood() {
        List<Food> listFood = new ArrayList<>();

        listFood.add(new Food(R.drawable.cafe_sua, "Cafe sữa", "300 calo"));
        listFood.add(new Food(R.drawable.cafe_den, "Cafe đen", "150 calo"));
        listFood.add(new Food(R.drawable.cafe_den, "Cafe đen", "150 calo"));
        listFood.add(new Food(R.drawable.cafe_den, "Cafe đen", "150 calo"));
        listFood.add(new Food(R.drawable.com_suon, "Sà Bì Chưởng", "700 calo"));
        listFood.add(new Food(R.drawable.cafe_den, "Cafe đen", "150 calo"));
        listFood.add(new Food(R.drawable.capuchino, "Cappuccino", "250 calo"));
        listFood.add(new Food(R.drawable.cafe_sua, "Cafe sữa", "300 calo"));
        listFood.add(new Food(R.drawable.cafe_phin, "Cafe pha phin", "200 calo"));
        listFood.add(new Food(R.drawable.cafe_phin, "Cafe pha phin", "200 calo"));
        listFood.add(new Food(R.drawable.capuchino, "Cappuccino", "250 calo"));
        listFood.add(new Food(R.drawable.cafe_phin, "Cafe pha phin", "200 calo"));
        listFood.add(new Food(R.drawable.cafe_den, "Cafe đen", "150 calo"));
        listFood.add(new Food(R.drawable.cafe_sua, "Cafe sữa", "300 calo"));
        listFood.add(new Food(R.drawable.com_suon, "Sà Bì Chưởng", "700 calo"));
        listFood.add(new Food(R.drawable.cafe_sua, "Cafe sữa", "300 calo"));
        listFood.add(new Food(R.drawable.cafe_phin, "Cafe pha phin", "200 calo"));
        listFood.add(new Food(R.drawable.cafe_phin, "Cafe pha phin", "200 calo"));
        listFood.add(new Food(R.drawable.com_suon, "Sà Bì Chưởng", "700 calo"));
        listFood.add(new Food(R.drawable.cafe_phin, "Cafe pha phin", "200 calo"));
        listFood.add(new Food(R.drawable.capuchino, "Cappuccino", "250 calo"));
        listFood.add(new Food(R.drawable.cafe_sua, "Cafe sữa", "300 calo"));
        listFood.add(new Food(R.drawable.capuchino, "Cappuccino", "250 calo"));
        listFood.add(new Food(R.drawable.cafe_sua, "Cafe sữa", "300 calo"));

        return listFood;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                foodAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                foodAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return true;
    }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()){
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();

    }
}