package com.example.gyogynovenykisokos.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.gyogynovenykisokos.entity.Favourites;

import java.util.List;


@Dao
public interface FavouritesDAO {

    @Insert()
    void insert(Favourites favourites);

    @Query("DELETE FROM favourites")
    void deleteAll();
    @Delete
    public void deleteFavourites(Favourites favourites);

    @Query("SELECT * FROM favourites ORDER BY id ASC")
    LiveData<List<Favourites>> getAlphabetizedFavourites();

}
