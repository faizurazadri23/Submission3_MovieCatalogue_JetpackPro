package com.faizurazadri.moviecatalogue.ui.tvshow;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.TvShowRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.utils.DataDummy;
import com.faizurazadri.moviecatalogue.vo.Resource;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TvShowViewModelTest{

    private TvShowViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Mock
    private TvShowRepository tvShowRepository;

    @Mock
    private Observer<Resource<PagedList<TvShowEntity>>> observer;

    @Mock
    private PagedList<TvShowEntity> pagedList;

    @Before
    public void setUp(){
        viewModel = new TvShowViewModel(tvShowRepository);
    }

    @Test
    public void testGetTvShow() {
        Resource<PagedList<TvShowEntity>> dummyTvShow = Resource.success(pagedList);

        when(dummyTvShow.data.size()).thenReturn(5);
        MutableLiveData<Resource<PagedList<TvShowEntity>>> tvShow = new MutableLiveData<>();
        tvShow.setValue(dummyTvShow);

        when(tvShowRepository.getAllTvShow()).thenReturn(tvShow);
        List<TvShowEntity> tvShowEntities = viewModel.getTvShow().getValue().data;
        verify(tvShowRepository).getAllTvShow();
        Assert.assertNotNull(tvShowEntities);
        Assert.assertEquals(5, tvShowEntities.size());

        viewModel.getTvShow().observeForever(observer);
        verify(observer).onChanged(dummyTvShow);
    }
}