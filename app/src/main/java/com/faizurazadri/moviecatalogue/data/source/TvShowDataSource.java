package com.faizurazadri.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.vo.Resource;

import java.util.List;

public interface TvShowDataSource {
    LiveData<Resource<PagedList<TvShowEntity>>> getAllTvShow();
    LiveData<Resource<TvShowEntity>> getTvShowById(String  tvshowId);

    LiveData<PagedList<TvShowEntity>> getFavoriteTvShow();

    void setFavoriteTvShow(TvShowEntity tvShowEntity, boolean state);
}
