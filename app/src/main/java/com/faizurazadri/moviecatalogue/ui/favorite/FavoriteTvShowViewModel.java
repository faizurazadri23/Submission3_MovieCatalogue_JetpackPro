package com.faizurazadri.moviecatalogue.ui.favorite;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.TvShowRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;

public class FavoriteTvShowViewModel extends ViewModel {
    private TvShowRepository tvShowRepository;

    public FavoriteTvShowViewModel(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    public LiveData<PagedList<TvShowEntity>> getFavorite() {
        return tvShowRepository.getFavoriteTvShow();
    }

    void setFavorite(TvShowEntity tvShowEntity) {
        final boolean newState = !tvShowEntity.isFavorite();
        tvShowRepository.setFavoriteTvShow(tvShowEntity, newState);
    }
}
