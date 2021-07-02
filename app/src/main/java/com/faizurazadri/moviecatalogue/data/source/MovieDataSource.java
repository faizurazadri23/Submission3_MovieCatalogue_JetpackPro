package com.faizurazadri.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.vo.Resource;

import java.util.List;

public interface MovieDataSource {
    LiveData<Resource<PagedList<MoviesEntity>>> getAllMovies();
    LiveData<Resource<MoviesEntity>> getMovieById(String movieId);


    LiveData<PagedList<MoviesEntity>> getFavoriteMovies();

    void setFavoriteMovies(MoviesEntity moviesEntity, boolean state);

}
