package com.lemberski.cachedemo;

import javax.annotation.PostConstruct;

import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.configuration.XMLStringConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class Cache {

    static final String TASKS_CACHE = "tasks";
    private static final String CACHE_CONFIG = "<infinispan><cache-container>" +
            "<distributed-cache name=\"%s\"></distributed-cache>" +
            "</cache-container></infinispan>";

    @Autowired
    private RemoteCacheManager remoteCacheManager;

    @PostConstruct
    public void setup() {
        String xml = String.format(CACHE_CONFIG, TASKS_CACHE);
        remoteCacheManager.administration().getOrCreateCache("tasks", new XMLStringConfiguration(xml));
    }

}
