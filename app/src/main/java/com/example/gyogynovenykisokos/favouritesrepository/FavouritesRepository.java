package com.example.gyogynovenykisokos.favouritesrepository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.gyogynovenykisokos.dao.FavouritesDAO;
import com.example.gyogynovenykisokos.database.FavouritesDatabase;
import com.example.gyogynovenykisokos.entity.Favourites;

import java.util.List;

public class FavouritesRepository {


        private FavouritesDAO mFavouritesDAO;
        private LiveData<List<Favourites>> mAllFavourite;


        public FavouritesRepository(Application application) {
            FavouritesDatabase db = FavouritesDatabase.getDatabase(application);
            mFavouritesDAO = db.favDAO();
            mAllFavourite = mFavouritesDAO.getAlphabetizedFavourites();
        }
        public LiveData<List<Favourites>> getAllFavourites() {
            return mAllFavourite;
        }
       public void insert(Favourites favourites) {
            FavouritesDatabase.databaseWriteExecutor.execute(() -> {
                mFavouritesDAO.insert(favourites);
            });
        }
    }

