package com.faizurazadri.moviecatalogue.data.source.remote;

import android.os.Handler;
import android.os.Looper;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.faizurazadri.moviecatalogue.data.source.remote.response.MovieResponse;
import com.faizurazadri.moviecatalogue.data.source.remote.response.TvShowResponse;
import com.faizurazadri.moviecatalogue.utils.EspressoIdlingResource;
import com.faizurazadri.moviecatalogue.utils.JsonHelper;

import java.util.List;

public class RemoteRepository {

    private static RemoteRepository INSTANCE;

    private final long SERVICE_LATENCY = 2000;
    private JsonHelper jsonHelper;
    private Handler handler = new Handler(Looper.getMainLooper());

    public RemoteRepository(JsonHelper jsonHelper) {
        this.jsonHelper = jsonHelper;
    }

    public static RemoteRepository getInstance(JsonHelper jsonHelper){
        if (INSTANCE == null){
            INSTANCE = new RemoteRepository(jsonHelper);
        }

        return INSTANCE;
    }

    public LiveData<ApiRespose<List<MovieResponse>>> getDataAllMovies(){
        EspressoIdlingResource.increment();
        MutableLiveData<ApiRespose<List<MovieResponse>>> resultMovies = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultMovies.setValue(ApiRespose.success(jsonHelper.movieResponses()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY);
        return resultMovies;
    }

    public LiveData<ApiRespose<List<TvShowResponse>>> getDataAllTVShow(){
        EspressoIdlingResource.increment();
        MutableLiveData<ApiRespose<List<TvShowResponse>>> resultTvShow = new MutableLiveData<>();
        handler.postDelayed(() -> {
            resultTvShow.setValue(ApiRespose.success(jsonHelper.tvShowResponses()));
            EspressoIdlingResource.decrement();
        }, SERVICE_LATENCY);

        return resultTvShow;
    }
}
