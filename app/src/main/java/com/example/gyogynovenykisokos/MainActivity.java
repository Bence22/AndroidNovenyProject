package com.example.gyogynovenykisokos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.example.gyogynovenykisokos.favouritelistadapter.FavouriteListAdapter;
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

    private void loadFragment(Fragment fragment, String tag, boolean addToBackStack) {
        FragmentTransaction fragmentTransaction =  getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment, tag);
        if(addToBackStack) {
            fragmentTransaction.addToBackStack(tag);
        }

        fragmentTransaction.commit();
    }
    private void loadFragmentAndAddToBackStack(Fragment fragment, String tag) {
        loadFragment(fragment, tag, true);
    }


    private RecyclerView recyclerView;
    private PlantAdapter plantAdapter;
    private List<PlantData> plantDataList = new ArrayList<>();
    private List<PlantData> filteredPlantDataList = new ArrayList<>();





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        plantAdapter = new PlantAdapter(this, filteredPlantDataList, new ItemClickListener() {
            @Override
            public void onItemClick(PlantData plant) {
                Fragment profile = new ProfileFragment();
                Bundle args = new Bundle();
                args.putSerializable("plant", plant);
                profile.setArguments(args);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, profile);
                transaction.addToBackStack(null);
                transaction.commit();

            }
        });
        recyclerView.setAdapter(plantAdapter);

        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        setTitle("Plant Finder");

        loadFragment(new HomeFragment(), "home", false);

        fetchDataFromApi();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();


        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filterData(newText);
                return true;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
            int itemId = item.getItemId();

            if (itemId == R.id.action_home) {
                //showMessage("Főoldal");
                loadFragmentAndAddToBackStack(new HomeFragment(), "home");
            } else if (itemId == R.id.action_profile) {
                //showMessage("Profil");
                loadFragmentAndAddToBackStack(new ProfileFragment(), "profile");
            } else if (itemId == R.id.action_favourites) {
                //showMessage("Favourites");
                loadFragmentAndAddToBackStack(new favouritesFragment(), "favourites");
            } else {
                return super.onOptionsItemSelected(item);
            }

            return true;
        }



    private void fetchDataFromApi() {
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
                        e.printStackTrace();
                    }

                    Gson gson = new Gson();
                    Root root = gson.fromJson(json, Root.class);
                    plantDataList.addAll(root.getData());
                    filteredPlantDataList.addAll(plantDataList);
                    plantAdapter.notifyDataSetChanged();
                } else {
                    Log.d("result", "Api call error");
                }
            }



            public void onItemClick(PlantData plantData) {
                Log.d("click",plantData.getCommon_name());

                //ProfileFragment profileFragment = ProfileFragment.newInstance(plantData);
                //loadFragmentAndAddToBackStack(profileFragment, "profile");
            }

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

    private void filterData(String query) {
        filteredPlantDataList.clear();

        for (PlantData plant : plantDataList) {
            if (plant.getCommon_name().toLowerCase().contains(query.toLowerCase())) {
                filteredPlantDataList.add(plant);
            }
        }

        plantAdapter.notifyDataSetChanged();
    }
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        RecyclerView recyclerView = findViewById(R.id.recyclerview_favourite);
        final FavouriteListAdapter adapter = new FavouriteListAdapter(new FavouriteListAdapter.FavouritesDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        return inflater.inflate(R.layout.fragment_favourites, container, false);
    }
}