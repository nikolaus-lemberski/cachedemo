package com.lemberski.cachedemo;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends CrudRepository<Task, UUID> {
    //
}