package com.lemberski.cachedemo.config;

import javax.annotation.PostConstruct;

import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.query.remote.client.ProtobufMetadataManagerConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class RemoteCacheSchemaUploader {

    private static final Logger LOG = LoggerFactory.getLogger(RemoteCacheSchemaUploader.class);

    @Autowired
    private RemoteCacheManager cacheManager;

    @PostConstruct
    public void setup() {
        LOG.info("Start protobuf schema upload to Infinispan");

        RemoteCache<String, String> metadataCache = cacheManager
                .getCache(ProtobufMetadataManagerConstants.PROTOBUF_METADATA_CACHE_NAME);
        GeneratedSchema schema = new TasksSchemaBuilderImpl();
        metadataCache.put(schema.getProtoFileName(), schema.getProtoFile());

        LOG.info("Protobuf schema upload to Infinispan done");
    }

}
