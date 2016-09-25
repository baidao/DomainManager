package com.baidao.retrofitadapter;

import com.baidao.retrofitadapter.exception.RetrofitException;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.adapter.rxjava.HttpException;

/**
 * Created by hexi on 16/8/9.
 */
public abstract class YtxCallback<T> implements Callback<T> {

    @Override
    public final void onFailure(Call<T> call, Throwable t) {
        RetrofitException e;
        // We had non-200 http error
        if (t instanceof HttpException) {
            HttpException httpException = (HttpException) t;
            Response response = httpException.response();
            e = RetrofitException.httpError(response.raw().request().url().toString(), response, null);
        }

        // A network error happened
        else if (t instanceof IOException) {
            e = RetrofitException.networkError((IOException) t);
        }

        // We don't know what happened. We need to simply convert to an unknown error
        else {
            e = RetrofitException.unexpectedError(t);
        }

        onError(call, e);
    }

    protected abstract void onError(Call<T> call, RetrofitException e);
}
