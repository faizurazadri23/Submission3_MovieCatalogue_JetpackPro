package com.faizurazadri.moviecatalogue.ui.detail;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.faizurazadri.moviecatalogue.data.source.MovieRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.utils.DataDummy;
import com.faizurazadri.moviecatalogue.vo.Resource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DetailMovieResponseViewModelTest {

    private DetailMovieViewModel viewModel;
    private Resource<MoviesEntity> dummyMovie = Resource.success(DataDummy.generateDummyMovies().get(0));
    private String movieId = dummyMovie.data.getId();

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();


    @Mock
    private MovieRepository movieRepository;

    @Mock
    private Observer<MoviesEntity> movieObserver;


    @Before
    public void setUp(){
        viewModel = new DetailMovieViewModel(movieRepository);
        viewModel.setId(movieId);
    }

    @Test
    public void getMovie(){
        MutableLiveData<Resource<MoviesEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);
        when(movieRepository.getMovieById(movieId)).thenReturn(movies);

        Observer<Resource<MoviesEntity>> observer = mock(Observer.class);
        viewModel.detail.observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }
}