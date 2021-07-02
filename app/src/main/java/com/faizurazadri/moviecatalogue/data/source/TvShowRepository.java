package com.faizurazadri.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.local.LocalDataSource;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.data.source.remote.ApiRespose;
import com.faizurazadri.moviecatalogue.data.source.remote.RemoteRepository;
import com.faizurazadri.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.faizurazadri.moviecatalogue.utils.AppExecutors;
import com.faizurazadri.moviecatalogue.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class TvShowRepository implements TvShowDataSource {


    private volatile static TvShowRepository INSTANCE = null;
    private final LocalDataSource localDataSource;
    private final RemoteRepository remoteRepository;
    private final AppExecutors appExecutors;

    public TvShowRepository(LocalDataSource localDataSource, RemoteRepository remoteRepository, AppExecutors appExecutors) {
        this.localDataSource = localDataSource;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static TvShowRepository getInstance(LocalDataSource localDataSource, RemoteRepository remoteRepository, AppExecutors appExecutors) {
        if (INSTANCE == null) {
            synchronized (TvShowRepository.class) {
                if (INSTANCE == null) {
                    INSTANCE = new TvShowRepository(localDataSource, remoteRepository, appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<PagedList<TvShowEntity>>> getAllTvShow() {

        return new NetworkBoundResource<PagedList<TvShowEntity>, List<TvShowResponse>>(appExecutors) {

            @Override
            protected LiveData<PagedList<TvShowEntity>> loadFromtDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllTvShows(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<TvShowEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiRespose<List<TvShowResponse>>> createCall() {
                return remoteRepository.getDataAllTVShow();
            }

            @Override
            protected void saveCallResult(List<TvShowResponse> data) {
                List<TvShowEntity> tvShowEntities = new ArrayList<>();

                for (TvShowResponse tvShowResponse : data) {
                    tvShowEntities.add(new TvShowEntity(
                            tvShowResponse.getBackdrop_path(),
                            tvShowResponse.getId(),
                            tvShowResponse.getOriginal_language(),
                            tvShowResponse.getOriginal_name(),
                            tvShowResponse.getOverview(),
                            tvShowResponse.getPopularity(),
                            tvShowResponse.getPoster_path(),
                            tvShowResponse.getFirst_air_date(),
                            tvShowResponse.getName(),
                            tvShowResponse.getVote_average(),
                            tvShowResponse.getVote_count()
                    ));

                    localDataSource.insertTvShow(tvShowEntities);
                }
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<TvShowEntity>> getTvShowById(String tvshowId) {

        return new NetworkBoundResource<TvShowEntity, TvShowResponse>(appExecutors) {

            @Override
            protected LiveData<TvShowEntity> loadFromtDB() {
                return localDataSource.getTvShowById(tvshowId);
            }

            @Override
            protected Boolean shouldFetch(TvShowEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiRespose<TvShowResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(TvShowResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<PagedList<TvShowEntity>> getFavoriteTvShow() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();

        return new LivePagedListBuilder<>(localDataSource.getFavoriteTvShow(), config).build();
    }

    @Override
    public void setFavoriteTvShow(TvShowEntity tvShowEntity, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setTvShowFavorite(tvShowEntity, state));
    }
}
