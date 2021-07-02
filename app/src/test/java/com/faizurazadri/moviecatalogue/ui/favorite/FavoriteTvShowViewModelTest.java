package com.faizurazadri.moviecatalogue.ui.favorite;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.TvShowRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class FavoriteTvShowViewModelTest{

    private FavoriteTvShowViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private TvShowRepository tvShowRepository;

    @Mock
    public Observer<PagedList<TvShowEntity>> observer;

    @Mock
    private PagedList<TvShowEntity> pagedList;

    @Before
    public void setUp() {
        viewModel = new FavoriteTvShowViewModel(tvShowRepository);
    }

    @Test
    public void getFavorite() {
        PagedList<TvShowEntity> dummyTvShow = pagedList;
        when(dummyTvShow.size()).thenReturn(3);
        MutableLiveData<PagedList<TvShowEntity>> tvShow = new MutableLiveData<>();
        tvShow.setValue(dummyTvShow);

        when(tvShowRepository.getFavoriteTvShow()).thenReturn(tvShow);
        List<TvShowEntity> tvShowEntities = viewModel.getFavorite().getValue();
        verify(tvShowRepository).getFavoriteTvShow();
        assertNotNull(tvShowEntities);
        assertEquals(3, tvShowEntities.size());

        viewModel.getFavorite().observeForever(observer);
        verify(observer).onChanged(dummyTvShow);
    }
}