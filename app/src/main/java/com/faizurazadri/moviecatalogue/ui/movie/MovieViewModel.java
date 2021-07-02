package com.faizurazadri.moviecatalogue.ui.movie;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.MovieRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.vo.Resource;

public class MovieViewModel extends ViewModel {

    private MovieRepository movieRepository;

    public MovieViewModel(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public LiveData<Resource<PagedList<MoviesEntity>>> getMovies() {
        return movieRepository.getAllMovies();
    }

}
