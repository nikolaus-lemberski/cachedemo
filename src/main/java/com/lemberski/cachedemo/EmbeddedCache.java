package com.lemberski.cachedemo;

import javax.annotation.PostConstruct;

import org.infinispan.commons.api.CacheContainerAdmin.AdminFlag;
import org.infinispan.configuration.cache.Configuration;
import org.infinispan.configuration.cache.ConfigurationBuilder;
import org.infinispan.manager.EmbeddedCacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class EmbeddedCache {

    private static final Logger LOG = LoggerFactory.getLogger(EmbeddedCache.class);

    @Autowired
    private EmbeddedCacheManager cacheManager;

    @PostConstruct
    public void setup() {
        LOG.info("Setting up Infinispan embedded cache");

        Configuration config = new ConfigurationBuilder()
                .simpleCache(true)
                .build();

        cacheManager
                .administration()
                .withFlags(AdminFlag.VOLATILE)
                .getOrCreateCache(App.TASKS_CACHE, config);

        LOG.info("Infinispan cache set up done");
    }

}
