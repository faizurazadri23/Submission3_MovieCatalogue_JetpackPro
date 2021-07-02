package com.faizurazadri.moviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.local.LocalDataSource;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.data.source.remote.RemoteRepository;
import com.faizurazadri.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.faizurazadri.moviecatalogue.utils.AppExecutors;
import com.faizurazadri.moviecatalogue.utils.DataDummy;
import com.faizurazadri.moviecatalogue.utils.LiveDataTestUtil;
import com.faizurazadri.moviecatalogue.utils.PagedListUtil;
import com.faizurazadri.moviecatalogue.vo.Resource;

import org.junit.Rule;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class TvShowRepositoryTest{
    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private LocalDataSource localDataSource = mock(LocalDataSource.class);
    private AppExecutors appExecutors = mock(AppExecutors.class);
    private FakeTvShowRepository tvShowRepository = new FakeTvShowRepository(localDataSource, remoteRepository,  appExecutors);

    private ArrayList<TvShowResponse> tvShowResponses = DataDummy.generateRemoteDummyTvShow();
    private String tvShowId = tvShowResponses.get(0).getId();

    @Test
    public void getAllTvShow(){
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getAllTvShows()).thenReturn(dataSourceFactory);
        tvShowRepository.getAllTvShow();

        Resource<PagedList<TvShowEntity>> tvShowEntites = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()));
        verify(localDataSource).getAllTvShows();
        assertNotNull(tvShowEntites.data);
        assertEquals(tvShowResponses.size(), tvShowEntites.data.size());
    }

    @Test
    public void getAllTvShowById(){
        MutableLiveData<TvShowEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DataDummy.generateDummyTvShow().get(0));

        when(localDataSource.getTvShowById(tvShowId)).thenReturn(dummyEntity);
        Resource<TvShowEntity> result = LiveDataTestUtil.getValue(tvShowRepository.getTvShowById(tvShowId));
        verify(localDataSource).getTvShowById(tvShowId);

        TvShowResponse response = tvShowResponses.get(0);
        TvShowEntity resultData = result.data;

        assertNotNull(result.data);
        assertNotNull(result.data.getOriginal_language());
        assertNotNull(response.getId(), resultData.getId());
        assertNotNull(response.getName(), resultData.getName());
        assertNotNull(response.getFirst_air_date(), resultData.getFirst_air_date());
        assertNotNull(response.getOriginal_language(), resultData.getOriginal_language());
        assertNotNull(response.getVote_average(), resultData.getVote_average());
        assertNotNull(response.getPopularity(), resultData.getPopularity());
        assertNotNull(response.getOverview(), resultData.getOverview());
        assertNotNull(response.getBackdrop_path(), resultData.getBackdrop_path());
    }

    @Test
    public void getFavorite(){
        DataSource.Factory<Integer, TvShowEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getFavoriteTvShow()).thenReturn(dataSourceFactory);
        tvShowRepository.getFavoriteTvShow();

        Resource<PagedList<TvShowEntity>> tvShowEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyTvShow()));
        verify(localDataSource).getFavoriteTvShow();
        assertNotNull(tvShowEntities);
        assertEquals(tvShowResponses.size(), tvShowEntities.data.size());
    }

}