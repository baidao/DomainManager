package com.baidao.retrofitadapter;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.schedulers.Schedulers;

/**
 * Created by hexi on 16/8/5.
 */
public class RetrofitBuilder {
    private String domain;
    private boolean debug;
    private Converter.Factory convertFactory;
    private CallAdapter.Factory callFactory;
    private int connectTimeout = 30;
    private int readTimeout = 20;
    private int writeTimeout;
    private volatile List<Interceptor> interceptors;

    public RetrofitBuilder  withDomain(String domain) {
        this.domain = domain;
        return this;
    }
    public RetrofitBuilder  withDebug(boolean debug) {
        this.debug = debug;
        return this;
    }

    /**
     * body convert可选，默认GsonConverterFactory
     * @param convertFactory
     * @return
     */
    public RetrofitBuilder  withConvertFactory(Converter.Factory convertFactory) {
        this.convertFactory = convertFactory;
        return this;
    }

    /**
     * CallAdapterFactory可选，默认RxJavaCallAdapterFactory
     * @param callFactory
     * @return
     */
    public RetrofitBuilder  withCallFactory(CallAdapter.Factory callFactory) {
        this.callFactory = callFactory;
        return this;
    }

    /**
     * 可选，默认30秒
     * @param connectionTimeout
     * @return
     */
    public RetrofitBuilder  withConnectionTimeout(int connectionTimeout) {
        this.connectTimeout = connectionTimeout;
        return this;
    }

    /**
     * 可选，默认20秒
     * @param readTimeout
     * @return
     */
    public RetrofitBuilder  withReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
        return this;
    }
    public RetrofitBuilder  withWriteTimeout(int writeTimeout) {
        this.writeTimeout = writeTimeout;
        return this;
    }

    public RetrofitBuilder  addInterceptor(Interceptor interceptor) {
        if (this.interceptors == null) {
            synchronized (RetrofitBuilder.class) {
                if (interceptors == null) {
                    interceptors = new ArrayList<>();
                }
            }
        }
        interceptors.add(interceptor);
        return this;
    }

    public RetrofitBuilder withInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
        return this;
    }

    public Retrofit build() {
        if (convertFactory == null) {
            convertFactory = GsonConverterFactory.create();
        }
        if (callFactory == null) {
            callFactory = RxErrorHandlingCallAdapterFactory.createWithScheduler(Schedulers.io());
        }

        return new Retrofit.Builder()
                .baseUrl(domain)
                .addConverterFactory(convertFactory)
                .addCallAdapterFactory(callFactory)
                .client(createHttpClient())
                .build();
    }

    private OkHttpClient createHttpClient() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(this.connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS);

        if (writeTimeout > 0) {
            builder.writeTimeout(writeTimeout, TimeUnit.SECONDS);
        }

        if (interceptors != null) {
            for (Interceptor interceptor : interceptors) {
                builder.addInterceptor(interceptor);
            }
        }

        if (debug) {
            HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            builder.addInterceptor(loggingInterceptor);
        }

        return builder
                .build();
    }
}
