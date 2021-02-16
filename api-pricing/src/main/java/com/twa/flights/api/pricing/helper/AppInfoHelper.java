package com.twa.flights.api.pricing.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class AppInfoHelper {
    private static final String BUILD_PROPERTY = "build";
    private static final String APP_NAME_PROPERTY = "appName";

    private final InfoEndpoint infoEndpoint;

    @Autowired
    public AppInfoHelper(InfoEndpoint infoEndpoint) {
        this.infoEndpoint = infoEndpoint;
    }

    @SuppressWarnings("unchecked")
    public String getAppName() {
        final Map<String, String> appInfo = (Map<String, String>) infoEndpoint.info().get(BUILD_PROPERTY);
        return appInfo.get(APP_NAME_PROPERTY);
    }
}
