package com.faizurazadri.moviecatalogue.ui.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.MovieRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;

public class FavoriteMoviesViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public FavoriteMoviesViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<PagedList<MoviesEntity>> getFavorite() {
        return movieRepository.getFavoriteMovies();
    }

    void setFavorite(MoviesEntity moviesEntity) {
        final boolean newState = !moviesEntity.isFavorite();
        movieRepository.setFavoriteMovies(moviesEntity, newState);
    }
}
