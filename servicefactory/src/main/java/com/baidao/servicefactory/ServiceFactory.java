package com.baidao.servicefactory;

import com.baidao.domain.DomainUtil;
import com.baidao.domain.ServerDomainType;
import com.baidao.retrofitadapter.RetrofitBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import okhttp3.Interceptor;
import retrofit2.Retrofit;

/**
 * Created by hexi on 16/8/10.
 */
public class ServiceFactory {

    private volatile static ServiceFactory instance;

    public static ServiceFactory getInstance() {
        if (instance == null) {
            synchronized (ServiceFactory.class) {
                if (instance == null) {
                    instance = new ServiceFactory();
                }
            }
        }
        return instance;
    }

    private Map<ServerDomainType, Object> apis;
    private List<Interceptor> interceptors;

    private ServiceFactory() {
        apis = new HashMap<>();
    }

    public void clearCache() {
        apis.clear();
        DomainUtil.clear();
    }

    public void addInterceptor(Interceptor interceptor) {
        if (this.interceptors == null) {
            synchronized (RetrofitBuilder.class) {
                if (interceptors == null) {
                    interceptors = new ArrayList<>();
                }
            }
        }
        interceptors.add(interceptor);
    }

    public void setInterceptors(List<Interceptor> interceptors) {
        this.interceptors = interceptors;
    }

    public <T> T create(ServerDomainType serverDomainType, Class<T> tClass) {
        T api = (T) apis.get(serverDomainType);
        if (api == null) {
            Retrofit retrofit = new RetrofitBuilder()
                    .withDomain(DomainUtil.getServerDomain(serverDomainType))
                    .withDebug(DomainUtil.isDebug())
                    .withInterceptors(interceptors)
                    .build();

            api = retrofit.create(tClass);

            apis.put(serverDomainType, api);
        }
        return api;
    }
}
