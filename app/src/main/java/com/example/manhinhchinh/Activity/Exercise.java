package com.example.manhinhchinh.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.widget.Button;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.manhinhchinh.Adapter.ExerciseAdapter;
import com.example.manhinhchinh.Adapter.FoodAdapter;
import com.example.manhinhchinh.Module.ExerciseModule;
import com.example.manhinhchinh.Module.FoodModule;
import com.example.manhinhchinh.R;
import com.example.manhinhchinh.ultil.MySingleton;
import com.example.manhinhchinh.ultil.Server;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Exercise extends AppCompatActivity {

    private RecyclerView rcv_exercise;
    private ExerciseAdapter exerciseAdapter;
    private SearchView searchViewEx;
    private Button btnExercise;
    public static List<ExerciseModule> exerciseModules = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercise);

        rcv_exercise = findViewById(R.id.rcv_exercise);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        rcv_exercise.setLayoutManager(linearLayoutManager);

        btnExercise = findViewById(R.id.btnExercise);

        getExerciseData();

        exerciseAdapter = new ExerciseAdapter(this, exerciseModules);
        rcv_exercise.setAdapter(exerciseAdapter);

        RecyclerView.ItemDecoration itemDecoration = new DividerItemDecoration(this, DividerItemDecoration.VERTICAL);
        rcv_exercise.addItemDecoration(itemDecoration);

    }

    private void getExerciseData() {
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Server.urlGetExercise, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
//                Toast.makeText(getApplicationContext(),""+response,Toast.LENGTH_LONG).show();
                if (response != null){
                    for (int i = 0; i<response.length(); i++){
                        try {
                            JSONObject jsonObjectEx = response.getJSONObject(i);
                            ExerciseModule tmpEx = new ExerciseModule(jsonObjectEx.getString("Description"),
                                        jsonObjectEx.getString("Picture"),
                                        jsonObjectEx.getString("ID"),
                                        jsonObjectEx.getString("Unit"),
                                        jsonObjectEx.getString("Calories"),
                                        jsonObjectEx.getString("ExerciseName"));
                                exerciseModules.add(tmpEx);
                                exerciseAdapter.notifyDataSetChanged();

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Loi gi "+error,Toast.LENGTH_LONG).show();
            }
        });
        MySingleton.getInstance(getApplicationContext()).addToRequestQueue(request);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchViewEx = (SearchView) menu.findItem(R.id.action_search).getActionView();
        searchViewEx.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
        searchViewEx.setMaxWidth(Integer.MAX_VALUE);

        searchViewEx.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                exerciseAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                exerciseAdapter.getFilter().filter(newText);
                return false;
            }
        });
        return true;
    }
}