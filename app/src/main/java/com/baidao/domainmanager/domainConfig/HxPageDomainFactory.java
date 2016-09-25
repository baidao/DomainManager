package com.baidao.domainmanager.domainConfig;

import android.content.Context;

import com.baidao.domain.PageDomainFactory;
import com.baidao.domain.PageDomainType;

import java.util.Map;

/**
 * @author rjhy
 * @created on 16-8-31
 * @desc desc
 */
public class HxPageDomainFactory extends PageDomainFactory {

    @Override
    public Map<PageDomainType, String> getDomain(Context context, boolean isDebug) {
        return HxPageDomainConfig.getDomain(isDebug);
    }
}
