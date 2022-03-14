package com.lemberski.cachedemo.config;

import javax.annotation.PostConstruct;

import com.lemberski.cachedemo.App;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.configuration.BasicConfiguration;
import org.infinispan.commons.configuration.XMLStringConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class RemoteCache {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteCache.class);

    private static final String CACHE_CONFIG = "<infinispan><cache-container>" +
            "<distributed-cache name=\"%s\"></distributed-cache>" +
            "</cache-container></infinispan>";

    @Autowired
    private RemoteCacheManager remoteCacheManager;

    @PostConstruct
    public void setup() {
        LOG.info("Setting up Infinispan remote cache");

        String xml = String.format(CACHE_CONFIG, App.TASKS_CACHE);
        BasicConfiguration config = new XMLStringConfiguration(xml);
        remoteCacheManager
                .administration()
                .getOrCreateCache(App.TASKS_CACHE, config);

        LOG.info("Infinispan cache setup done");
    }

}
