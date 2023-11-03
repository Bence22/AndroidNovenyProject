package com.example.gyogynovenykisokos;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

//import com.example.androidnovenyproject.R;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private PlantAdapter plantAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        List<PlantData> plantDataList = new ArrayList<>();

        plantAdapter = new PlantAdapter(this, plantDataList);
        recyclerView.setAdapter(plantAdapter);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Plant Finder");


        RecyclerView recyclerView = findViewById(R.id.recyclerView);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://perenual.com/api/")
                .build();
        ApiService apiService = retrofit.create(ApiService.class);
        Call<ResponseBody> call = apiService.getData("sk-O0PN65325c19a6cab2564", 1);

        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                if (response.isSuccessful()) {
                    String json = null;
                    try {
                        json = response.body().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }

                    Gson gson = new Gson();
                    Root root = gson.fromJson(json, Root.class);

                    List<PlantData> plantDataList = root.getData();


                    plantAdapter.updateData(plantDataList);

                    //for (PlantData plant : root.getData()) {
                    //System.out.println(plant.getCommon_name());


                } else {
                    Log.d("result", "Fail api call");
                }
            }

            //@Override
            //public boolean onCreateOptionsMenu(Menu menu) {
                //getMenuInflater().inflate(R.menu.main_menu, menu);
                //return true;
            //}

           //@Override
            //public boolean onOptionsItemSelected(@NonNull MenuItem item) {
                //return super.onOptionsItemSelected(item);
            //}

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d("result", "Network error");
            }
        });
    }
}