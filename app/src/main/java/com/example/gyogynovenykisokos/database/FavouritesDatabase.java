package com.example.gyogynovenykisokos.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.gyogynovenykisokos.dao.FavouritesDAO;
import com.example.gyogynovenykisokos.entity.Favourites;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Favourites.class}, version = 1, exportSchema = false)
public abstract class FavouritesDatabase extends RoomDatabase {

    public abstract FavouritesDAO favDAO();

    private static volatile FavouritesDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    public static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static FavouritesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (FavouritesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                    FavouritesDatabase.class, "favourites")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
