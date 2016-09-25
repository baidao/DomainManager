package com.baidao.domainmanager.application;

import android.app.Application;

import com.baidao.domain.DomainUtil;
import com.baidao.domainmanager.BuildConfig;
import com.baidao.domainmanager.domainConfig.HxPageDomainFactory;
import com.baidao.domainmanager.domainConfig.HxServerDomainFactory;

/**
 * Created by hexi on 16/9/25.
 */

public class HxApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initDomainConfig();

    }

    private void initDomainConfig() {
        DomainUtil.initialize(this, new HxPageDomainFactory(), new HxServerDomainFactory());
        DomainUtil.setIsDebug(BuildConfig.DEBUG);
    }
}
