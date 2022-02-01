package com.lemberski.cachedemo;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    private UUID id;
    private String description;

    public Task(String description) {
        this.id = UUID.randomUUID();
        this.description = description;
    }

}
