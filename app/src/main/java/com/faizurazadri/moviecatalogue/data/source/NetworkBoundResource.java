package com.faizurazadri.moviecatalogue.data.source;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;

import com.faizurazadri.moviecatalogue.data.source.remote.ApiRespose;
import com.faizurazadri.moviecatalogue.utils.AppExecutors;
import com.faizurazadri.moviecatalogue.vo.Resource;

public abstract class NetworkBoundResource<ResultType , RequestType> {

    private MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    private AppExecutors mExecutors;

    public NetworkBoundResource(AppExecutors mExecutors) {
        this.mExecutors = mExecutors;
        result.setValue(Resource.loading(null));

        LiveData<ResultType> dbSource = loadFromtDB();

        result.addSource(dbSource, data -> {
            result.removeSource(dbSource);

            if (shouldFetch(data)){
                fetcFromNetwork(dbSource);
            }else {
                result.addSource(dbSource, newData -> result.setValue(Resource.success(newData)));
            }
        });
    }

    protected void onFetchFailed(){
    }

    protected abstract LiveData<ResultType> loadFromtDB();

    protected abstract Boolean shouldFetch(ResultType data);

    protected abstract LiveData<ApiRespose<RequestType>> createCall();

    protected abstract void saveCallResult(RequestType data);

    private void fetcFromNetwork(LiveData<ResultType> dbSource){
        LiveData<ApiRespose<RequestType>> apiResponse = createCall();

        result.addSource(dbSource, newData ->
                result.setValue(Resource.loading(newData)));


        result.addSource(apiResponse, response -> {
            result.removeSource(apiResponse);
            result.removeSource(dbSource);

            switch (response.status){
                case SUCCESS:
                    mExecutors.diskIO().execute(() ->{
                        saveCallResult(response.body);

                        mExecutors.mainThread().execute(() ->
                                result.addSource(loadFromtDB(),
                                        newData -> result.setValue(Resource.success(newData))));
                    });

                    break;

                case EMPTY:
                    mExecutors.mainThread().execute(() ->
                            result.addSource(loadFromtDB(),
                                    newData -> result.setValue(Resource.success(newData))));

                    break;

                case ERROR:
                    onFetchFailed();
                    result.addSource(dbSource, newData ->
                            result.setValue(Resource.error(response.message, newData)));
                    break;
            }
        });
    }

    public LiveData<Resource<ResultType>> asLiveData(){
        return result;
    }
}
