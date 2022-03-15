package com.lemberski.cachedemo.config;

import com.lemberski.cachedemo.Task;

import org.infinispan.protostream.GeneratedSchema;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder(schemaPackageName = "cachedemo", includeClasses = Task.class)
public interface TasksSchemaBuilder extends GeneratedSchema {
    //
}
