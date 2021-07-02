package com.faizurazadri.moviecatalogue.ui.favorite;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.MovieRepository;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;

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
public class FavoriteMoviesViewModelTest {

    private FavoriteMoviesViewModel viewModel;

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    @Mock
    private MovieRepository movieRepository;

    @Mock
    public Observer<PagedList<MoviesEntity>> observer;

    @Mock
    private PagedList<MoviesEntity> pagedList;

    @Before
    public void setUp(){
        viewModel = new FavoriteMoviesViewModel(movieRepository);
    }

    @Test
    public void getFavorite(){
        PagedList<MoviesEntity> dummyMovies = pagedList;
        when(dummyMovies.size()).thenReturn(3);
        MutableLiveData<PagedList<MoviesEntity>> movies = new MutableLiveData<>();
        movies.setValue(dummyMovies);

        when(movieRepository.getFavoriteMovies()).thenReturn(movies);
        List<MoviesEntity> moviesEntities = viewModel.getFavorite().getValue();
        verify(movieRepository).getFavoriteMovies();
        assertNotNull(moviesEntities);
        assertEquals(3, moviesEntities.size());

        viewModel.getFavorite().observeForever(observer);
        verify(observer).onChanged(dummyMovies);
    }
}