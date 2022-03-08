package com.lemberski.cachedemo;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@CacheConfig(cacheNames = { App.TASKS_CACHE })
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task create(String description) {
        Task task = new Task();
        task.setDescription(description);
        task = taskRepository.save(task);
        return task;
    }

    @Cacheable(key = "#id", unless = "#result==null")
    public Task get(Long id) {
        // simulate something slowly like loading data from db or legacy system
        sleep(10);

        return taskRepository.findById(id).orElse(null);
    }

    @CacheEvict(key = "#task.id")
    public Task update(Task task) {
        if (taskRepository.existsById(task.getId())) {
            task = taskRepository.save(task);
            return task;
        }
        
        return null;
    }

    @CacheEvict(key = "#id")
    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public Iterable<Task> getAll() {
        return taskRepository.findAll();
    }

    private void sleep(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            // ignore
        }
    }

}
