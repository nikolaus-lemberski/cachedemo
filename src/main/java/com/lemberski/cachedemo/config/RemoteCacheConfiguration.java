package com.lemberski.cachedemo.config;

import java.net.URI;
import java.net.URISyntaxException;

import com.lemberski.cachedemo.App;

import org.infinispan.commons.marshall.ProtoStreamMarshaller;
import org.infinispan.spring.starter.remote.InfinispanRemoteCacheCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;

@Configuration
@Profile("prod")
public class RemoteCacheConfiguration {

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public InfinispanRemoteCacheCustomizer caches() {
        return configurationBuilder -> {
            URI cacheConfigUri;
            try {
                cacheConfigUri = this.getClass().getClassLoader().getResource("tasksCache.xml").toURI();
            } catch (URISyntaxException e) {
                throw new RuntimeException(e);
            }

            configurationBuilder.remoteCache(App.TASKS_CACHE).configurationURI(cacheConfigUri);
            configurationBuilder.remoteCache(App.TASKS_CACHE).marshaller(ProtoStreamMarshaller.class);
            configurationBuilder.addContextInitializer(new TasksSchemaBuilderImpl());
        };
    }

}
