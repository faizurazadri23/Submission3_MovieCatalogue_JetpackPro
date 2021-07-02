package com.faizurazadri.moviecatalogue.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.faizurazadri.moviecatalogue.data.source.TvShowRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.data.source.local.entity.TvShowEntity;
import com.faizurazadri.moviecatalogue.utils.DataDummy;
import com.faizurazadri.moviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailTvShowViewModelTest {

    private DetailTvShowViewModel viewModel;
    private Resource<TvShowEntity> dummyTvShow = Resource.success(DataDummy.generateDummyTvShow().get(0));
    private String tvShowId = dummyTvShow.data.getId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    public TvShowRepository tvShowRepository;

    @Mock
    private Observer<TvShowEntity> tvShowEntityObserver;

    @Before
    public void setUp(){
        viewModel = new DetailTvShowViewModel(tvShowRepository);
        viewModel.setId(tvShowId);
    }

    @Test
    public void testGetTvshow() {
        MutableLiveData<Resource<TvShowEntity>> tvshow = new MutableLiveData<>();
        tvshow.setValue(dummyTvShow);
        when(tvShowRepository.getTvShowById(tvShowId)).thenReturn(tvshow);

        Observer<Resource<TvShowEntity>> observer = mock(Observer.class);
        viewModel.detail.observeForever(observer);
        verify(observer).onChanged(dummyTvShow);
    }
}