package com.faizurazadri.moviecatalogue.data.source.local.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;

@Database(entities = {MoviesEntity.class, TvShowEntity.class},
version = 1,
exportSchema = false)
public abstract class MovieCatalogueDatabase extends RoomDatabase {

    public abstract MoviesCatalogueDao moviesDao();

    private static volatile MovieCatalogueDatabase INSTANCE;

    public static MovieCatalogueDatabase getInstance(Context context){
        if (INSTANCE == null){
            synchronized (MovieCatalogueDatabase.class){
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        MovieCatalogueDatabase.class, "MoviesCatalogue.db")
                        .build();

            }
        }
        return INSTANCE;
    }

}
