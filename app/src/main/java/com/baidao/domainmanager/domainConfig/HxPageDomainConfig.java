package com.baidao.domainmanager.domainConfig;

import com.baidao.domain.PageDomainType;

import java.util.HashMap;
import java.util.Map;

/**
 * @author rjhy
 * @created on 16-8-31
 * @desc desc
 */
public class HxPageDomainConfig {
    private static Map<PageDomainType, String> PRODUCT = new HashMap<PageDomainType, String>() {{
        put(HxPageDomainType.TEST, "http://www.baidu.com/");
    }};

    private static Map<PageDomainType, String> TEST = new HashMap<PageDomainType, String>() {{
        put(HxPageDomainType.TEST, "http://www.baidu.com/");
    }};

    static Map<PageDomainType, String> getDomain(boolean isDebug) {
        if (isDebug) {
            return TEST;
        }
        return PRODUCT;
    }
}
