package com.twa.flights.api.clusters.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.info.InfoEndpoint;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AppInfoService {

    private static final String BUILD_PROPERTY = "build";
    private static final String APP_NAME_PROPERTY = "appName";
    private static final String VERSION_PROPERTY = "version";

    private final InfoEndpoint infoEndpoint;

    @Autowired
    public AppInfoService(InfoEndpoint infoEndpoint) {
        this.infoEndpoint = infoEndpoint;
    }

    @SuppressWarnings("unchecked")
    public String getAppName() {
        final Map<String, String> appInfo = (Map<String, String>) infoEndpoint.info().get(BUILD_PROPERTY);
        return appInfo.get(APP_NAME_PROPERTY);
    }

    @SuppressWarnings("unchecked")
    public String getVersion() {
        final Map<String, String> appInfo = (Map<String, String>) infoEndpoint.info().get(BUILD_PROPERTY);
        return appInfo.get(VERSION_PROPERTY);
    }
}