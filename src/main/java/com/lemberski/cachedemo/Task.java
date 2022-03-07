package com.lemberski.cachedemo;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table("TASKS")
public class Task implements Serializable {

    @Id
    private Long id;
    private String description;
    private Boolean done;

}
