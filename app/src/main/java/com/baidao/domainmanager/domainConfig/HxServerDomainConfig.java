package com.baidao.domainmanager.domainConfig;

import com.baidao.domain.CommonServerDomainType;
import com.baidao.domain.ServerDomainType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rjhy
 * @created on 16-8-31
 * @desc desc
 */
public class HxServerDomainConfig {
    private static Map<ServerDomainType, String> PRODUCT = new HashMap<ServerDomainType, String>() {{
        put(CommonServerDomainType.SSO, "http://baidao.com.sso");
        put(HxServerDomainType.NOTE, "http://private-f61f90-login44.apiary-mock.com/");
    }};

    private static Map<ServerDomainType, String> TEST = new HashMap<ServerDomainType, String>() {{
        put(CommonServerDomainType.SSO, "http://test.baidao.com.sso");
        put(HxServerDomainType.NOTE, "https://private-28d044-peel1.apiary-mock.com/");
    }};

    static Map<ServerDomainType, String> getDomain(boolean isDebug) {
        if (isDebug) {
            return TEST;
        }
        return PRODUCT;
    }
}
