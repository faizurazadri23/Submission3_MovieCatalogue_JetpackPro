package com.faizurazadri.moviecatalogue.data.source.local.room;

import androidx.annotation.WorkerThread;
import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;

import java.util.List;

@Dao
public interface MoviesCatalogueDao {

    @WorkerThread
    @Query("SELECT * FROM moviesentities")
    DataSource.Factory<Integer, MoviesEntity> getMovies();

    @WorkerThread
    @Query("SELECT * FROM moviesentities WHERE id = :id")
    LiveData<MoviesEntity> getMovieById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMovies(List<MoviesEntity> movies);

    @Update
    void updateMovies(MoviesEntity movies);


    @WorkerThread
    @Query("SELECT * FROM tvshowentities")
    DataSource.Factory<Integer, TvShowEntity> getTvShow();

    @WorkerThread
    @Query("SELECT * FROM tvshowentities WHERE id = :id")
    LiveData<TvShowEntity> getTvShowById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertTvShow(List<TvShowEntity> tvShow);

    @Update
    void updateTvShow(TvShowEntity tvShow);

    @WorkerThread
    @Query("SELECT * FROM moviesentities where favorite = 1")
    DataSource.Factory<Integer, MoviesEntity> getFavoriteMovies();

    @WorkerThread
    @Query("SELECT * FROM tvshowentities where favorite = 1")
    DataSource.Factory<Integer, TvShowEntity> getFavoriteTvShow();


}
