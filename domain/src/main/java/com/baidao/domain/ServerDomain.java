package com.baidao.domain;

import android.content.Context;

import java.util.Map;

/**
 * Created by hexi on 16/1/28.
 */
public class ServerDomain {
    private static Context context;
    private static ServerDomainFactory domainFactory;
    private static Map<ServerDomainType, String> DOMAINS;

    static void initialize(Context context, ServerDomainFactory domainFactory) {
        ServerDomain.context = context;
        ServerDomain.domainFactory = domainFactory;
    }

    static void clear() {
        if (DOMAINS == null) {
            return;
        }
        synchronized (ServerDomain.class) {
            DOMAINS = null;
        }
    }

    static String get(ServerDomainType domainType) {
        if (DOMAINS == null) {
            synchronized (ServerDomain.class) {
                if (DOMAINS == null) {
                    DOMAINS = domainFactory.getDomain(context, DomainUtil.isDebug());
                }
            }
        }
        return DOMAINS.get(domainType);
    }
}
