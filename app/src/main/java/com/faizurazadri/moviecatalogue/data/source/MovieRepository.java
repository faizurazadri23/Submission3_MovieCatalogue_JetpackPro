package com.faizurazadri.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PagedList;

import com.faizurazadri.moviecatalogue.data.source.local.LocalDataSource;
import com.faizurazadri.moviecatalogue.data.source.local.entity.MoviesEntity;
import com.faizurazadri.moviecatalogue.data.source.remote.ApiRespose;
import com.faizurazadri.moviecatalogue.data.source.remote.RemoteRepository;
import com.faizurazadri.moviecatalogue.data.source.remote.response.MovieResponse;
import com.faizurazadri.moviecatalogue.utils.AppExecutors;
import com.faizurazadri.moviecatalogue.vo.Resource;

import java.util.ArrayList;
import java.util.List;

public class MovieRepository implements MovieDataSource {

    private volatile static MovieRepository INSTANCE = null;
    private final RemoteRepository remoteRepository;
    private final LocalDataSource localDataSource;
    private final AppExecutors appExecutors;

    public MovieRepository(LocalDataSource localDataSource,RemoteRepository remoteRepository,  AppExecutors appExecutors) {
        this.localDataSource = localDataSource;
        this.remoteRepository = remoteRepository;
        this.appExecutors = appExecutors;
    }

    public static MovieRepository getInstance(LocalDataSource localDataSource, RemoteRepository remoteRepository,  AppExecutors appExecutors){
        if (INSTANCE == null){
            synchronized (MovieRepository.class){
                if (INSTANCE == null){
                    INSTANCE = new MovieRepository(localDataSource, remoteRepository , appExecutors);
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public LiveData<Resource<PagedList<MoviesEntity>>> getAllMovies() {

        return new NetworkBoundResource<PagedList<MoviesEntity>, List<MovieResponse>>(appExecutors){

            @Override
            protected LiveData<PagedList<MoviesEntity>> loadFromtDB() {
                PagedList.Config config = new PagedList.Config.Builder()
                        .setEnablePlaceholders(false)
                        .setInitialLoadSizeHint(4)
                        .setPageSize(4)
                        .build();
                return new LivePagedListBuilder<>(localDataSource.getAllMovies(), config).build();
            }

            @Override
            protected Boolean shouldFetch(PagedList<MoviesEntity> data) {
                return (data == null) || (data.size() == 0);
            }

            @Override
            protected LiveData<ApiRespose<List<MovieResponse>>> createCall() {
                return remoteRepository.getDataAllMovies();
            }

            @Override
            protected void saveCallResult(List<MovieResponse> data) {
                List<MoviesEntity> moviesEntities = new ArrayList<>();

                for (MovieResponse movieResponse : data) {
                    moviesEntities.add(new MoviesEntity(
                            movieResponse.getBackdrop_path(),
                            movieResponse.getId(),
                            movieResponse.getOriginal_language(),
                            movieResponse.getOriginal_title(),
                            movieResponse.getOverview(),
                            movieResponse.getPopularity(),
                            movieResponse.getPoster_path(),
                            movieResponse.getRelease_date(),
                            movieResponse.getTitle(),
                            movieResponse.getVote_average(),
                            movieResponse.getVote_count()
                    ));

                    localDataSource.insertMovies(moviesEntities);
                }
            }
        }.asLiveData();
    }

    @Override
    public LiveData<Resource<MoviesEntity>> getMovieById(String movieId) {

        return new NetworkBoundResource<MoviesEntity, MovieResponse>(appExecutors){

            @Override
            protected LiveData<MoviesEntity> loadFromtDB() {
                return localDataSource.getMovieById(movieId);
            }

            @Override
            protected Boolean shouldFetch(MoviesEntity data) {
                return data == null;
            }

            @Override
            protected LiveData<ApiRespose<MovieResponse>> createCall() {
                return null;
            }

            @Override
            protected void saveCallResult(MovieResponse data) {

            }
        }.asLiveData();
    }

    @Override
    public LiveData<PagedList<MoviesEntity>> getFavoriteMovies() {
        PagedList.Config config = new PagedList.Config.Builder()
                .setEnablePlaceholders(false)
                .setInitialLoadSizeHint(4)
                .setPageSize(4)
                .build();

        return new LivePagedListBuilder<>(localDataSource.getFavoriteMovies(), config).build();
    }

    @Override
    public void setFavoriteMovies(MoviesEntity moviesEntity, boolean state) {
        appExecutors.diskIO().execute(() -> localDataSource.setMovieFavorite(moviesEntity, state));
    }
}
