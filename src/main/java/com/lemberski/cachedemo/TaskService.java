package com.lemberski.cachedemo;

import static java.lang.String.format;
import static java.util.stream.Collectors.toList;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import javax.annotation.PostConstruct;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

@Service
@CacheConfig(cacheNames = { "tasks" })
public class TaskService {

    private final Map<UUID, Task> tasks = new HashMap<>();

    @PostConstruct
    public void setup() {
        IntStream.range(0, 10).forEach(i -> {
            Task task = new Task(format("Task %s description", ++i));
            tasks.put(task.getId(), task);
        });
    }

    public Task create(String description) {
        Task task = new Task(description);
        tasks.put(task.getId(), task);
        return task;
    }

    @Cacheable(key = "#id", unless = "#result==null")
    public Task get(UUID id) {
        sleep(10);

        return tasks.get(id);
    }

    @CacheEvict(key = "#id")
    public void delete(UUID id) {
        tasks.remove(id);
    }

    public List<Task> getAll() {
        return tasks
                .values()
                .stream()
                .sorted((t1, t2) -> t1.getDescription().compareTo(t2.getDescription()))
                .collect(toList());
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            // ignore
        }
    }

}
