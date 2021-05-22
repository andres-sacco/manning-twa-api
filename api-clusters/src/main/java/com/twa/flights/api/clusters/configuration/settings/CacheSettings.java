package com.twa.flights.api.clusters.configuration.settings;

public class CacheSettings {
    private int expireAfterWriteTime;

    public int getExpireAfterWriteTime() {
        return expireAfterWriteTime;
    }

    public void setExpireAfterWriteTime(int expireAfterWriteTime) {
        this.expireAfterWriteTime = expireAfterWriteTime;
    }
}
