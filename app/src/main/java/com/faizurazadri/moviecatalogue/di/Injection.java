package com.faizurazadri.moviecatalogue.di;

import android.app.Application;
import android.content.Context;

import com.faizurazadri.moviecatalogue.data.source.MovieRepository;
import com.faizurazadri.moviecatalogue.data.source.TvShowRepository;
import com.faizurazadri.moviecatalogue.data.source.local.LocalDataSource;
import com.faizurazadri.moviecatalogue.data.source.local.room.MovieCatalogueDatabase;
import com.faizurazadri.moviecatalogue.data.source.remote.RemoteRepository;
import com.faizurazadri.moviecatalogue.utils.AppExecutors;
import com.faizurazadri.moviecatalogue.utils.JsonHelper;

public class Injection {
    public static MovieRepository provideRepositoryMovie(Context application){
        MovieCatalogueDatabase database = MovieCatalogueDatabase.getInstance(application);

        LocalDataSource localDataSource = LocalDataSource.getInstance(database.moviesDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        AppExecutors appExecutors = new AppExecutors();
        return MovieRepository.getInstance(localDataSource, remoteRepository, appExecutors);
    }

    public static TvShowRepository provideRepositoryTvShow(Context application){
        MovieCatalogueDatabase database = MovieCatalogueDatabase.getInstance(application);

        LocalDataSource localDataSource = LocalDataSource.getInstance(database.moviesDao());
        RemoteRepository remoteRepository = RemoteRepository.getInstance(new JsonHelper(application));
        AppExecutors appExecutors = new AppExecutors();
        return TvShowRepository.getInstance(localDataSource, remoteRepository, appExecutors);
    }
}
