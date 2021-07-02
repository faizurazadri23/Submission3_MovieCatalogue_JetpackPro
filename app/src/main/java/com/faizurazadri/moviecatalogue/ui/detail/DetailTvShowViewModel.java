package com.faizurazadri.moviecatalogue.ui.detail;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;
import androidx.lifecycle.ViewModel;

import com.faizurazadri.moviecatalogue.data.source.TvShowRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.vo.Resource;

public class DetailTvShowViewModel extends ViewModel {

    private TvShowRepository tvShowRepository;
    private LiveData<Resource<TvShowEntity>> result;
    private MutableLiveData<String> id = new MutableLiveData<>();

    LiveData<Resource<TvShowEntity>> detail = Transformations.switchMap(id, data -> {
        result = tvShowRepository.getTvShowById(getId());
        return result;
    });

    public DetailTvShowViewModel(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    public String getId() {
        return this.id.getValue();
    }

    public void setId(String id) {
        this.id.setValue(id);
    }

    public void setFavorite() {
        if (detail.getValue() != null) {
            TvShowEntity entity = detail.getValue().data;
            final boolean newState = !entity.isFavorite();
            tvShowRepository.setFavoriteTvShow(entity, newState);
        }
    }
}
