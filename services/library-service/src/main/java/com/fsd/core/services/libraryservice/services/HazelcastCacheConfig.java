package com.fsd.core.services.libraryservice.services;

import com.hazelcast.config.Config;
import com.hazelcast.config.EvictionPolicy;
import com.hazelcast.config.MapConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastCacheConfig {

    @Bean
    public Config hazelCastConfig() {

        Config config = new Config();
        config.setInstanceName("hazelcast-cache");

        MapConfig bookCache = new MapConfig();
        bookCache.setTimeToLiveSeconds(200);
        bookCache.setEvictionPolicy(EvictionPolicy.LFU);
        config.getMapConfigs().put("books", bookCache);

        MapConfig userCache = new MapConfig();
        userCache.setTimeToLiveSeconds(200);
        userCache.setEvictionPolicy(EvictionPolicy.LFU);
        config.getMapConfigs().put("users", userCache);

        return config;
    }

}