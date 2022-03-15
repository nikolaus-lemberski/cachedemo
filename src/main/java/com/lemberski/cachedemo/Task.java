package com.lemberski.cachedemo;

import org.infinispan.protostream.annotations.ProtoDoc;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@ProtoDoc("@Indexed")
@Table("TASKS")
public class Task {

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
    @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.YES, store = Store.YES)")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @ProtoField(value = 2, required = true)
    @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.YES, store = Store.YES)")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ProtoField(value = 3, required = true)
    @ProtoDoc("@Field(index=Index.YES, analyze = Analyze.YES, store = Store.YES)")
    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

}
