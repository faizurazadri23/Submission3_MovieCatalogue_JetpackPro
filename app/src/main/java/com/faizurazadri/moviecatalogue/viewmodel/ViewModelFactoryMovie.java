package com.faizurazadri.moviecatalogue.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.faizurazadri.moviecatalogue.data.source.MovieRepository;
import com.faizurazadri.moviecatalogue.di.Injection;
import com.faizurazadri.moviecatalogue.ui.detail.DetailMovieViewModel;
import com.faizurazadri.moviecatalogue.ui.favorite.FavoriteMoviesViewModel;
import com.faizurazadri.moviecatalogue.ui.movie.MovieViewModel;

public class ViewModelFactoryMovie extends ViewModelProvider.NewInstanceFactory {

    public static volatile ViewModelFactoryMovie INSTANCE;

    private final MovieRepository movieRepository;

    public ViewModelFactoryMovie(MovieRepository provideRepositoryMovie) {
        movieRepository = provideRepositoryMovie;
    }

    public static ViewModelFactoryMovie getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactoryMovie.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactoryMovie(Injection.provideRepositoryMovie(context));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(MovieViewModel.class)) {
            return (T) new MovieViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(DetailMovieViewModel.class)) {
            return (T) new DetailMovieViewModel(movieRepository);
        } else if (modelClass.isAssignableFrom(FavoriteMoviesViewModel.class)) {
            return (T) new FavoriteMoviesViewModel(movieRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
