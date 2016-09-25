package com.baidao.domain;

import android.content.Context;

import java.util.Map;

/**
 * Created by hexi on 16/1/28.
 */
public class PageDomain {
    private static Context context;
    private static PageDomainFactory domainFactory;
    private static Map<PageDomainType, String> DOMAINS;

    static void initialize(Context context, PageDomainFactory domainFactory) {
        PageDomain.context = context;
        PageDomain.domainFactory = domainFactory;
    }

    static void clear() {
        if (DOMAINS == null) {
            return;
        }
        synchronized (ServerDomain.class) {
            DOMAINS = null;
        }
    }

    static String get(PageDomainType domainType) {
        if (DOMAINS == null) {
            DOMAINS = domainFactory.getDomain(context, DomainUtil.isDebug());
        }
        return DOMAINS.get(domainType);
    }
}
