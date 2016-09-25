package com.baidao.domain;

import android.content.Context;

import java.util.Map;

/**
 * Created by hexi on 16/1/28.
 */
public abstract class PageDomainFactory {

    public abstract Map<PageDomainType,String> getDomain(Context context, boolean isDebug);
}
