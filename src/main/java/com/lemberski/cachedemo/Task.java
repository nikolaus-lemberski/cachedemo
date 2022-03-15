package com.lemberski.cachedemo;

import java.io.Serializable;

import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("TASKS")
public class Task implements Serializable {

    @Id
    private String id;
    private String description;
    private Boolean done;

    public Task() {
        //
    }

    @ProtoFactory
    public Task(String id, String description, Boolean done) {
        this.id = id;
        this.description = description;
        this.done = done;
    }

    @ProtoField(value = 1, required = true)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ProtoField(value = 2, required = true)
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ProtoField(value = 3, required = true)
    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

}
