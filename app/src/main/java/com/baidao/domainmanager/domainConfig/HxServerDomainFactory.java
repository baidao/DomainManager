package com.baidao.domainmanager.domainConfig;

import android.content.Context;

import com.baidao.domain.ServerDomainFactory;
import com.baidao.domain.ServerDomainType;

import java.util.Map;

/**
 * @author rjhy
 * @created on 16-8-31
 * @desc desc
 */
public class HxServerDomainFactory extends ServerDomainFactory {

    @Override
    public Map<ServerDomainType, String> getDomain(Context context, boolean isDebug) {
        return HxServerDomainConfig.getDomain(isDebug);
    }
}
