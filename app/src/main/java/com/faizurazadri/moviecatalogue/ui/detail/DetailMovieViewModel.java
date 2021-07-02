package com.faizurazadri.moviecatalogue.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.faizurazadri.moviecatalogue.data.source.MovieRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.vo.Resource;

public class DetailMovieViewModel extends ViewModel {

    private MovieRepository movieRepository;
    private MutableLiveData<String> id = new MutableLiveData<>();
    private LiveData<Resource<MoviesEntity>> result;

    LiveData<Resource<MoviesEntity>> detail = Transformations.switchMap(id, data -> {
        result = movieRepository.getMovieById(getId());
        return result;
    });

    public DetailMovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public String getId() {
        return this.id.getValue();
    }

    public void setId(String id) {
        this.id.setValue(id);
    }

    public void setFavorite() {
        if (detail.getValue() != null) {
            MoviesEntity moviesEntity = detail.getValue().data;

            final boolean newState = !moviesEntity.isFavorite();
            movieRepository.setFavoriteMovies(moviesEntity, newState);
        }
    }
}
