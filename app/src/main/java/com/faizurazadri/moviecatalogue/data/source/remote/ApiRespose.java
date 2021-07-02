package com.faizurazadri.moviecatalogue.data.source.remote;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import static com.faizurazadri.moviecatalogue.data.source.remote.StatusResponse.EMPTY;
import static com.faizurazadri.moviecatalogue.data.source.remote.StatusResponse.ERROR;
import static com.faizurazadri.moviecatalogue.data.source.remote.StatusResponse.SUCCESS;

public class ApiRespose<T> {

    @NonNull
    public final StatusResponse status;

    @Nullable
    public final String message;

    @Nullable
    public final T body;

    public ApiRespose(@NonNull StatusResponse status, @Nullable String message, @Nullable T body) {
        this.status = status;
        this.message = message;
        this.body = body;
    }

    public static <T> ApiRespose<T> success(@Nullable T body){
        return new ApiRespose<>(SUCCESS, null, body);
    }

    public static <T> ApiRespose<T> empty(String msg, @Nullable T body){
        return new ApiRespose<>(EMPTY, msg, body);
    }

    public static <T> ApiRespose<T> error(String message, @Nullable T body){
        return new ApiRespose<>(ERROR, message, body);
    }
}
