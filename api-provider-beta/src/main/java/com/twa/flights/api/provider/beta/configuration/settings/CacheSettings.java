package com.twa.flights.api.provider.beta.configuration.settings;

import java.util.Objects;

public class CacheSettings {

    private Integer expireAfterWriteTime;

    private static final Integer DEFAULT_EXPIRE_AFTER = 15;

    public static final CacheSettings DEFAULT_CACHE_SETTINGS = new CacheSettings(DEFAULT_EXPIRE_AFTER);

    public CacheSettings() {
    }

    public CacheSettings(Integer expireAfterWriteTime) {
        this.expireAfterWriteTime = expireAfterWriteTime;
    }


    public Integer getExpireAfterWriteTime() {
        return expireAfterWriteTime;
    }

    public void setExpireAfterWriteTime(Integer expireAfterWriteTime) {
        this.expireAfterWriteTime = expireAfterWriteTime;
    }

  @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        CacheSettings that = (CacheSettings) o;
        return Objects.equals(expireAfterWriteTime, that.expireAfterWriteTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(expireAfterWriteTime);
    }

    @Override
    public String toString() {
        return "CacheSettings{" + ", expireAfter=" + expireAfterWriteTime + '}';
    }
}