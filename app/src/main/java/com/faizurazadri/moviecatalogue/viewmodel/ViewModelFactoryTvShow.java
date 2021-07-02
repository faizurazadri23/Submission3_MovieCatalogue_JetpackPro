package com.faizurazadri.moviecatalogue.viewmodel;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.faizurazadri.moviecatalogue.data.source.TvShowRepository;
import com.faizurazadri.moviecatalogue.di.Injection;
import com.faizurazadri.moviecatalogue.ui.detail.DetailTvShowViewModel;
import com.faizurazadri.moviecatalogue.ui.favorite.FavoriteTvShowViewModel;
import com.faizurazadri.moviecatalogue.ui.tvshow.TvShowViewModel;

public class ViewModelFactoryTvShow extends ViewModelProvider.NewInstanceFactory {

    public static volatile ViewModelFactoryTvShow INSTANCE;

    private final TvShowRepository tvShowRepository;

    public ViewModelFactoryTvShow(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    public static ViewModelFactoryTvShow getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (ViewModelFactoryMovie.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactoryTvShow(Injection.provideRepositoryTvShow(context));
                }
            }
        }
        return INSTANCE;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if (modelClass.isAssignableFrom(TvShowViewModel.class)) {
            return (T) new TvShowViewModel(tvShowRepository);
        } else if (modelClass.isAssignableFrom(DetailTvShowViewModel.class)) {
            return (T) new DetailTvShowViewModel(tvShowRepository);
        } else if (modelClass.isAssignableFrom(FavoriteTvShowViewModel.class)) {
            return (T) new FavoriteTvShowViewModel(tvShowRepository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }
}
