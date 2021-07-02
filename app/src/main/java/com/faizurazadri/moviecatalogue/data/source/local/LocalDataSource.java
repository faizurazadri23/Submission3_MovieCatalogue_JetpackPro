package com.faizurazadri.moviecatalogue.data.source.local;

import androidx.lifecycle.LiveData;
import androidx.paging.DataSource;

import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.data.source.local.room.MoviesCatalogueDao;

import java.util.List;

public class LocalDataSource {

    public static LocalDataSource INSTANCE;
    private final MoviesCatalogueDao mMovieDao;

    public LocalDataSource(MoviesCatalogueDao mMovieDao) {
        this.mMovieDao = mMovieDao;
    }

    public static LocalDataSource getInstance(MoviesCatalogueDao moviesCatalogueDao){
        if (INSTANCE == null){
            INSTANCE = new LocalDataSource(moviesCatalogueDao);
        }

        return INSTANCE;
    }

    public DataSource.Factory<Integer, MoviesEntity> getAllMovies(){
        return mMovieDao.getMovies();
    }

    public LiveData<MoviesEntity> getMovieById(String id){
        return mMovieDao.getMovieById(id);
    }

    public DataSource.Factory<Integer, TvShowEntity> getAllTvShows(){
        return mMovieDao.getTvShow();
    }

    public LiveData<TvShowEntity> getTvShowById(String id){
        return mMovieDao.getTvShowById(id);
    }

    public DataSource.Factory<Integer, MoviesEntity> getFavoriteMovies(){
        return mMovieDao.getFavoriteMovies();
    }

    public DataSource.Factory<Integer, TvShowEntity> getFavoriteTvShow(){
        return mMovieDao.getFavoriteTvShow();
    }

    public void insertMovies(List<MoviesEntity> moviesEntities){
        mMovieDao.insertMovies(moviesEntities);
    }

    public void insertTvShow(List<TvShowEntity> tvShowEntities){
        mMovieDao.insertTvShow(tvShowEntities);
    }

    public void setMovieFavorite(MoviesEntity movieFavorite, boolean newSate){
        movieFavorite.setFavorite(newSate);
        mMovieDao.updateMovies(movieFavorite);
    }

    public void setTvShowFavorite(TvShowEntity tvShowFavorite, boolean newState){
        tvShowFavorite.setFavorite(newState);
        mMovieDao.updateTvShow(tvShowFavorite);
    }

    public void updateFavoriteMovies(MoviesEntity movies){
        mMovieDao.updateMovies(movies);
    }

    public void updateFavoriteTvShow(TvShowEntity tvShow){
        mMovieDao.updateTvShow(tvShow);
    }

}
