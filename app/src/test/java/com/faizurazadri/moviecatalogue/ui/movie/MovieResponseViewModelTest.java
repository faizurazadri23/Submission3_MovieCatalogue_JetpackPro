package com.faizurazadri.moviecatalogue.ui.movie;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

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
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MovieResponseViewModelTest {

    private MovieViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    public MovieRepository movieRepository;

    @Mock
    private Observer<Resource<PagedList<MoviesEntity>>> observer;

    @Mock
    private PagedList<MoviesEntity> pagedList;


    @Before
    public void setUp(){
        viewModel = new MovieViewModel(movieRepository);
    }

    @Test
    public void getMovie(){
        Resource<PagedList<MoviesEntity>> dummyMovie = Resource.success(pagedList);

        when(dummyMovie.data.size()).thenReturn(5);
        MutableLiveData<Resource<PagedList<MoviesEntity>>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovie);

        when(movieRepository.getAllMovies()).thenReturn(movies);
        List<MoviesEntity> moviesEntities = viewModel.getMovies().getValue().data;
        verify(movieRepository).getAllMovies();
        assertNotNull(moviesEntities);
        assertEquals(5, moviesEntities.size());

        viewModel.getMovies().observeForever(observer);
        verify(observer).onChanged(dummyMovie);
    }
}