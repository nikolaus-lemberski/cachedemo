package com.lemberski.cachedemo.config;

import java.util.UUID;

import com.lemberski.cachedemo.GeneratedId;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.relational.core.mapping.event.AbstractRelationalEventListener;
import org.springframework.data.relational.core.mapping.event.BeforeConvertEvent;

@Configuration
public class EntityEventListener extends AbstractRelationalEventListener<GeneratedId> {

    @Override
    protected void onBeforeConvert(BeforeConvertEvent<GeneratedId> event) {
        GeneratedId entity = event.getEntity();
        if (entity.getId() == null) {
            entity.setId(UUID.randomUUID());
        }
        super.onBeforeConvert(event);
    }

}
