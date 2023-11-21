package com.example.gyogynovenykisokos.favouriteviewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.gyogynovenykisokos.dao.FavouritesDAO;
import com.example.gyogynovenykisokos.database.FavouritesDatabase;
import com.example.gyogynovenykisokos.entity.Favourites;
import com.example.gyogynovenykisokos.favouritesrepository.FavouritesRepository;

import java.util.List;

public class ViewModel extends AndroidViewModel {

        private FavouritesRepository mRepository;

        private final LiveData<List<Favourites>> mAllFavourites;

        public ViewModel (Application application) {
            super(application);
            mRepository = new FavouritesRepository(application);
            mAllFavourites = mRepository.getAllFavourites();
        }

        LiveData<List<Favourites>> getAllFavourites() { return mAllFavourites; }

        public void insert(Favourites favourites) { mRepository.insert(favourites); }
}
