package com.faizurazadri.moviecatalogue.ui.tvshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.TvShowRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.vo.Resource;

public class TvShowViewModel extends ViewModel {

    public MutableLiveData<String> result = new MutableLiveData<>();
    private TvShowRepository tvShowRepository;

    public TvShowViewModel(TvShowRepository tvShowRepository) {
        this.tvShowRepository = tvShowRepository;
    }

    public LiveData<Resource<PagedList<TvShowEntity>>> getTvShow() {
        return tvShowRepository.getAllTvShow();
    }
}
