package com.faizurazadri.moviecatalogue.data.source;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.local.LocalDataSource;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.data.source.remote.RemoteRepository;
import com.faizurazadri.moviecatalogue.data.source.remote.response.MovieResponse;
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

public class MovieRepositoryTest{

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

    private RemoteRepository remoteRepository = mock(RemoteRepository.class);
    private LocalDataSource localDataSource = mock(LocalDataSource.class);
    private AppExecutors appExecutors = mock(AppExecutors.class);

    private FakeMovieRepository movieRepository = new FakeMovieRepository(localDataSource, remoteRepository,  appExecutors);

    private ArrayList<MovieResponse> movieResponses = DataDummy.generateRemoteDummyMovies();
    private String moviesId = movieResponses.get(0).getId();

    @Test
    public void getAllMovies(){
        DataSource.Factory<Integer, MoviesEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getAllMovies()).thenReturn(dataSourceFactory);
        movieRepository.getAllMovies();

        Resource<PagedList<MoviesEntity>> movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()));

        verify(localDataSource).getAllMovies();

        assertNotNull(movieEntities.data);
        assertEquals(movieResponses.size(), movieEntities.data.size());
    }

    @Test
    public void getAllMoviesById(){
        MutableLiveData<MoviesEntity> dummyEntity = new MutableLiveData<>();
        dummyEntity.setValue(DataDummy.generateDummyMovies().get(0));

        when(localDataSource.getMovieById(moviesId)).thenReturn(dummyEntity);
        Resource<MoviesEntity> result = LiveDataTestUtil.getValue(movieRepository.getMovieById(moviesId));

        verify(localDataSource).getMovieById(moviesId);

        MovieResponse response = movieResponses.get(0);
        MoviesEntity resultData = result.data;

        assertNotNull(result.data);
        assertNotNull(result.data.getTitle());
        assertNotNull(response.getId(), resultData.getId());
        assertNotNull(response.getVote_average(), resultData.getVote_average());
        assertNotNull(response.getOriginal_language(), resultData.getOriginal_language());
        assertNotNull(response.getRelease_date(), resultData.getRelease_date());
        assertNotNull(response.getOverview(), resultData.getOverview());
        assertNotNull(response.getPopularity(), resultData.getPopularity());
        assertNotNull(response.getVote_count(), resultData.getVote_count());
        assertNotNull(response.getBackdrop_path(), resultData.getBackdrop_path());
    }

    @Test
    public void getFavorite(){
        DataSource.Factory<Integer, MoviesEntity> dataSourceFactory = mock(DataSource.Factory.class);
        when(localDataSource.getFavoriteMovies()).thenReturn(dataSourceFactory);
        movieRepository.getFavoriteMovies();

        Resource<PagedList<MoviesEntity>> movieEntities = Resource.success(PagedListUtil.mockPagedList(DataDummy.generateDummyMovies()));

        verify(localDataSource).getFavoriteMovies();

        assertNotNull(movieEntities);
        assertEquals(movieResponses.size(), movieEntities.data.size());
    }
}