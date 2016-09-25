package com.baidao.domain;

import android.content.Context;

public class DomainUtil {

    private static boolean isDebug = false;

    public static boolean isDebug() {
        return isDebug;
    }

    public static void initialize(Context context, PageDomainFactory pageDomainFactory, ServerDomainFactory serverDomainFactory) {
        PageDomain.initialize(context.getApplicationContext(), pageDomainFactory);
        ServerDomain.initialize(context.getApplicationContext(), serverDomainFactory);
    }

    public static void setIsDebug(boolean isDebug) {
        DomainUtil.isDebug = isDebug;
    }

    /**
     * clear domain cache after call setIsDebug(boolean isDebug)
     */
    public static void clear() {
        ServerDomain.clear();
        PageDomain.clear();
    }

    public static String getServerDomain(ServerDomainType domainType) {
        return ServerDomain.get(domainType);
    }

    public static String getPageDomain(PageDomainType domainType) {
        return PageDomain.get(domainType);
    }

}